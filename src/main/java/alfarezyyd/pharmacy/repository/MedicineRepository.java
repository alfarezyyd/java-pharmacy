package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Medicine;

import java.sql.Connection;
import java.util.LinkedList;

public interface MedicineRepository {

  LinkedList<Medicine> getAllMedicine(Connection connection) throws DatabaseError;

  LinkedList<Medicine> getAllDeletedMedicine(Connection connection) throws DatabaseError;

  Medicine getMedicineById(Connection connection, Long id) throws DatabaseError, ActionError;

  Long createMedicine(Connection connection, Medicine medicine) throws DatabaseError, ActionError;

  void updateMedicine(Connection connection, Medicine medicine) throws DatabaseError;
  void softDeleteMedicine(Connection connection, Medicine medicine) throws DatabaseError;
  void permanentlyDeleteMedicine(Connection connection, Long medicineId) throws DatabaseError;
}

