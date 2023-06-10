package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.util.DatabaseUtil;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

public class CustomerUsecaseImpl implements CustomerUsecase {
  private CustomerRepository customerRepository;
  private AddressUsecase addressUsecase;
  private HikariDataSource hikariDataSource;

  public CustomerUsecaseImpl(CustomerRepository customerRepository, AddressUsecase addressUsecase, HikariDataSource hikariDataSource) {
    this.customerRepository = customerRepository;
    this.hikariDataSource = hikariDataSource;
    this.addressUsecase = addressUsecase;
  }


  @Override
  public LinkedList<CustomerResponse> getAllCustomer() {
    try (Connection connection = DatabaseUtil.getHikariDataSource().getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      LinkedList<CustomerResponse> customerResponses = new LinkedList<>();
      for (var customer : allCustomer) {
        customerResponses.add(Model.convertToCustomerResponse(customer));
      }
      return customerResponses;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void createCustomer(CustomerCreateRequest customerCreateRequest) {
    try {
      Address address = new Address();
      Long addressId = addressUsecase.createAddress(customerCreateRequest.getAddressCreateRequest());
      Customer customer = new Customer();
      customer.setFullName(customerCreateRequest.getFullName());
      customer.setAddressId(addressId);
      customer.setDateOfBirth(LocalDate.parse(customerCreateRequest.getDateOfBirth()));
      customer.setGender(Gender.valueOf(customerCreateRequest.getGender()));
      customer.setPhone(customerCreateRequest.getPhone());

      customerRepository.createCustomer(DatabaseUtil.getHikariDataSource().getConnection(), customer);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void updateCustomer(CustomerUpdateRequest customerUpdateRequest) {

  }

  @Override
  public void deleteCustomer(Long customerId) {

  }
}
