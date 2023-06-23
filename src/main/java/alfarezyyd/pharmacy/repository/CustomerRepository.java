package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Customer;

import java.sql.Connection;
import java.util.LinkedList;

public interface CustomerRepository {

  LinkedList<Customer> getAllCustomer(Connection connection) throws DatabaseError;

  Boolean checkIfCustomerExists(Connection connection, Long customerId) throws DatabaseError, ActionError;

  Long createCustomer(Connection connection, Customer customer) throws DatabaseError, ActionError;

  void updateCustomer(Connection connection, Customer customer) throws DatabaseError;


  void deleteCustomer(Connection connection, Long customerId) throws DatabaseError;
}
