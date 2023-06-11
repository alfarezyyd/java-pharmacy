package alfarezyyd.pharmacy.exception;

public class DatabaseError {
  private String errorReason;
  private Integer errorCode;

  public DatabaseError(String errorReason, Integer errorCode) {
    this.errorReason = errorReason;
    this.errorCode = errorCode;
  }

  public String getErrorReason() {
    return errorReason;
  }

  public void setErrorReason(String errorReason) {
    this.errorReason = errorReason;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }
}

