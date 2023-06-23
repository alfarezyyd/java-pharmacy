package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.*;
import alfarezyyd.pharmacy.repository.OrderRepository;

import java.sql.*;
import java.util.LinkedList;

public class OrderRepositoryImpl implements OrderRepository {
  @Override
  public LinkedList<Order> getAllOrderByCustomerId(Connection connection, Long customerId) throws DatabaseError {
    String sqlSyntax = """
        SELECT * FROM orders WHERE customer_id = ?
        """;
    LinkedList<Order> allOrderByCustomer = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, customerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setCustomerId(resultSet.getLong("customer_id"));
        order.setTotalAmount(resultSet.getFloat("total_amount"));
        order.setPaymentMethod(PaymentMethod.fromValue(resultSet.getString("payment_method")));
        order.setPaymentStatus(PaymentStatus.fromValue(resultSet.getString("payment_status")));
        order.setOrderStatus(OrderStatus.fromValue(resultSet.getString("order_status")));
        order.setShippingMethod(ShippingMethod.fromValue(resultSet.getString("shipping_method")));
        order.setTrackingNumber(resultSet.getString("tracking_number"));
        order.setCreatedAt(resultSet.getTimestamp("created_at"));
        order.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        allOrderByCustomer.add(order);
      }
      return allOrderByCustomer;
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public Long createOrder(Connection connection, Order order) throws DatabaseError, ActionError {
    String sqlSyntax = """
        INSERT INTO orders(customer_id, total_amount, payment_method, payment_status, order_status, shipping_method, tracking_number)
        VALUES(?,?,?,?,?,?,?)
        """;
    try {
      long orderId;
      PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setLong(1, order.getCustomerId());
      preparedStatement.setFloat(2, order.getTotalAmount());
      preparedStatement.setString(3, order.getPaymentMethod().getValue());
      preparedStatement.setString(4, order.getPaymentStatus().getValue());
      preparedStatement.setString(5, order.getOrderStatus().getValue());
      preparedStatement.setString(6, order.getShippingMethod().getValue());
      preparedStatement.setString(7, order.getTrackingNumber());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        orderId = resultSet.getLong(1);
        return orderId;
      } else {
        throw new ActionError("create order", "create order failed");
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public Boolean checkOrderIfExists(Connection connection, Long orderId) throws DatabaseError, ActionError {
    String sqlSyntax = """
            SELECT * FROM orders WHERE id = ?;
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, orderId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return true;
      } else {
        throw new ActionError("check order if exists", "failed! order doesn't exists");
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateOrder(Connection connection, Order order) throws DatabaseError {
    String sqlSyntax = """
        UPDATE orders SET total_amount=?, payment_method = ?, payment_status=?, order_status=?, shipping_method=?, tracking_number=?, updated_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setFloat(1, order.getTotalAmount());
      preparedStatement.setString(2, order.getPaymentMethod().getValue());
      preparedStatement.setString(3, order.getPaymentStatus().getValue());
      preparedStatement.setString(4, order.getOrderStatus().getValue());
      preparedStatement.setString(5, order.getShippingMethod().getValue());
      preparedStatement.setString(6, order.getTrackingNumber());
      preparedStatement.setTimestamp(7, order.getUpdatedAt());
      preparedStatement.setLong(8, order.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }


  @Override
  public void deleteOrder(Connection connection, Long orderId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM orders WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, orderId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
