package alfarezyyd.pharmacy.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActionError extends Exception {
  private final String action;
  @JsonProperty("error_message")
  private String errorMessage;

  public ActionError(String action, String errorMessage) {
    this.action = action;
    this.errorMessage = errorMessage;
  }


  @Override
  public Throwable fillInStackTrace() {
    return null;
  }

  public String getAction() {
    return action;
  }


  public String getErrorMessage() {
    return errorMessage;
  }

}
