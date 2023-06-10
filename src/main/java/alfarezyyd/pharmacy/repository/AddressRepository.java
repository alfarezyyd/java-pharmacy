package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.model.entity.Address;

import java.sql.Connection;

public interface AddressRepository {
  Address getAddressById(Connection connection, Long addressId);

  Long createAddress(Connection connection, Address address);

  void updateAddress(Connection connection, Address address);

  void softDeleteAddress(Connection connection, Long addressId);
  void permanentlyDeleteAddress(Connection connection, Long addressId);
}
