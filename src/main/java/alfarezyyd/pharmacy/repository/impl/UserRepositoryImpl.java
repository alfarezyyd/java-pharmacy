package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.User;
import alfarezyyd.pharmacy.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserRepositoryImpl implements UserRepository {
  @Override
  public LinkedList<User> getAllUser(Connection connection) throws DatabaseError {
    LinkedList<User> allUser = new LinkedList<>();
    String sqlSyntax = """
        SELECT * FROM users
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
         ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmployeeId(resultSet.getLong("employee_id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setLastLogin(resultSet.getTimestamp("last_login"));
        user.setCreatedAt(resultSet.getTimestamp("created_at"));
        user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        allUser.add(user);
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allUser;
  }

  @Override
  public void createUser(Connection connection, User user) throws DatabaseError {
    String sqlSyntax = """
        INSERT INTO users(employee_id, username, email, password) VALUES(?,?,?,?)
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, user.getEmployeeId());
      preparedStatement.setString(2, user.getUsername());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getPassword());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateUser(Connection connection, User user) throws DatabaseError {
    String sqlSyntax = """
        UPDATE users SET username=?, password=?, last_login=?, updated_at=? WHERE id=?
        """;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
      preparedStatement.setString(1, user.getUsername());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setTimestamp(3, user.getLastLogin());
      preparedStatement.setTimestamp(4, user.getUpdatedAt());
      preparedStatement.setLong(5, user.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteUser(Connection connection, Long userId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM users WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, userId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateEmailUser(Connection connection, String email, Long userId) throws DatabaseError {
    String sqlSyntax = """
        UPDATE users SET email=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, email);
      preparedStatement.setLong(2, userId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
