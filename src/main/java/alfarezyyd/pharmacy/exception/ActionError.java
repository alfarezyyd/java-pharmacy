package alfarezyyd.pharmacy.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionError {
  private String action;
  @JsonProperty("error_message")

  private String errorMessage;

  public ActionError(String action, String errorMessage) {
    this.action = action;
    this.errorMessage = errorMessage;
  }

  public String getFieldName() {
    return action;
  }

  public void setFieldName(String action) {
    this.action = action;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
