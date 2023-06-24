package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Order;

import java.sql.Connection;
import java.util.LinkedList;

public interface OrderRepository {
  LinkedList<Order> getAllOrder(Connection connection) throws DatabaseError;

  Long createOrder(Connection connection, Order order) throws DatabaseError, ActionError;


  void updateOrder(Connection connection, Order order) throws DatabaseError;

  void deleteOrder(Connection connection, Long orderId) throws DatabaseError;

  void updateTotalAmount(Connection connection, Float totalAmount, Long orderId) throws DatabaseError;
}
