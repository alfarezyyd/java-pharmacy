package alfarezyyd.pharmacy.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionError extends Throwable {
  private final String action;
  @JsonProperty("error_message")

  private String errorMessage;

  public ActionError(String action, String errorMessage) {
    this.action = action;
    this.errorMessage = errorMessage;
  }

  public String getAction() {
    return action;
  }


  public String getErrorMessage() {
    return errorMessage;
  }


}
