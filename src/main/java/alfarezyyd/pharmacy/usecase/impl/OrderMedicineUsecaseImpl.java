package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Medicine;
import alfarezyyd.pharmacy.model.entity.OrderMedicine;
import alfarezyyd.pharmacy.model.web.orderMedicine.OrderMedicineRequest;
import alfarezyyd.pharmacy.repository.MedicineRepository;
import alfarezyyd.pharmacy.repository.OrderMedicineRepository;
import alfarezyyd.pharmacy.usecase.OrderMedicineUsecase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderMedicineUsecaseImpl implements OrderMedicineUsecase {
  private final OrderMedicineRepository orderMedicineRepository;
  private final MedicineRepository medicineRepository;

  public OrderMedicineUsecaseImpl(OrderMedicineRepository orderMedicineRepository, MedicineRepository medicineRepository) {
    this.orderMedicineRepository = orderMedicineRepository;
    this.medicineRepository = medicineRepository;
  }

  @Override
  public Float createOrderMedicine(Connection connection, ClientError clientError, ServerError serverError, Long orderId, OrderMedicineRequest orderMedicineRequest) {
    Float totalAmount = 0F;
    try {
      LinkedList<Medicine> allMedicine = medicineRepository.getAllMedicine(connection);
      LinkedList<Long> allTotalPrice = Model.countTotalPrice(clientError, orderMedicineRequest, allMedicine);
      if (allTotalPrice == null) {
        return null;
      }
      OrderMedicine orderMedicine = new OrderMedicine();
      orderMedicine.setOrderId(orderId);
      orderMedicine.setAllMedicineId(orderMedicineRequest.getAllMedicineId());
      orderMedicine.setAllQuantity(orderMedicineRequest.getAllQuantity());
      orderMedicine.setAllTotalPrice(allTotalPrice);
      orderMedicineRepository.createOrderMedicine(connection, orderMedicine);
      for (var price : allTotalPrice) {
        totalAmount += price;
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return totalAmount;
  }

  @Override
  public Float updateOrderMedicine(Connection connection, ClientError clientError, ServerError serverError, Long orderId, OrderMedicineRequest orderMedicineRequest) {
    Float totalAmount = 0F;
    try {
      LinkedList<Medicine> allMedicine = medicineRepository.getAllMedicine(connection);
      LinkedList<Long> allTotalPrice = Model.countTotalPrice(clientError, orderMedicineRequest, allMedicine);
      orderMedicineRepository.deleteOrderMedicine(connection, orderId);
      if (allTotalPrice == null) {
        return null;
      }
      OrderMedicine orderMedicine = new OrderMedicine();
      orderMedicine.setOrderId(orderId);
      orderMedicine.setAllMedicineId(orderMedicineRequest.getAllMedicineId());
      orderMedicine.setAllQuantity(orderMedicineRequest.getAllQuantity());
      orderMedicine.setAllTotalPrice(allTotalPrice);
      orderMedicineRepository.createOrderMedicine(connection, orderMedicine);
      for (var price : allTotalPrice) {
        totalAmount += price;
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return totalAmount;
  }

  @Override
  public void deleteOrderMedicine(Connection connection, ServerError serverError, Long orderId) {
    try {
      orderMedicineRepository.deleteOrderMedicine(connection, orderId);
    } catch (DatabaseError e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
