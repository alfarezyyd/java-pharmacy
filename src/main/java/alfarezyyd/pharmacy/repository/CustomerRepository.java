package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.model.entity.Customer;

import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;

public interface CustomerRepository {
  LinkedList<Customer> getAllCustomer(Connection connection);

  void createCustomer(Connection connection, Customer customer);

  void updateCustomer(Customer customer);

  void deleteCustomer(Long customerId);
}
