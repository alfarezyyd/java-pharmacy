package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.option.DosageForm;
import alfarezyyd.pharmacy.model.entity.MedicineInformation;
import alfarezyyd.pharmacy.model.web.medicineInformation.MedicineInformationCreateRequest;
import alfarezyyd.pharmacy.model.web.medicineInformation.MedicineInformationUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.MedicineInformationResponse;
import alfarezyyd.pharmacy.repository.MedicineInformationRepository;
import alfarezyyd.pharmacy.usecase.MedicineInformationUsecase;
import alfarezyyd.pharmacy.util.SearchUtil;
import alfarezyyd.pharmacy.util.StringUtil;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Set;

public class MedicineInformationUsecaseImpl implements MedicineInformationUsecase {
  private final MedicineInformationRepository medicineInformationRepository;
  private final HikariDataSource hikariDataSource;

  public MedicineInformationUsecaseImpl(MedicineInformationRepository medicineInformationRepository, HikariDataSource hikariDataSource) {
    this.medicineInformationRepository = medicineInformationRepository;
    this.hikariDataSource = hikariDataSource;
  }

  @Override
  public MedicineInformationResponse getMedicineInformationById(ServerError serverError, ClientError clientError, Long medicineInformationId) {
    MedicineInformationResponse medicineInformationResponse = new MedicineInformationResponse();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<MedicineInformation> allMedicineInformation = medicineInformationRepository.getAllMedicineInformation(connection, medicineInformationId);
      MedicineInformation medicineInformation = SearchUtil.binarySearch(allMedicineInformation, medicineInformationId);
      if (medicineInformation == null) {
        clientError.addActionError("get medicine information", "failed! medicine information not found");
        return null;
      }
      medicineInformationResponse = Model.convertToMedicineInformationResponse(medicineInformation);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return medicineInformationResponse;
  }

  @Override
  public void createMedicineInformation(ServerError serverError, ClientError clientError, MedicineInformationCreateRequest medicineInformationCreateRequest, Long medicineId) {
    Set<ConstraintViolation<MedicineInformationCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(medicineInformationCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<MedicineInformationCreateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
      return;
    }
    try (Connection connection = hikariDataSource.getConnection()) {
      MedicineInformation medicineInformation = new MedicineInformation();
      medicineInformation.setId(medicineId);
      medicineInformation.setDosageForm(DosageForm.fromValue(medicineInformationCreateRequest.getDosageForm()));
      medicineInformation.setStrength(medicineInformationCreateRequest.getStrength());
      medicineInformation.setIndications(medicineInformationCreateRequest.getIndications());
      medicineInformation.setContraindications(medicineInformationCreateRequest.getContraindications());
      medicineInformation.setSideEffects(medicineInformationCreateRequest.getSideEffects());
      medicineInformation.setPrecautions(medicineInformationCreateRequest.getPrecautions());
      medicineInformation.setStorageConditions(medicineInformationCreateRequest.getStorageConditions());
      medicineInformation.setDescription(medicineInformationCreateRequest.getDescription());
      medicineInformation.setExpiryDate(Date.valueOf(medicineInformationCreateRequest.getExpiryDate()));
      medicineInformation.setCountryOfOrigin(medicineInformationCreateRequest.getCountryOfOrigin());
      medicineInformationRepository.createMedicineInformation(connection, medicineInformation);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateMedicineInformation(ServerError serverError, ClientError clientError, MedicineInformationUpdateRequest medicineInformationUpdateRequest, Long medicineId) {
    Set<ConstraintViolation<MedicineInformationUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(medicineInformationUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<MedicineInformationUpdateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
      return;
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<MedicineInformation> allMedicineInformation = medicineInformationRepository.getAllMedicineInformation(connection, medicineId);
      MedicineInformation medicineInformation = SearchUtil.binarySearch(allMedicineInformation, medicineId);
      if (medicineInformation == null) {
        clientError.addActionError("update medicine information", "medicine information not found");
        return;
      }
      medicineInformation.setDosageForm(DosageForm.fromValue(medicineInformationUpdateRequest.getDosageForm()));
      medicineInformation.setStrength(medicineInformationUpdateRequest.getStrength());
      medicineInformation.setIndications(medicineInformationUpdateRequest.getIndications());
      medicineInformation.setContraindications(medicineInformationUpdateRequest.getContraindications());
      medicineInformation.setSideEffects(medicineInformationUpdateRequest.getSideEffects());
      medicineInformation.setPrecautions(medicineInformationUpdateRequest.getPrecautions());
      medicineInformation.setStorageConditions(medicineInformationUpdateRequest.getStorageConditions());
      medicineInformation.setDescription(medicineInformationUpdateRequest.getDescription());
      medicineInformation.setExpiryDate(Date.valueOf(medicineInformationUpdateRequest.getExpiryDate()));
      medicineInformation.setCountryOfOrigin(medicineInformationUpdateRequest.getCountryOfOrigin());
      medicineInformationRepository.updateMedicineInformation(connection, medicineInformation);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteMedicineInformation(ServerError serverError, ClientError clientError, Long medicineInformationId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<MedicineInformation> allMedicineInformation = medicineInformationRepository.getAllMedicineInformation(connection, medicineInformationId);
      MedicineInformation medicineInformation = SearchUtil.binarySearch(allMedicineInformation, medicineInformationId);
      if (medicineInformation == null) {
        clientError.addActionError("delete medicine information", "medicine information not found");
        return;
      }
      medicineInformationRepository.deleteMedicineInformation(connection, medicineInformationId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
