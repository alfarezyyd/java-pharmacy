package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;

public interface AddressUsecase {
  AddressResponse findAddressById(ServerError serverError, Long addressId);

  void createAddress(ServerError serverError, AddressCreateRequest addressCreateRequest, Long id);

  void updateAddress(ServerError serverError, ClientError clientError, AddressUpdateRequest addressUpdateRequest);

}
