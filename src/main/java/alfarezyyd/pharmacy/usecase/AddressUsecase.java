package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;

import java.util.LinkedList;

public interface AddressUsecase {
  AddressResponse findAddressById(Long addressId);

  Long createAddress(AddressCreateRequest addressCreateRequest);

  void updateAddress(AddressUpdateRequest addressUpdateRequest);

  void softDeleteAddress(Long addressId);

  void permanentlyDeleteAddress(Long addressId);

}
