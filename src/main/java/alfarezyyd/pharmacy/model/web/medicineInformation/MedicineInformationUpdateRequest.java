package alfarezyyd.pharmacy.model.web.medicineInformation;

import alfarezyyd.pharmacy.constraint.ValidDateConstraint;
import alfarezyyd.pharmacy.constraint.ValidDosageFormConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicineInformationUpdateRequest {
  @NotNull
  @ValidDosageFormConstraint
  @JsonProperty("dosage_form")
  private String dosageForm;
  @NotNull
  private Float strength;
  @NotBlank
  private String indications;
  @NotBlank
  private String contraindications;
  @NotBlank
  @JsonProperty("side_effects")
  private String sideEffects;
  @NotBlank

  private String precautions;
  @NotBlank
  @JsonProperty("storage_conditions")
  private String storageConditions;
  @NotBlank
  private String description;
  @ValidDateConstraint
  @NotBlank
  @JsonProperty("expiry_date")
  private String expiryDate;
  @NotBlank
  @JsonProperty("country_of_origin")
  private String countryOfOrigin;



  public Float getStrength() {
    return strength;
  }

  public String getIndications() {
    return indications;
  }

  public String getContraindications() {
    return contraindications;
  }

  public String getSideEffects() {
    return sideEffects;
  }

  public String getPrecautions() {
    return precautions;
  }

  public String getStorageConditions() {
    return storageConditions;
  }


  public String getExpiryDate() {
    return expiryDate;
  }

  public String getCountryOfOrigin() {
    return countryOfOrigin;
  }

  public String getDosageForm() {
    return dosageForm;
  }

  public String getDescription() {
    return description;
  }
}
