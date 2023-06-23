package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
  public static void commitOrRollback(ServerError serverError, ClientError clientError, Connection connection) throws SQLException {
    if (serverError.hasErrors() || clientError.hasErrors()) {
      connection.rollback();
    } else {
      connection.commit();
    }
  }
}
