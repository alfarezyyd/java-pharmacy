package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Address;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

public interface AddressRepository {
  Address getAddressById(Connection connection, Long addressId) throws DatabaseError;
  LinkedList<Address> getAllAddressByCustomerId(Connection connection, Long customerId) throws DatabaseError;

  void createAddress(Connection connection, Address address) throws DatabaseError;

  void updateAddress(Connection connection, Address address) throws DatabaseError;

  void softDeleteCustomerAddress(Connection connection, ArrayList<Long> arrayOfAddressId, Long customerId) throws DatabaseError;
  void permanentlyDeleteCustomerAddress(Connection connection, Long addressId, Long customerId) throws DatabaseError;
}
