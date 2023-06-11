package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.repository.CustomerRepository;

import java.sql.*;
import java.util.LinkedList;

public class CustomerRepositoryImpl implements CustomerRepository {
  @Override
  public Customer getCustomerById(Connection connection, Long customerId) {
    String sqlSyntax = """
        SELECT * FROM customers WHERE id = ?
        """;
    Customer customer = new Customer();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, customerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customer.setId(resultSet.getLong("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setGender(Gender.valueOf(resultSet.getString("gender")));
        customer.setPhone(resultSet.getString("phone"));
        customer.setCreatedAt(resultSet.getTimestamp("created_at"));
        customer.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        customer.setDeletedAt(resultSet.getTimestamp("deleted_at"));
      } else {
        ClientError.addActionError("find customer", "customer not found");
      }
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return customer;
  }

  @Override
  public LinkedList<Customer> getAllCustomer(Connection connection) {
    String sqlSyntax = """
         SELECT * FROM customers WHERE deleted_at = NULL;
        """;
    LinkedList<Customer> allCustomer = new LinkedList<>();
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sqlSyntax)) {
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setGender(Gender.valueOf(resultSet.getString("gender")));
        allCustomer.add(customer);
      }
      connection.close();
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return allCustomer;

  }

  @Override
  public LinkedList<Customer> getAllDeletedCustomer(Connection connection) {
    String sqlSyntax = """
        SELECT * FROM customers WHERE deleted_at != NULL;
        """;
    LinkedList<Customer> allDeletedCustomer = new LinkedList<>();
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(sqlSyntax);
      while (resultSet.next()) {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        customer.setGender(Gender.valueOf(resultSet.getString("gender")));
        allDeletedCustomer.add(customer);
      }
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
    return allDeletedCustomer;

  }

  @Override
  public Long createCustomer(Connection connection, Customer customer) {
    String sqlSyntax = "INSERT INTO customers(full_name, date_of_birth, gender, phone) VALUES (?,?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS)) {
      long customerId = 0L;
      preparedStatement.setString(1, customer.getFullName());
      preparedStatement.setDate(2, Date.valueOf(customer.getDateOfBirth()));
      preparedStatement.setString(3, customer.getGender().toString());
      preparedStatement.setString(4, customer.getPhone());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        customerId = resultSet.getLong(1);
      } else {
        ClientError.addActionError("create customer", "create customer failed");
      }
      connection.close();
      return customerId;
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
      return 0L;
    }
  }

  @Override
  public void updateCustomer(Connection connection, Customer customer) {
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
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void softDeleteCustomer(Connection connection, Customer customer) {
    String sqlSyntax = """
        UPDATE customers SET deleted_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setTimestamp(1, customer.getDeletedAt());
      preparedStatement.setLong(2, customer.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }

  @Override
  public void permanentlyDeleteCustomer(Connection connection, Long customerId) {
    String sqlSyntax = """
        DELETE FROM customers WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, customerId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      ServerError.addDatabaseError(e.getMessage(), e.getErrorCode());
    }
  }
}
