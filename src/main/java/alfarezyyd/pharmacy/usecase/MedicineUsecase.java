package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.medicine.MedicineCreateRequest;
import alfarezyyd.pharmacy.model.web.medicine.MedicineUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.MedicineResponse;

import java.util.LinkedList;

public interface MedicineUsecase {
  LinkedList<MedicineResponse> getAllMedicine(ServerError serverError, ClientError clientError, String sortedBy, String algorithm);

  MedicineResponse getDetailMedicine(ServerError serverError, ClientError clientError, Long medicineId);

  void createMedicine(ServerError serverError, ClientError clientError, MedicineCreateRequest medicineCreateRequest);

  void updateMedicine(ServerError serverError, ClientError clientError, MedicineUpdateRequest medicineUpdateRequest);

  void deleteMedicine(ServerError serverError, ClientError clientError, Long medicineId);

}
