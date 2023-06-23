package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;

import java.util.LinkedList;

public interface AddressUsecase {
  LinkedList<AddressResponse> getAllAddressByCustomerId(ServerError serverError, ClientError clientError, Long customerId);

  void createAddress(ServerError serverError, ClientError clientError, AddressCreateRequest addressCreateRequest, Long id);

  void updateAddress(ServerError serverError, ClientError clientError, AddressUpdateRequest addressUpdateRequest);

  void deleteAddress(ServerError serverError, ClientError clientError, Long addressId, Long customerId);
}
