package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.MedicineInformation;

import java.sql.Connection;
import java.util.LinkedList;

public interface MedicineInformationRepository {
  LinkedList<MedicineInformation> getAllMedicineInformation(Connection connection, Long id) throws DatabaseError;

  Boolean checkIfMedicineInformationExists(Connection connection, Long id) throws DatabaseError, ActionError;

  void createMedicineInformation(Connection connection, MedicineInformation medicineInformation) throws DatabaseError;

  void updateMedicineInformation(Connection connection, MedicineInformation medicineInformation) throws DatabaseError;

  void deleteMedicineInformation(Connection connection, Long medicineInformationId) throws DatabaseError;
}
