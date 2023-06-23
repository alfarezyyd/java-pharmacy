package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.ActionError;
import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.repository.AddressRepository;

import java.sql.*;
import java.util.LinkedList;

public class AddressRepositoryImpl implements AddressRepository {
  @Override
  public Address getAddressById(Connection connection, Long addressId) throws DatabaseError, ActionError {
    String sqlSyntax = """
        SELECT * FROM addresses WHERE id = ?
        """;
    Address address;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, addressId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        address = new Address();
        address.setId(resultSet.getLong("id"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setState(resultSet.getString("state"));
        address.setCountry(resultSet.getString("country"));
        address.setPostalCode(resultSet.getString("postal_code"));
        address.setDefault(resultSet.getBoolean("is_default"));
        address.setDescription(resultSet.getString("description"));
        address.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        address.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
      }else{
        throw new ActionError("find address", "address not found");
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return address;

  }

  @Override
  public LinkedList<Address> getAllAddressByCustomerId(Connection connection, Long customerId) throws DatabaseError {
    String sqlSyntax = """
        SELECT * FROM addresses WHERE customer_id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, customerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      LinkedList<Address> allAddress = new LinkedList<>();
      while (resultSet.next()) {
        Address address = new Address();
        address.setId(resultSet.getLong("id"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setState(resultSet.getString("state"));
        allAddress.add(address);
      }
      return allAddress;
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void createAddress(Connection connection, Address address) throws DatabaseError {
    String sqlSyntax = """
        INSERT INTO addresses(customer_id, street, city, state, country, postal_code, is_default, description) VALUES (?,?,?,?,?,?,?,?)
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setLong(1, address.getCustomerId());
      preparedStatement.setString(2, address.getStreet());
      preparedStatement.setString(3, address.getCity());
      preparedStatement.setString(4, address.getState());
      preparedStatement.setString(5, address.getCountry());
      preparedStatement.setString(6, address.getPostalCode());
      preparedStatement.setBoolean(7, address.getDefault());
      preparedStatement.setString(8, address.getDescription());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateAddress(Connection connection, Address address) throws DatabaseError {
    String sqlSyntax = """
        UPDATE addresses SET street=?, city=?, state=?, country=?, postal_code=?, is_default=?, description=?, updated_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, address.getStreet());
      preparedStatement.setString(2, address.getCity());
      preparedStatement.setString(3, address.getState());
      preparedStatement.setString(4, address.getCountry());
      preparedStatement.setString(5, address.getPostalCode());
      preparedStatement.setBoolean(6, address.getDefault());
      preparedStatement.setString(7, address.getDescription());
      preparedStatement.setTimestamp(8, Timestamp.valueOf(address.getUpdatedAt()));
      preparedStatement.setLong(9, address.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteCustomerAddress(Connection connection, Long addressId, Long customerId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM addresses WHERE id=? AND customer_id=?
        """;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
      preparedStatement.setLong(1, addressId);
      preparedStatement.setLong(2, customerId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
