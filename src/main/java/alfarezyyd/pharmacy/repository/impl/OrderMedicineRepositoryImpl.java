package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.OrderMedicine;
import alfarezyyd.pharmacy.repository.OrderMedicineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderMedicineRepositoryImpl implements OrderMedicineRepository {
  @Override
  public void createOrderMedicine(Connection connection, OrderMedicine orderMedicine) throws DatabaseError {
    String sqlSyntax = """
        INSERT INTO orders_medicines(medicine_id,order_id, quantity, total_price) VALUES(?,?,?,?)
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      for (int i = 0; i < orderMedicine.getAllMedicineId().size(); i++) {
        preparedStatement.clearParameters();
        preparedStatement.setLong(1, orderMedicine.getAllMedicineId().get(i));
        preparedStatement.setLong(2, orderMedicine.getOrderId());
        preparedStatement.setLong(3, orderMedicine.getAllQuantity().get(i));
        preparedStatement.setLong(4, orderMedicine.getAllTotalPrice().get(i));
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteOrderMedicine(Connection connection, Long orderId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM orders_medicines WHERE order_id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, orderId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
