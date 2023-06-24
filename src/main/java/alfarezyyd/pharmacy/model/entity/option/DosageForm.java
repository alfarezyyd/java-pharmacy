package alfarezyyd.pharmacy.model.entity.option;

import java.util.HashMap;
import java.util.Map;

public enum DosageForm {
  TABLET("Tablet"), CAPSULE("Capsule"), CAPLET("Caplet"), PILL("Pill"), POWDER("Powder"), SUPPOSITORIA("Suppositoria"), RUB("Rub"), LIQUID("Liquid"), SUSPENSION("Suspension"), INJECTION("Injection"), DROP("Drop"), INHALER("Inhaler");
  private final String value;
  private static final Map<String, DosageForm> mappingDosageForm = new HashMap<>();

  static {
    for (DosageForm dosageForm : values()) {
      mappingDosageForm.put(dosageForm.value, dosageForm);
    }
  }

  DosageForm(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static DosageForm fromValue(String value) {
    return mappingDosageForm.get(value);
  }
}
