package alfarezyyd.pharmacy.model.entity;

import java.util.HashMap;
import java.util.Map;

public enum ShippingMethod {
  REGULAR("Regular"), EXPRESS("Express"), ONE_DAY("One Day");
  private final String value;
  private static final Map<String, ShippingMethod> mappingShippingMethod = new HashMap<>();

  static {
    for (ShippingMethod shippingMethod : values()) {
      mappingShippingMethod.put(shippingMethod.value, shippingMethod);
    }
  }

  ShippingMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static ShippingMethod fromValue(String value) {
    return mappingShippingMethod.get(value);
  }

}

