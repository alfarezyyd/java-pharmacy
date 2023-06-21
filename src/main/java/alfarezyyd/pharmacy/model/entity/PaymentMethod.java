package alfarezyyd.pharmacy.model.entity;

public enum PaymentMethod {
  CREDIT_CARD("Credit Card"), TRANSFER("Transfer"), COD("COD");
  private final String value;

  PaymentMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
