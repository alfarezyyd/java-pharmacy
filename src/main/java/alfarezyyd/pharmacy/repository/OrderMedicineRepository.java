package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.OrderMedicine;

import java.sql.Connection;

public interface OrderMedicineRepository {
  void createOrderMedicine(Connection connection, OrderMedicine orderMedicine) throws DatabaseError;

  void deleteOrderMedicine(Connection connection, Long orderId) throws DatabaseError;
}
