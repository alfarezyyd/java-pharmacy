package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;

import java.util.LinkedList;

public interface CustomerUsecase {
  LinkedList<CustomerResponse> getAllCustomer(ServerError serverError);

  LinkedList<CustomerResponse> getAllDeletedCustomer(ServerError serverError);

  void createCustomer(ServerError serverError, ClientError clientError, CustomerCreateRequest customerCreateRequest);

  void updateCustomer(ServerError serverError, ClientError clientError, CustomerUpdateRequest customerUpdateRequest);

  void deleteCustomer(ServerError serverError, ClientError clientError, Long customerId);

}
