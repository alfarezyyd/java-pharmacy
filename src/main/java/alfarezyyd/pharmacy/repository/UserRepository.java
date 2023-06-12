package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.model.entity.User;

import java.sql.Connection;

public interface UserRepository {
  void getUserById(Connection connection, User user);
}
