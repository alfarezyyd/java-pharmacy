package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;
import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.util.SearchUtil;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class AddressUsecaseImpl implements AddressUsecase {
  private final AddressRepository addressRepository;
  private final HikariDataSource hikariDataSource;
  private final CustomerRepository customerRepository;

  public AddressUsecaseImpl(AddressRepository addressRepository, HikariDataSource hikariDataSource, CustomerRepository customerRepository) {
    this.addressRepository = addressRepository;
    this.hikariDataSource = hikariDataSource;
    this.customerRepository = customerRepository;
  }

  @Override
  public LinkedList<AddressResponse> getAllAddressByCustomerId(ServerError serverError, ClientError clientError, Long customerId) {
    LinkedList<AddressResponse> addressResponse = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Address> allAddress = addressRepository.getAllAddress(connection);
      LinkedList<Address> foundedAddresses = SearchUtil.sequentialSearchByCustomerId(allAddress, customerId);
      if (foundedAddresses.size() == 0) {
        clientError.addActionError("find address", "address not found");
        return null;
      }
      for (Address foundedAddress : foundedAddresses) {
        addressResponse.add(Model.convertToAddressResponse(foundedAddress));
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return addressResponse;
  }

  @Override
  public void createAddress(ServerError serverError, ClientError clientError, AddressCreateRequest addressCreateRequest, Long id) {

    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      Customer customer = SearchUtil.binarySearch(allCustomer, id);
      if (customer == null) {
        clientError.addActionError("create address", "customer not found");
        return;
      }
      Address address = new Address();
      address.setCustomerId(id);
      address.setStreet(addressCreateRequest.getStreet());
      address.setCity(addressCreateRequest.getCity());
      address.setState(addressCreateRequest.getState());
      address.setCountry(addressCreateRequest.getCountry());
      address.setPostalCode(addressCreateRequest.getPostalCode());
      address.setDefault(addressCreateRequest.getDefault());
      address.setDescription(addressCreateRequest.getDescription());
      addressRepository.createAddress(connection, address);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }


  @Override
  public void updateAddress(ServerError serverError, ClientError clientError, AddressUpdateRequest addressUpdateRequest) {
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      Customer customer = SearchUtil.binarySearch(allCustomer, addressUpdateRequest.getCustomerId());
      if (customer == null) {
        clientError.addActionError("update address", "customer not found");
        return;
      }

      LinkedList<Address> allAddress = addressRepository.getAllAddress(connection);
      Address address = SearchUtil.binarySearch(allAddress, addressUpdateRequest.getId());
      if (address == null) {
        clientError.addActionError("update address", "address not found");
        return;
      }

      address.setCustomerId(addressUpdateRequest.getCustomerId());
      address.setStreet(addressUpdateRequest.getStreet());
      address.setCity(addressUpdateRequest.getCity());
      address.setState(addressUpdateRequest.getState());
      address.setCountry(addressUpdateRequest.getCountry());
      address.setPostalCode(addressUpdateRequest.getPostalCode());
      address.setDefault(addressUpdateRequest.getDefault());
      address.setDescription(addressUpdateRequest.getDescription());
      addressRepository.updateAddress(connection, address);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    }
  }

  @Override
  public void deleteAddress(ServerError serverError, ClientError clientError, Long addressId, Long customerId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Customer> allCustomer = customerRepository.getAllCustomer(connection);
      Customer customer = SearchUtil.binarySearch(allCustomer, customerId);
      if (customer == null) {
        clientError.addActionError("update address", "customer not found");
        return;
      }

      LinkedList<Address> allAddress = addressRepository.getAllAddress(connection);
      Address address = SearchUtil.binarySearch(allAddress, addressId);
      if (address == null) {
        clientError.addActionError("update address", "address not found");
        return;
      }

      addressRepository.deleteSingleCustomerAddress(connection, addressId, customerId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
