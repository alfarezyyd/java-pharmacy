package alfarezyyd.pharmacy.exception;

import java.util.LinkedList;

public class ServerError {
  private static final LinkedList<DatabaseError> databaseErrors = new LinkedList<>();

  public static void addDatabaseError(String errorReason, Integer errorCode) {
    databaseErrors.add(new DatabaseError(errorReason, errorCode));
  }

  public static LinkedList<DatabaseError> getDatabaseErrors() {
    return databaseErrors;
  }

  public static Boolean hasErrors() {
    if (databaseErrors.size() > 0) {
      return true;
    }
    return false;
  }


  public static LinkedList<Object> getAllServerError() {
    LinkedList<Object> allClientError = new LinkedList<>();
    allClientError.add(databaseErrors);
    return allClientError;
  }
}
