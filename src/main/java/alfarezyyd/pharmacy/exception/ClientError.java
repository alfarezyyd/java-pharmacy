package alfarezyyd.pharmacy.exception;

import java.util.HashMap;
import java.util.LinkedList;

public class ClientError {
  private final LinkedList<ValidationError> validationErrors = new LinkedList<>();
  private final LinkedList<ActionError> actionErrors = new LinkedList<>();


  public void addValidationError(String fieldName, String errorMessage) {
    validationErrors.add(new ValidationError(fieldName, errorMessage));
  }


  public void addActionError(String actionName, String errorMessage) {
    actionErrors.add(new ActionError(actionName, errorMessage));
  }


  public Boolean hasErrors() {
    return validationErrors.size() > 0 || actionErrors.size() > 0;
  }

  public LinkedList<ValidationError> getValidationErrors() {
    return validationErrors;
  }

  public LinkedList<ActionError> getActionErrors() {
    return actionErrors;
  }

  public HashMap<String, LinkedList<?>> getClientErrors() {
    HashMap<String, LinkedList<?>> clientErrorsResponse = new HashMap<>();
    clientErrorsResponse.put("validation_errors", getValidationErrors());
    clientErrorsResponse.put("action_errors", getActionErrors());
    return clientErrorsResponse;
  }
}
