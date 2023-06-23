package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Medicine;

import java.sql.Connection;
import java.util.LinkedList;

public interface MedicineRepository {

  LinkedList<Medicine> getAllMedicine(Connection connection) throws DatabaseError;

  Boolean checkIfMedicineExists(Connection connection, Long medicineId) throws DatabaseError, ActionError;

  Long createMedicine(Connection connection, Medicine medicine) throws DatabaseError;

  void updateMedicine(Connection connection, Medicine medicine) throws DatabaseError;


  void deleteMedicine(Connection connection, Long medicineId) throws DatabaseError;
}

