package alfarezyyd.pharmacy.model.entity;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
  MAN("Man"), WOMAN("Woman");
  private final String value;
  private static final Map<String, Gender> mappingGender = new HashMap<>();

  static {
    for (Gender gender : values()) {
      mappingGender.put(gender.value, gender);
    }
  }

  Gender(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Gender fromValue(String value) {
    return mappingGender.get(value);
  }

}
