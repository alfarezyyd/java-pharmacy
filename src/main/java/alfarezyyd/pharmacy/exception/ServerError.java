package alfarezyyd.pharmacy.exception;

import java.util.HashMap;
import java.util.LinkedList;

public class ServerError {
  private final LinkedList<DatabaseError> databaseErrors = new LinkedList<>();

  public void addDatabaseError(String errorReason, Integer errorCode) {
    databaseErrors.add(new DatabaseError(errorReason, errorCode));
  }

  public Boolean hasErrors() {
    return databaseErrors.size() > 0;
  }

  public LinkedList<DatabaseError> getDatabaseErrors() {
    return databaseErrors;
  }

  public HashMap<String, LinkedList<?>> getServerErrors(){
    HashMap<String, LinkedList<?>> serverErrorsResponse = new HashMap<>();
    serverErrorsResponse.put("database_errors", getDatabaseErrors());
    return serverErrorsResponse;
  }
}
