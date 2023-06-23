package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.OrderMedicine;

import java.sql.Connection;
import java.util.ArrayList;

public interface OrderMedicineRepository {
  void createOrderMedicine(Connection connection, OrderMedicine orderMedicine) throws DatabaseError;
}
