package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.MedicineInformation;

import java.sql.Connection;

public interface MedicineInformationRepository {
  MedicineInformation getMedicineInformationById(Connection connection, Long id) throws ActionError, DatabaseError;

  void createMedicineInformation(Connection connection, MedicineInformation medicineInformation) throws DatabaseError;

  void updateMedicineInformation(Connection connection, MedicineInformation medicineInformation) throws DatabaseError;

  void deleteMedicineInformation(Connection connection, Long medicineInformationId) throws DatabaseError;
}
