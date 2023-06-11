package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;

public interface AddressUsecase {
  AddressResponse findAddressById(Long addressId);

  Long createAddress(AddressCreateRequest addressCreateRequest, Long id);

  void updateAddress(AddressUpdateRequest addressUpdateRequest);

}
