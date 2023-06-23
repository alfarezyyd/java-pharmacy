package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Address;

import java.sql.Connection;
import java.util.LinkedList;

public interface AddressRepository {
  LinkedList<Address> getAllAddress(Connection connection) throws DatabaseError;

  void createAddress(Connection connection, Address address) throws DatabaseError;

  void updateAddress(Connection connection, Address address) throws DatabaseError;

  void deleteSingleCustomerAddress(Connection connection, Long addressId, Long customerId) throws DatabaseError;
  void deleteAllCustomerAddress(Connection connection, Long customerId) throws DatabaseError;

}
