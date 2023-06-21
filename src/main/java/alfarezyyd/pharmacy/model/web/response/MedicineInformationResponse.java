package alfarezyyd.pharmacy.model.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicineInformationResponse {
  private Float strength;

  private String indications;

  private String contraindications;
  @JsonProperty("side_effects")
  private String sideEffects;

  private String precautions;
  @JsonProperty("storage_conditions")

  private String storageConditions;
  @JsonProperty("expiry_date")

  private String expiryDate;
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

  public void setStrength(Float strength) {
    this.strength = strength;
  }

  public void setIndications(String indications) {
    this.indications = indications;
  }

  public void setContraindications(String contraindications) {
    this.contraindications = contraindications;
  }

  public void setSideEffects(String sideEffects) {
    this.sideEffects = sideEffects;
  }

  public void setPrecautions(String precautions) {
    this.precautions = precautions;
  }

  public void setStorageConditions(String storageConditions) {
    this.storageConditions = storageConditions;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public void setCountryOfOrigin(String countryOfOrigin) {
    this.countryOfOrigin = countryOfOrigin;
  }
}
