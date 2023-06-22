package alfarezyyd.pharmacy.model.entity;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethod {
  CREDIT_CARD("Credit Card"), TRANSFER("Transfer"), COD("COD");
  private final String value;
  private static final Map<String, PaymentMethod> mappingPaymentMethod = new HashMap<>();

  static {
    for (PaymentMethod paymentMethod : values()) {
      mappingPaymentMethod.put(paymentMethod.value, paymentMethod);
    }
  }

  PaymentMethod(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static PaymentMethod fromValue(String value) {
    return mappingPaymentMethod.get(value);
  }
}
