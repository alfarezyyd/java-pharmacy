package alfarezyyd.pharmacy.model.entity;

public enum PaymentStatus {
  PAID("Paid"), NOT_PAID_YET("Not Paid Yet");
  private final String value;

  PaymentStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
