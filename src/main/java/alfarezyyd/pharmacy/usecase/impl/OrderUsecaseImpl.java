package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Order;
import alfarezyyd.pharmacy.model.web.order.OrderCreateRequest;
import alfarezyyd.pharmacy.model.web.order.OrderUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.OrderResponse;
import alfarezyyd.pharmacy.repository.OrderRepository;
import alfarezyyd.pharmacy.usecase.OrderUsecase;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderUsecaseImpl implements OrderUsecase {
  private final OrderRepository orderRepository;
  private final HikariDataSource hikariDataSource;

  public OrderUsecaseImpl(OrderRepository orderRepository, HikariDataSource hikariDataSource) {
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
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return allOrderResponse;
  }

  @Override
  public void createOrder(ServerError serverError, ClientError clientError, OrderCreateRequest orderCreateRequest) {
    try (Connection connection = hikariDataSource.getConnection()) {
      Order order = new Order();
      order.setCustomerId(orderCreateRequest.getCustomerId());
      order.setTotalAmount(orderCreateRequest.getTotalAmount());
      order.setPaymentMethod(orderCreateRequest.getPaymentMethod());
      order.setPaymentStatus(orderCreateRequest.getPaymentStatus());
      order.setOrderStatus(orderCreateRequest.getOrderStatus());
      order.setShippingMethod(orderCreateRequest.getShippingMethod());
      order.setTrackingNumber(orderCreateRequest.getTrackingNumber());
      orderRepository.createOrder(connection, order);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }

  @Override
  public void updateOrder(ServerError serverError, ClientError clientError, OrderUpdateRequest orderUpdateRequest) {
    try (Connection connection = hikariDataSource.getConnection()) {
      Order order = new Order();
      order.setId(orderUpdateRequest.getId());
      order.setCustomerId(orderUpdateRequest.getCustomerId());
      order.setTotalAmount(orderUpdateRequest.getTotalAmount());
      order.setPaymentMethod(orderUpdateRequest.getPaymentMethod());
      order.setPaymentStatus(orderUpdateRequest.getPaymentStatus());
      order.setOrderStatus(orderUpdateRequest.getOrderStatus());
      order.setShippingMethod(orderUpdateRequest.getShippingMethod());
      order.setTrackingNumber(orderUpdateRequest.getTrackingNumber());
      orderRepository.updateOrder(connection, order);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void deleteOrder(ServerError serverError, ClientError clientError, Long orderId) {
   try (Connection connection = hikariDataSource.getConnection()) {
      orderRepository.deleteOrder(connection, orderId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }
}
