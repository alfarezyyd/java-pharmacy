package alfarezyyd.pharmacy.model.entity;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {
  PAID("Paid"), NOT_PAID_YET("Not Paid Yet");
  private final String value;
  private static final Map<String, PaymentStatus> mappingPaymentStatus = new HashMap<>();

  static {
    for (PaymentStatus paymentStatus : values()) {
      mappingPaymentStatus.put(paymentStatus.value, paymentStatus);
    }
  }

  PaymentStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static PaymentStatus fromValue(String value) {
    return mappingPaymentStatus.get(value);
  }
}
