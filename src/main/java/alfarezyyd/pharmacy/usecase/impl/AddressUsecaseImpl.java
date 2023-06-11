package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;
import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

public class AddressUsecaseImpl implements AddressUsecase {
  private AddressRepository addressRepository;
  private HikariDataSource hikariDataSource;

  public AddressUsecaseImpl(AddressRepository addressRepository, HikariDataSource hikariDataSource) {
    this.addressRepository = addressRepository;
    this.hikariDataSource = hikariDataSource;
  }

  @Override
  public AddressResponse findAddressById(Long addressId) {
    try {
      Address address = addressRepository.getAddressById(hikariDataSource.getConnection(), addressId);
      return Model.convertToAddressResponse(address);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Long createAddress(AddressCreateRequest addressCreateRequest, Long id) {
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
      Long addressId = addressRepository.createAddress(hikariDataSource.getConnection(), address);
      return addressId;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void updateAddress(AddressUpdateRequest addressUpdateRequest) {
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
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
