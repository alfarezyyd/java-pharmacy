package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;
import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class AddressUsecaseImpl implements AddressUsecase {
  private final AddressRepository addressRepository;
  private final HikariDataSource hikariDataSource;

  public AddressUsecaseImpl(AddressRepository addressRepository, HikariDataSource hikariDataSource) {
    this.addressRepository = addressRepository;
    this.hikariDataSource = hikariDataSource;
  }

  @Override
  public AddressResponse findAddressById(ServerError serverError, ClientError clientError, Long addressId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      Address address = addressRepository.getAddressById(connection, addressId);
      return Model.convertToAddressResponse(address);
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    } catch (SQLException e) {
     serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return new AddressResponse();
  }

  @Override
  public void createAddress(ServerError serverError, AddressCreateRequest addressCreateRequest, Long id) {
    try {
      Address address = new Address();
      address.setCustomerId(id);
      address.setStreet(addressCreateRequest.getStreet());
      address.setCity(addressCreateRequest.getCity());
      address.setState(addressCreateRequest.getState());
      address.setCountry(addressCreateRequest.getCountry());
      address.setPostalCode(addressCreateRequest.getPostalCode());
      address.setDefault(addressCreateRequest.getDefault());
      address.setDescription(addressCreateRequest.getDescription());
      addressRepository.createAddress(hikariDataSource.getConnection(), address);
    } catch (SQLException e) {
     serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }


  @Override
  public void updateAddress(ServerError serverError, ClientError clientError, AddressUpdateRequest addressUpdateRequest) {
    try {
      Address address = addressRepository.getAddressById(hikariDataSource.getConnection(), addressUpdateRequest.getId());
      if (address != null) {
        address.setStreet(addressUpdateRequest.getStreet());
        address.setCity(addressUpdateRequest.getCity());
        address.setState(addressUpdateRequest.getState());
        address.setCountry(addressUpdateRequest.getCountry());
        address.setPostalCode(addressUpdateRequest.getPostalCode());
        address.setDefault(addressUpdateRequest.getDefault());
        address.setDescription(addressUpdateRequest.getDescription());
        addressRepository.updateAddress(hikariDataSource.getConnection(), address);
      }
    } catch (ActionError e) {
      clientError.addActionError(e.getAction(), e.getErrorMessage());
    } catch (SQLException e) {
     serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
