package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.entity.OrderMedicine;
import alfarezyyd.pharmacy.model.web.orderMedicine.OrderMedicineCreateRequest;
import alfarezyyd.pharmacy.repository.MedicineRepository;
import alfarezyyd.pharmacy.repository.OrderMedicineRepository;
import alfarezyyd.pharmacy.usecase.OrderMedicineUsecase;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderMedicineUsecaseImpl implements OrderMedicineUsecase {
  private final HikariDataSource hikariDataSource;
  private final OrderMedicineRepository orderMedicineRepository;
  private final MedicineRepository medicineRepository;

  public OrderMedicineUsecaseImpl(HikariDataSource hikariDataSource, OrderMedicineRepository orderMedicineRepository, MedicineRepository medicineRepository) {
    this.hikariDataSource = hikariDataSource;
    this.orderMedicineRepository = orderMedicineRepository;
    this.medicineRepository = medicineRepository;
  }

  @Override
  public void createOrderMedicine(ClientError clientError, ServerError serverError, Long orderId, OrderMedicineCreateRequest orderMedicineCreateRequest) {
    try (Connection connection = hikariDataSource.getConnection()) {
      for (var medicineId : orderMedicineCreateRequest.getAllMedicineId()) {
        medicineRepository.checkIfMedicineExists(connection, medicineId);
      }
      OrderMedicine orderMedicine = new OrderMedicine();
      orderMedicine.setOrderId(orderId);
      orderMedicine.setAllMedicineId(orderMedicineCreateRequest.getAllMedicineId());
      orderMedicine.setAllQuantity(orderMedicineCreateRequest.getAllQuantity());
      orderMedicine.setAllTotalPrice(orderMedicineCreateRequest.getAllTotalPrice());
      orderMedicineRepository.createOrderMedicine(connection, orderMedicine);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }
}
