package alfarezyyd.pharmacy.exception;

import java.sql.SQLException;

public class DatabaseError extends SQLException {
  private final String message;
  private final Integer errorCode;
  private final String SQLState;

  public DatabaseError(String message, Integer errorCode, String SQLState) {
    this.message = message;
    this.errorCode = errorCode;
    this.SQLState = SQLState;
  }

  @Override
  public Throwable fillInStackTrace() {
    return null;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getErrorCode() {
    return errorCode;
  }

  @Override
  public String toString() {
    return "DatabaseError{" +
        "message='" + message + '\'' +
        ", errorCode=" + errorCode +
        '}';
  }

  @Override
  public String getSQLState() {
    return SQLState;
  }
}

