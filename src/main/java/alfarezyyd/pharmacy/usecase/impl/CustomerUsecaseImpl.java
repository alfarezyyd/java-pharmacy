package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.config.Database;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class CustomerUsecaseImpl implements CustomerUsecase {
  private CustomerRepository customerRepository;
  private AddressUsecase addressUsecase;
  private AddressRepository addressRepository;
  private HikariDataSource hikariDataSource;

  public CustomerUsecaseImpl(CustomerRepository customerRepository, AddressUsecase addressUsecase, AddressRepository addressRepository, HikariDataSource hikariDataSource) {
    this.customerRepository = customerRepository;
    this.hikariDataSource = hikariDataSource;
    this.addressUsecase = addressUsecase;
    this.addressRepository = addressRepository;
  }


  @Override
  public LinkedList<CustomerResponse> getAllCustomer() {
    LinkedList<CustomerResponse> customerResponses = new LinkedList<>();
    try (Connection connection = Database.getHikariDataSource().getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      for (var customer : allCustomer) {
        customerResponses.add(Model.convertToCustomerResponse(customer));
      }
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return customerResponses;
  }

  @Override
  public LinkedList<CustomerResponse> getAllDeletedCustomer() {
    LinkedList<CustomerResponse> customerResponses = new LinkedList<>();

    try (Connection connection = Database.getHikariDataSource().getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllDeletedCustomer(connection);
      for (var customer : allCustomer) {
        customerResponses.add(Model.convertToCustomerResponse(customer));
      }
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return customerResponses;

  }

  @Override
  public void createCustomer(CustomerCreateRequest customerCreateRequest) {
    Set<ConstraintViolation<CustomerCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(customerCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<CustomerCreateRequest> constraintViolation : constraintViolations) {
        ClientError.addValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
      }
      return;
    }
    try {
      Customer customer = new Customer();
      customer.setFullName(customerCreateRequest.getFullName());
      customer.setDateOfBirth(LocalDate.parse(customerCreateRequest.getDateOfBirth()));
      customer.setGender(Gender.valueOf(customerCreateRequest.getGender()));
      customer.setPhone(customerCreateRequest.getPhone());
      Long customerId = customerRepository.createCustomer(Database.getHikariDataSource().getConnection(), customer);
      addressUsecase.createAddress(customerCreateRequest.getAddressCreateRequest(), customerId);
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
    Set<ConstraintViolation<CustomerUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(customerUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<CustomerUpdateRequest> constraintViolation : constraintViolations) {
        ClientError.addValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
      }
    }
    try {
      Customer customer = customerRepository.getCustomerById(hikariDataSource.getConnection(), customerUpdateRequest.getId());
      if (customer != null) {
        customer.setFullName(customerUpdateRequest.getFullName());
        customer.setDateOfBirth(LocalDate.parse(customerUpdateRequest.getDateOfBirth()));
        customer.setGender(Gender.valueOf(customerUpdateRequest.getGender()));
        customer.setPhone(customerUpdateRequest.getPhone());
        customer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        customerRepository.updateCustomer(hikariDataSource.getConnection(), customer);
      }
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void deleteCustomer(Long customerId) {
    try {
      Customer customer = customerRepository.getCustomerById(hikariDataSource.getConnection(), customerId);
      if (customer != null) {
        if (customer.getDeletedAt() == null) {
          customer.setDeletedAt(new Timestamp(System.currentTimeMillis()));
          customerRepository.softDeleteCustomer(hikariDataSource.getConnection(), customer);
          LinkedList<Address> allAddressByCustomerId = addressRepository.getAllAddressByCustomerId(hikariDataSource.getConnection(), customer.getId());
          ArrayList<Long> arrayOfAddressId = new ArrayList<>();
          for (Address address : allAddressByCustomerId) {
            arrayOfAddressId.add(address.getId());
          }
          addressRepository.softDeleteCustomerAddress(hikariDataSource.getConnection(), arrayOfAddressId, customer.getId());

        } else {
          LinkedList<Address> allAddressByCustomerId = addressRepository.getAllAddressByCustomerId(hikariDataSource.getConnection(), customer.getId());
          for (Address address : allAddressByCustomerId) {
            addressRepository.permanentlyDeleteCustomerAddress(hikariDataSource.getConnection(), address.getId(), customerId);
          }
          customerRepository.permanentlyDeleteCustomer(hikariDataSource.getConnection(), customer.getId());
        }
      }
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }
}
