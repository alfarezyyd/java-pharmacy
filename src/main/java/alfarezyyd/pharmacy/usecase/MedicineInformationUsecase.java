package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.medicineInformation.MedicineInformationCreateRequest;
import alfarezyyd.pharmacy.model.web.medicineInformation.MedicineInformationUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.MedicineInformationResponse;

public interface MedicineInformationUsecase {
  MedicineInformationResponse getMedicineInformationById(ServerError serverError, ClientError clientError, Long medicineInformationId);

  void createMedicineInformation(ServerError serverError, ClientError clientError, MedicineInformationCreateRequest medicineInformationCreateRequest, Long medicineId);

  void updateMedicineInformation(ServerError serverError, ClientError clientError, MedicineInformationUpdateRequest medicineInformationUpdateRequest, Long medicineId);

  void deleteMedicineInformation(ServerError serverError, ClientError clientError, Long medicineInformationId);

}
