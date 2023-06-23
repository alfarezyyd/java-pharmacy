package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Address;

import java.sql.Connection;
import java.util.LinkedList;

public interface AddressRepository {
  Address getAddressById(Connection connection, Long addressId) throws DatabaseError, ActionError;
  LinkedList<Address> getAllAddressByCustomerId(Connection connection, Long customerId) throws DatabaseError;

  void createAddress(Connection connection, Address address) throws DatabaseError;

  void updateAddress(Connection connection, Address address) throws DatabaseError;

  void deleteCustomerAddress(Connection connection, Long addressId, Long customerId) throws DatabaseError;
}
