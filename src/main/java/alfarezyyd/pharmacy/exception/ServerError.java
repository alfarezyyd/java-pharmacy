package alfarezyyd.pharmacy.exception;

import java.util.HashMap;
import java.util.LinkedList;

public class ServerError {
  private final LinkedList<DatabaseError> databaseErrors = new LinkedList<>();
  private final LinkedList<OperationError> operationErrors = new LinkedList<>();
  public void addDatabaseError(String errorReason, Integer errorCode, String SQLState) {
    databaseErrors.add(new DatabaseError(errorReason, errorCode, SQLState));
  }

  public void addOperationError(String operation, String errorMessage){
    operationErrors.add(new OperationError(operation, errorMessage));
  }

  public Boolean hasErrors() {
    return databaseErrors.size() > 0 || operationErrors.size() > 0;
  }

  public LinkedList<DatabaseError> getDatabaseErrors() {
    return databaseErrors;
  }

  public LinkedList<OperationError> getOperationErrors() {
    return operationErrors;
  }

  public HashMap<String, LinkedList<?>> getServerErrors(){
    HashMap<String, LinkedList<?>> serverErrorsResponse = new HashMap<>();
    serverErrorsResponse.put("database_errors", getDatabaseErrors());
    serverErrorsResponse.put("operation_errors", getOperationErrors());
    return serverErrorsResponse;
  }

}
