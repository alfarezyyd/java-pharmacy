package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
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
import alfarezyyd.pharmacy.util.SearchUtil;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Set;

public class CustomerUsecaseImpl implements CustomerUsecase {
  private final CustomerRepository customerRepository;
  private final AddressUsecase addressUsecase;
  private final AddressRepository addressRepository;
  private final HikariDataSource hikariDataSource;

  public CustomerUsecaseImpl(CustomerRepository customerRepository, AddressUsecase addressUsecase, AddressRepository addressRepository, HikariDataSource hikariDataSource) {
    this.customerRepository = customerRepository;
    this.hikariDataSource = hikariDataSource;
    this.addressUsecase = addressUsecase;
    this.addressRepository = addressRepository;
  }


  @Override
  public LinkedList<CustomerResponse> getAllCustomer(ServerError serverError) {
    LinkedList<CustomerResponse> customerResponses = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      for (var customer : allCustomer) {
        customerResponses.add(Model.convertToCustomerResponse(customer));
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return customerResponses;
  }

  @Override
  public CustomerResponse getDetailCustomer(ServerError serverError, ClientError clientError, Long customerId) {
    CustomerResponse customerResponse = new CustomerResponse();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      Customer customer = SearchUtil.binarySearch(allCustomer, customerId);
      if (customer == null) {
        clientError.addActionError("find customer", "failed! customer not found!");
        return null;
      }
      customerResponse = Model.convertToCustomerResponse(customer);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return customerResponse;
  }

  @Override
  public void createCustomer(ServerError serverError, ClientError clientError, CustomerCreateRequest customerCreateRequest) {
    Set<ConstraintViolation<CustomerCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(customerCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<CustomerCreateRequest> constraintViolation : constraintViolations) {
        clientError.addValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
      }
      return;
    }
    try (Connection connection = hikariDataSource.getConnection()) {
      Customer customer = new Customer();
      customer.setFullName(customerCreateRequest.getFullName());
      customer.setDateOfBirth(LocalDate.parse(customerCreateRequest.getDateOfBirth()));
      customer.setGender(Gender.valueOf(customerCreateRequest.getGender()));
      customer.setPhone(customerCreateRequest.getPhone());
      Long customerId = customerRepository.createCustomer(connection, customer);
      addressUsecase.createAddress(serverError, customerCreateRequest.getAddressCreateRequest(), customerId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }

  @Override
  public void updateCustomer(ServerError serverError, ClientError clientError, CustomerUpdateRequest customerUpdateRequest) {
    Set<ConstraintViolation<CustomerUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(customerUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<CustomerUpdateRequest> constraintViolation : constraintViolations) {
        clientError.addValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
      }
    }
    try (Connection connection = hikariDataSource.getConnection()) {
      Boolean isCustomerExists = customerRepository.checkIfCustomerExists(connection, customerUpdateRequest.getId());
      if (isCustomerExists) {
        Customer customer = new Customer();
        customer.setId(customerUpdateRequest.getId());
        customer.setFullName(customerUpdateRequest.getFullName());
        customer.setDateOfBirth(LocalDate.parse(customerUpdateRequest.getDateOfBirth()));
        customer.setGender(Gender.fromValue(customerUpdateRequest.getGender()));
        customer.setPhone(customerUpdateRequest.getPhone());
        customer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        customerRepository.updateCustomer(connection, customer);
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }

  @Override
  public void deleteCustomer(ServerError serverError, ClientError clientError, Long customerId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      Boolean isCustomerExists = customerRepository.checkIfCustomerExists(connection, customerId);
      if (isCustomerExists) {
        LinkedList<Address> allAddressByCustomerId = addressRepository.getAllAddressByCustomerId(connection, customerId);
        for (Address address : allAddressByCustomerId) {
          addressRepository.permanentlyDeleteCustomerAddress(connection, address.getId(), customerId);
        }
        customerRepository.permanentlyDeleteCustomer(connection, customerId);
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }
}



