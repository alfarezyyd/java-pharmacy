package alfarezyyd.pharmacy.model.entity;

public enum ShippingMethod {
  REGULAR("Regular"), EXPRESS("Express"), ONE_DAY("One Day");
  private final String value;

  ShippingMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
