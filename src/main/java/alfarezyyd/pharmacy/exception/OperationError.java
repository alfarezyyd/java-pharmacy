package alfarezyyd.pharmacy.exception;

public class OperationError extends Exception {
  private final String operation;
  private final String errorMessage;

  public OperationError(String operation, String errorMessage) {
    this.operation = operation;
    this.errorMessage = errorMessage;
  }


  @Override
  public Throwable fillInStackTrace() {
    return null;
  }

  public String getOperation() {
    return operation;
  }


  public String getErrorMessage() {
    return errorMessage;
  }
}
