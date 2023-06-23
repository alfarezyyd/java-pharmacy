package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Medicine;
import alfarezyyd.pharmacy.model.entity.MedicineInformation;
import alfarezyyd.pharmacy.model.web.medicine.MedicineCreateRequest;
import alfarezyyd.pharmacy.model.web.medicine.MedicineUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.MedicineInformationResponse;
import alfarezyyd.pharmacy.model.web.response.MedicineResponse;
import alfarezyyd.pharmacy.repository.MedicineRepository;
import alfarezyyd.pharmacy.usecase.MedicineInformationUsecase;
import alfarezyyd.pharmacy.usecase.MedicineUsecase;
import alfarezyyd.pharmacy.util.SearchUtil;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Set;

public class MedicineUsecaseImpl implements MedicineUsecase {
  private final MedicineRepository medicineRepository;
  private final HikariDataSource hikariDataSource;
  private final MedicineInformationUsecase medicineInformationUsecase;

  public MedicineUsecaseImpl(MedicineRepository medicineRepository, HikariDataSource hikariDataSource, MedicineInformationUsecase medicineInformationUsecase) {
    this.medicineRepository = medicineRepository;
    this.hikariDataSource = hikariDataSource;
    this.medicineInformationUsecase = medicineInformationUsecase;
  }

  @Override
  public LinkedList<MedicineResponse> getAllMedicine(ServerError serverError, ClientError clientError) {
    LinkedList<MedicineResponse> allMedicineResponse = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Medicine> allMedicine = medicineRepository.getAllMedicine(connection);
      for (Medicine medicine : allMedicine) {
        MedicineResponse medicineResponse = Model.convertToMedicineResponse(medicine, null);
        allMedicineResponse.add(medicineResponse);
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return allMedicineResponse;
  }

  @Override
  public MedicineResponse getDetailMedicine(ServerError serverError, ClientError clientError, Long medicineId) {
    MedicineResponse medicineResponse = new MedicineResponse();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Medicine> allMedicine = medicineRepository.getAllMedicine(connection);
      Medicine medicine = SearchUtil.binarySearch(allMedicine, medicineId);
      if (medicine != null) {
        MedicineInformationResponse medicineInformationResponse = medicineInformationUsecase.getMedicineInformationById(serverError, clientError, medicineId);
        medicineResponse = Model.convertToMedicineResponse(medicine, medicineInformationResponse);
      } else {
        clientError.addActionError("get detail medicine", "medicine not found!");
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return medicineResponse;
  }

  @Override
  public void createMedicine(ServerError serverError, ClientError clientError, MedicineCreateRequest medicineCreateRequest) {
    Set<ConstraintViolation<MedicineCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(medicineCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<MedicineCreateRequest> constraintViolation : constraintViolations) {
        clientError.addValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
      }
      return;
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      Medicine medicine = new Medicine();
      medicine.setName(medicineCreateRequest.getName());
      medicine.setBrand(medicineCreateRequest.getBrand());
      medicine.setPrice(medicineCreateRequest.getPrice());
      medicine.setStock(medicineCreateRequest.getStock());
      Long idNewMedicine = medicineRepository.createMedicine(connection, medicine);
      medicineInformationUsecase.createMedicineInformation(serverError, clientError, medicineCreateRequest.getMedicineInformationCreateRequest(), idNewMedicine);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void updateMedicine(ServerError serverError, ClientError clientError, MedicineUpdateRequest medicineUpdateRequest) {
    Set<ConstraintViolation<MedicineUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(medicineUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<MedicineUpdateRequest> constraintViolation : constraintViolations) {
        clientError.addValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
      }
      return;
    }
    try (Connection connection = hikariDataSource.getConnection()) {
      Boolean isMedicineExists = medicineRepository.checkIfMedicineExists(connection, medicineUpdateRequest.getId());
      if (isMedicineExists) {
        Medicine medicine = new Medicine();
        medicine.setId(medicineUpdateRequest.getId());
        medicine.setName(medicineUpdateRequest.getName());
        medicine.setBrand(medicineUpdateRequest.getBrand());
        medicine.setPrice(medicineUpdateRequest.getPrice());
        medicine.setStock(medicineUpdateRequest.getStock());
        medicineRepository.updateMedicine(connection, medicine);
        medicineInformationUsecase.updateMedicineInformation(serverError, clientError, medicineUpdateRequest.getMedicineInformationCreateRequest(), medicine.getId());
      }
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void deleteMedicine(ServerError serverError, ClientError clientError, Long medicineId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      Boolean isMedicineExists = medicineRepository.checkIfMedicineExists(connection, medicineId);
      if (isMedicineExists) {
        medicineInformationUsecase.deleteMedicineInformation(serverError, clientError, medicineId);
        medicineRepository.deleteMedicine(connection, medicineId);
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }
}
