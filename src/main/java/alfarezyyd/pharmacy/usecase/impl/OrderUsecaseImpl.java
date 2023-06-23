package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.*;
import alfarezyyd.pharmacy.model.web.order.OrderCreateRequest;
import alfarezyyd.pharmacy.model.web.order.OrderUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.OrderResponse;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.repository.OrderRepository;
import alfarezyyd.pharmacy.usecase.OrderMedicineUsecase;
import alfarezyyd.pharmacy.usecase.OrderUsecase;
import alfarezyyd.pharmacy.util.StringUtil;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Set;

public class OrderUsecaseImpl implements OrderUsecase {
  private final CustomerRepository customerRepository;
  private final OrderMedicineUsecase orderMedicineUsecase;
  private final OrderRepository orderRepository;
  private final HikariDataSource hikariDataSource;

  public OrderUsecaseImpl(CustomerRepository customerRepository, OrderMedicineUsecase orderMedicineUsecase, OrderRepository orderRepository, HikariDataSource hikariDataSource) {
    this.customerRepository = customerRepository;
    this.orderMedicineUsecase = orderMedicineUsecase;
    this.orderRepository = orderRepository;
    this.hikariDataSource = hikariDataSource;
  }

  @Override
  public LinkedList<OrderResponse> getAllOrderByCustomerId(ServerError serverError, Long customerId) {
    LinkedList<OrderResponse> allOrderResponse = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Order> allOrderByCustomerId = orderRepository.getAllOrderByCustomerId(connection, customerId);
      for (var order : allOrderByCustomerId) {
        allOrderResponse.add(Model.convertToOrderResponse(order));
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allOrderResponse;
  }

  @Override
  public void createOrder(ServerError serverError, ClientError clientError, OrderCreateRequest orderCreateRequest) {
    Set<ConstraintViolation<OrderCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(orderCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<OrderCreateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      Order order = new Order();
      connection.setAutoCommit(false);
      Boolean isCustomerExists = customerRepository.checkIfCustomerExists(connection, orderCreateRequest.getCustomerId());
      if (isCustomerExists) {
        order.setCustomerId(orderCreateRequest.getCustomerId());
        order.setTotalAmount(orderCreateRequest.getTotalAmount());
        order.setPaymentMethod(PaymentMethod.fromValue(orderCreateRequest.getPaymentMethod()));
        order.setPaymentStatus(PaymentStatus.fromValue(orderCreateRequest.getPaymentStatus()));
        order.setOrderStatus(OrderStatus.fromValue(orderCreateRequest.getOrderStatus()));
        order.setShippingMethod(ShippingMethod.fromValue(orderCreateRequest.getShippingMethod()));
        order.setTrackingNumber(orderCreateRequest.getTrackingNumber());
        Long orderId = orderRepository.createOrder(connection, order);
        orderMedicineUsecase.createOrderMedicine(clientError, serverError, orderId, orderCreateRequest.getOrderMedicine());
        if (serverError.hasErrors() || clientError.hasErrors()) {
          connection.rollback();
        } else {
          connection.commit();
        }
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }

  @Override
  public void updateOrder(ServerError serverError, ClientError clientError, OrderUpdateRequest orderUpdateRequest) {
    Set<ConstraintViolation<OrderUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(orderUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<OrderUpdateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      Order order = new Order();
      Boolean checkOrderIfExists = orderRepository.checkOrderIfExists(connection, orderUpdateRequest.getId());
      if (checkOrderIfExists) {
        order.setId(orderUpdateRequest.getId());
        order.setCustomerId(orderUpdateRequest.getCustomerId());
        order.setTotalAmount(orderUpdateRequest.getTotalAmount());
        order.setPaymentMethod(PaymentMethod.fromValue(orderUpdateRequest.getPaymentMethod()));
        order.setPaymentStatus(PaymentStatus.fromValue(orderUpdateRequest.getPaymentStatus()));
        order.setOrderStatus(OrderStatus.fromValue(orderUpdateRequest.getOrderStatus()));
        order.setShippingMethod(ShippingMethod.fromValue(orderUpdateRequest.getShippingMethod()));
        order.setTrackingNumber(orderUpdateRequest.getTrackingNumber());
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.updateOrder(connection, order);
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }

  @Override
  public void deleteOrder(ServerError serverError, ClientError clientError, Long orderId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      if (orderRepository.checkOrderIfExists(connection, orderId)) {
        orderRepository.deleteOrder(connection, orderId);
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }
}
