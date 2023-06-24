package alfarezyyd.pharmacy.model.entity.option;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
  ON_PROCESS("On Process"), SENT("Sent"), RECEIVED("Received"), CANCELLED("Cancelled");
  private final String value;
  private static final Map<String, OrderStatus> mappingOrderStatus = new HashMap<>();

  static {
    for (OrderStatus orderStatus : values()) {
      mappingOrderStatus.put(orderStatus.value, orderStatus);
    }
  }

  OrderStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static OrderStatus fromValue(String value) {
    return mappingOrderStatus.get(value);
  }
}
