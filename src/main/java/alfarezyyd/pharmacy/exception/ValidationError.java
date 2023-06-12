package alfarezyyd.pharmacy.exception;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ValidationError {
  @JsonProperty("field_name")
  private String fieldName;
  @JsonProperty("error_message")

  private String errorMessage;

  public ValidationError(String fieldName, String errorMessage) {
    this.fieldName = fieldName;
    this.errorMessage = errorMessage;
  }
}
