package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.model.entity.Address;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

public interface AddressRepository {
  Address getAddressById(Connection connection, Long addressId);
  LinkedList<Address> getAllAddressByCustomerId(Connection connection, Long customerId);

  Long createAddress(Connection connection, Address address);

  void updateAddress(Connection connection, Address address);

  void softDeleteCustomerAddress(Connection connection, ArrayList<Long> arrayOfAddressId, Long customerId);
  void permanentlyDeleteCustomerAddress(Connection connection, Long addressId, Long customerId);
}
