package alfarezyyd.pharmacy.exception;

import java.util.LinkedList;

public class ClientError {
  private static final LinkedList<ValidationError> validationErrors = new LinkedList<>();
  private static final LinkedList<ActionError> actionErrors = new LinkedList<>();


  public static void addValidationError(String fieldName, String errorMessage) {
    validationErrors.add(new ValidationError(fieldName, errorMessage));
  }


  public static void addActionError(String actionName, String errorMessage) {
    actionErrors.add(new ActionError(actionName, errorMessage));
  }


  public static LinkedList<ValidationError> getValidationErrors() {
    return validationErrors;
  }

  public static LinkedList<ActionError> getActionErrors() {
    return actionErrors;
  }

  public static Boolean hasErrors() {
    if (validationErrors.size() > 0 || actionErrors.size() > 0) {
      return true;
    }
    return false;
  }

  public static LinkedList<Object> getAllClientError(){
    LinkedList<Object> allClientError = new LinkedList<>();
    allClientError.add(validationErrors);
    allClientError.add(actionErrors);
    return allClientError;
  }
}
