package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.repository.CustomerRepository;

import java.sql.*;
import java.util.LinkedList;

public class CustomerRepositoryImpl implements CustomerRepository {
  @Override
  public LinkedList<Customer> getAllCustomer(Connection connection) {
    String sqlSyntax = """
         SELECT * FROM customers;
        """;
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sqlSyntax)) {
      LinkedList<Customer> allCustomer = new LinkedList<>();
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setGender(Gender.valueOf(resultSet.getString("gender")));
        allCustomer.add(customer);
      }
      connection.close();
      return allCustomer;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void createCustomer(Connection connection, Customer customer) {
    String sqlSyntax = "INSERT INTO customers(address_id, full_name, date_of_birth, gender, phone) VALUES (?,?,?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, customer.getAddressId());
      preparedStatement.setString(2, customer.getFullName());
      preparedStatement.setDate(3, Date.valueOf(customer.getDateOfBirth()));
      preparedStatement.setString(4, customer.getGender().toString());
      preparedStatement.setString(5, customer.getPhone());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void updateCustomer(Customer customer) {

  }

  @Override
  public void deleteCustomer(Long customerId) {

  }
}
