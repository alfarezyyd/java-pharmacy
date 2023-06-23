package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.orderMedicine.OrderMedicineRequest;

import java.sql.Connection;

public interface OrderMedicineUsecase {
  Float createOrderMedicine(Connection connection, ClientError clientError, ServerError serverError, Long orderId, OrderMedicineRequest orderMedicineRequest);

  Float updateOrderMedicine(Connection connection, ClientError clientError, ServerError serverError, Long orderId, OrderMedicineRequest orderMedicineRequest);

  void deleteOrderMedicine(Connection connection, ServerError serverError, Long orderId);
}
