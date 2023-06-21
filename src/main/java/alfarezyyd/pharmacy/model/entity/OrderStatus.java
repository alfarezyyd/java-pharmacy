package alfarezyyd.pharmacy.model.entity;

public enum OrderStatus {
  ON_PROCESS("On Process"), SENT("Sent"), ACCEPTED("Received"), CANCELLED("Cancelled");
  private final String value;

  OrderStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
