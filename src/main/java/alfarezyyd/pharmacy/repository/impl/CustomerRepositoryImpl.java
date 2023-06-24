package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.repository.CustomerRepository;

import java.sql.*;
import java.util.LinkedList;

public class CustomerRepositoryImpl implements CustomerRepository {

  @Override
  public LinkedList<Customer> getAllCustomer(Connection connection) throws DatabaseError {
    String sqlSyntax = """
         SELECT * FROM customers;
        """;
    LinkedList<Customer> allCustomer = new LinkedList<>();

    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
         ResultSet resultSet = preparedStatement.executeQuery(sqlSyntax)) {
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setPhone(resultSet.getString("phone"));
        customer.setGender(Gender.fromValue(resultSet.getString("gender")));
        customer.setCreatedAt(resultSet.getTimestamp("created_at"));
        customer.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        allCustomer.add(customer);
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allCustomer;

  }

  @Override
  public Long createCustomer(Connection connection, Customer customer) throws DatabaseError, ActionError {
    String sqlSyntax = """
        INSERT INTO customers(full_name, date_of_birth, gender, phone) VALUES (?,?,?,?)""";
    long customerId;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, customer.getFullName());
      preparedStatement.setDate(2, Date.valueOf(customer.getDateOfBirth()));
      preparedStatement.setString(3, customer.getGender().toString());
      preparedStatement.setString(4, customer.getPhone());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        customerId = resultSet.getLong(1);
      } else {
        resultSet.close();
        throw new ActionError("create customer", "create customer failed");
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return customerId;
  }

  @Override
  public void updateCustomer(Connection connection, Customer customer) throws DatabaseError {
    String sqlSyntax = """
        UPDATE customers SET full_name=?, date_of_birth=?, gender=?, phone=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, customer.getFullName());
      preparedStatement.setDate(2, Date.valueOf(customer.getDateOfBirth()));
      preparedStatement.setString(3, customer.getGender().toString());
      preparedStatement.setString(4, customer.getPhone());
      preparedStatement.setLong(5, customer.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteCustomer(Connection connection, Long customerId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM customers WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, customerId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
