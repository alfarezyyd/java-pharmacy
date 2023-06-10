package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.repository.AddressRepository;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {
  @Override
  public Address getAddressById(Connection connection, Long addressId) {
    String sqlSyntax = """
        SELECT * FROM addresses WHERE id = ?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
         ResultSet resultSet = preparedStatement.executeQuery()) {
      preparedStatement.setLong(1, addressId);
      Address address = new Address();
      if (resultSet.next()) {
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
        address.setDeletedAt(resultSet.getTimestamp("deleted_at").toLocalDateTime());
        connection.close();
        return address;
      } else {
        connection.close();
        return address;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public Long createAddress(Connection connection, Address address) {
    String sqlSyntax = """
        INSERT INTO addresses(street, city, state, country, postal_code, is_default, description) VALUES (?,?,?,?,?,?,?)
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, address.getStreet());
      preparedStatement.setString(2, address.getCity());
      preparedStatement.setString(3, address.getState());
      preparedStatement.setString(4, address.getCountry());
      preparedStatement.setString(5, address.getPostalCode());
      preparedStatement.setBoolean(6, address.getDefault());
      preparedStatement.setString(7, address.getDescription());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      Long generatedKeys = 0L;
      if (resultSet.next()) {
         generatedKeys = resultSet.getLong(1);
      }
      resultSet.close();
      connection.close();
      return generatedKeys;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void updateAddress(Connection connection, Address address) {
    String sqlSyntax = """
        UPDATE addresses SET street=?, city=?, state=?, country=?, postal_code=?, is_default=?, description=?, updated_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);) {
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
      throw new RuntimeException(e);
    }
  }

  @Override
  public void softDeleteAddress(Connection connection, Long addressId) {
    String sqlSyntax = """
        UPDATE addresses SET deleted_at=? WHERE id=?
        """;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
      preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
      preparedStatement.setLong(2, addressId);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void permanentlyDeleteAddress(Connection connection, Long addressId) {
    String sqlSyntax = """
        DELETE FROM addresses WHERE id=?
        """;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
      preparedStatement.setLong(1, addressId);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
