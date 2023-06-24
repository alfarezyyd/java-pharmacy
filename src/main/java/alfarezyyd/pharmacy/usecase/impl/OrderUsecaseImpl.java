package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.helper.Transaction;
import alfarezyyd.pharmacy.model.entity.*;
import alfarezyyd.pharmacy.model.entity.option.OrderStatus;
import alfarezyyd.pharmacy.model.entity.option.PaymentMethod;
import alfarezyyd.pharmacy.model.entity.option.PaymentStatus;
import alfarezyyd.pharmacy.model.entity.option.ShippingMethod;
import alfarezyyd.pharmacy.model.web.order.OrderCreateRequest;
import alfarezyyd.pharmacy.model.web.order.OrderUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.OrderResponse;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.repository.OrderRepository;
import alfarezyyd.pharmacy.usecase.OrderMedicineUsecase;
import alfarezyyd.pharmacy.usecase.OrderUsecase;
import alfarezyyd.pharmacy.util.SearchUtil;
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
  public LinkedList<OrderResponse> getAllOrderByCustomerId(ServerError serverError, ClientError clientError, Long customerId) {
    LinkedList<OrderResponse> allOrderResponse = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Order> allOrder = orderRepository.getAllOrder(connection);
      LinkedList<Order> foundedOrders = SearchUtil.sequentialSearchByCustomerId(allOrder, customerId);
      if (foundedOrders.size() == 0) {
        clientError.addActionError("find order", "order not found");
        return null;
      }
      for (Order foundedOrder : foundedOrders) {
        allOrderResponse.add(Model.convertToOrderResponse(foundedOrder));
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
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      Customer customer = SearchUtil.binarySearch(allCustomer, orderCreateRequest.getCustomerId());
      if (customer == null) {
        clientError.addActionError("create order", "customer not found");
      }
      order.setCustomerId(orderCreateRequest.getCustomerId());
      order.setPaymentMethod(PaymentMethod.fromValue(orderCreateRequest.getPaymentMethod()));
      order.setPaymentStatus(PaymentStatus.fromValue(orderCreateRequest.getPaymentStatus()));
      order.setOrderStatus(OrderStatus.fromValue(orderCreateRequest.getOrderStatus()));
      order.setShippingMethod(ShippingMethod.fromValue(orderCreateRequest.getShippingMethod()));
      order.setTrackingNumber(orderCreateRequest.getTrackingNumber());
      Long orderId = orderRepository.createOrder(connection, order);
      Float totalAmount = orderMedicineUsecase.createOrderMedicine(connection, clientError, serverError, orderId, orderCreateRequest.getOrderMedicine());
      Transaction.commitOrRollback(serverError, clientError, connection);
      if (totalAmount != 0F) {
        updateTotalAmount(serverError, totalAmount, orderId);
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
      connection.setAutoCommit(false);

      LinkedList<Order> allOrder = orderRepository.getAllOrder(connection);
      Order order = SearchUtil.binarySearch(allOrder, orderUpdateRequest.getId());
      if (order == null) {
        clientError.addActionError("update order", "order not found");
        return;
      }
      order.setPaymentMethod(PaymentMethod.fromValue(orderUpdateRequest.getPaymentMethod()));
      order.setPaymentStatus(PaymentStatus.fromValue(orderUpdateRequest.getPaymentStatus()));
      order.setOrderStatus(OrderStatus.fromValue(orderUpdateRequest.getOrderStatus()));
      order.setShippingMethod(ShippingMethod.fromValue(orderUpdateRequest.getShippingMethod()));
      order.setTrackingNumber(orderUpdateRequest.getTrackingNumber());
      order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
      orderRepository.updateOrder(connection, order);
      Float totalAmount = orderMedicineUsecase.updateOrderMedicine(connection, clientError, serverError, order.getId(), orderUpdateRequest.getOrderMedicine());
      Transaction.commitOrRollback(serverError, clientError, connection);
      if (totalAmount != 0F) {
        updateTotalAmount(serverError, totalAmount, order.getId());
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteOrder(ServerError serverError, ClientError clientError, Long orderId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Order> allOrder = orderRepository.getAllOrder(connection);
      Order order = SearchUtil.binarySearch(allOrder, orderId);
      if (order == null) {
        clientError.addActionError("delete order", "order not found");
      }
      orderMedicineUsecase.deleteOrderMedicine(connection, serverError, orderId);
      orderRepository.deleteOrder(connection, orderId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateTotalAmount(ServerError serverError, Float totalAmount, Long orderId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      orderRepository.updateTotalAmount(connection, totalAmount, orderId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
