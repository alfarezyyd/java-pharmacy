package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.User;

import java.sql.Connection;
import java.util.LinkedList;

public interface UserRepository {
  LinkedList<User> getAllUser(Connection connection) throws DatabaseError;

  void createUser(Connection connection, User user) throws DatabaseError;

  void updateUser(Connection connection, User user) throws DatabaseError;

  void deleteUser(Connection connection, Long userId) throws DatabaseError;
  void updateEmailUser(Connection connection, String email, Long userId) throws DatabaseError;
}
