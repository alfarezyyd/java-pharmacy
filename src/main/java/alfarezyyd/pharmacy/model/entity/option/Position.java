package alfarezyyd.pharmacy.model.entity.option;

import java.util.HashMap;
import java.util.Map;

public enum Position {
  PROGRAMMER("Programmer"), PHARMACISTS("Pharmacists");
  private final String value;
  private static final Map<String, Position> mappingPosition = new HashMap<>();

  Position(String value) {
    this.value = value;
  }

  static {
    for (Position position : values()) {
      mappingPosition.put(position.value, position);
    }
  }

  public String getValue() {
    return value;
  }

  public static Position fromValue(String value) {
    return mappingPosition.get(value);
  }

}
