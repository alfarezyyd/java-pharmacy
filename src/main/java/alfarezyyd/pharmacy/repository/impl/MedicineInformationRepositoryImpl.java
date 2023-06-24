package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.DosageForm;
import alfarezyyd.pharmacy.model.entity.MedicineInformation;
import alfarezyyd.pharmacy.repository.MedicineInformationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MedicineInformationRepositoryImpl implements MedicineInformationRepository {
  @Override
  public LinkedList<MedicineInformation> getAllMedicineInformation(Connection connection, Long id) throws DatabaseError {
    String sqlSyntax = """
        SELECT * FROM medicines_information
        """;
    LinkedList<MedicineInformation> allMedicineInformation = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        MedicineInformation medicineInformation = new MedicineInformation();
        medicineInformation.setId(resultSet.getLong("id"));
        medicineInformation.setDosageForm(DosageForm.fromValue(resultSet.getString("dosage_form")));
        medicineInformation.setStrength(resultSet.getFloat("strength"));
        medicineInformation.setIndications(resultSet.getString("indications"));
        medicineInformation.setContraindications(resultSet.getString("contraindications"));
        medicineInformation.setSideEffects(resultSet.getString("side_effects"));
        medicineInformation.setPrecautions(resultSet.getString("precautions"));
        medicineInformation.setStorageConditions(resultSet.getString("storage_conditions"));
        medicineInformation.setDescription(resultSet.getString("description"));
        medicineInformation.setExpiryDate(resultSet.getDate("expiry_date"));
        medicineInformation.setCountryOfOrigin(resultSet.getString("country_of_origin"));
        allMedicineInformation.add(medicineInformation);
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allMedicineInformation;
  }


  @Override
  public void createMedicineInformation(Connection connection, MedicineInformation medicineInformation) throws DatabaseError {
    String sqlSyntax = """
        INSERT INTO medicines_information VALUES (?,?,?,?,?,?,?,?,?,?,?)
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, medicineInformation.getId());
      preparedStatement.setString(2, medicineInformation.getDosageForm().getValue());
      preparedStatement.setFloat(3, medicineInformation.getStrength());
      preparedStatement.setString(4, medicineInformation.getIndications());
      preparedStatement.setString(5, medicineInformation.getContraindications());
      preparedStatement.setString(6, medicineInformation.getSideEffects());
      preparedStatement.setString(7, medicineInformation.getPrecautions());
      preparedStatement.setString(8, medicineInformation.getStorageConditions());
      preparedStatement.setString(9, medicineInformation.getDescription());
      preparedStatement.setDate(10, medicineInformation.getExpiryDate());
      preparedStatement.setString(11, medicineInformation.getCountryOfOrigin());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateMedicineInformation(Connection connection, MedicineInformation medicineInformation) throws DatabaseError {
    String sqlSyntax = """
        UPDATE medicines_information SET dosage_form=?, strength=?, indications=?, contraindications=?, side_effects=?, precautions=?, storage_conditions=?, description=?, expiry_date=?, country_of_origin=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, medicineInformation.getDosageForm().getValue());
      preparedStatement.setFloat(2, medicineInformation.getStrength());
      preparedStatement.setString(3, medicineInformation.getIndications());
      preparedStatement.setString(4, medicineInformation.getContraindications());
      preparedStatement.setString(5, medicineInformation.getSideEffects());
      preparedStatement.setString(6, medicineInformation.getPrecautions());
      preparedStatement.setString(7, medicineInformation.getStorageConditions());
      preparedStatement.setString(8, medicineInformation.getDescription());
      preparedStatement.setDate(9, medicineInformation.getExpiryDate());
      preparedStatement.setString(10, medicineInformation.getCountryOfOrigin());
      preparedStatement.setLong(11, medicineInformation.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }

  }

  @Override
  public void deleteMedicineInformation(Connection connection, Long medicineInformationId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM medicines_information WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, medicineInformationId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
