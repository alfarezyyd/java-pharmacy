package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;

import java.util.LinkedList;

public interface CustomerUsecase {
  LinkedList<CustomerResponse> getAllCustomer();
  LinkedList<CustomerResponse> getAllDeletedCustomer();

  void createCustomer(CustomerCreateRequest customerCreateRequest);

  void updateCustomer(CustomerUpdateRequest customerUpdateRequest);

  void deleteCustomer(Long customerId);

}
