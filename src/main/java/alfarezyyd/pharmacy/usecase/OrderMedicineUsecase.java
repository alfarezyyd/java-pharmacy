package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.orderMedicine.OrderMedicineCreateRequest;

public interface OrderMedicineUsecase {
  void createOrderMedicine(ClientError clientError, ServerError serverError, Long orderId, OrderMedicineCreateRequest orderMedicineCreateRequest);
}
