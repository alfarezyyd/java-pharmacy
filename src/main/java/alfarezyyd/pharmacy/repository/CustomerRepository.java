package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.model.entity.Customer;

import java.sql.Connection;
import java.util.LinkedList;

public interface CustomerRepository {
  Customer getCustomerById(Connection connection, Long customerId);

  LinkedList<Customer> getAllCustomer(Connection connection);
  LinkedList<Customer> getAllDeletedCustomer(Connection connection);


  Long createCustomer(Connection connection, Customer customer);

  void updateCustomer(Connection connection, Customer customer);

  void softDeleteCustomer(Connection connection,Customer customer);

  void permanentlyDeleteCustomer(Connection connection, Long customerId);
}
