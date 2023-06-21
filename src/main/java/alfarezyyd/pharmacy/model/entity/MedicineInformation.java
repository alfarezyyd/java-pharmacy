package alfarezyyd.pharmacy.model.entity;

import java.sql.Date;

public class MedicineInformation {
  private Long id;
  private Float strength;
  private String indications;
  private String contraindications;
  private DosageForm dosageForm;
  private String sideEffects;
  private String precautions;
  private String storageConditions;
  private String description;

  private Date expiryDate;
  private String countryOfOrigin;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getStrength() {
    return strength;
  }

  public void setStrength(Float strength) {
    this.strength = strength;
  }

  public String getIndications() {
    return indications;
  }

  public void setIndications(String indications) {
    this.indications = indications;
  }


  public String getContraindications() {
    return contraindications;
  }

  public void setContraindications(String contraindications) {
    this.contraindications = contraindications;
  }
 public DosageForm getDosageForm() {
    return dosageForm;
  }

  public void setDosageForm(DosageForm dosageForm) {
    this.dosageForm = dosageForm;
  }

  public String getSideEffects() {
    return sideEffects;
  }

  public void setSideEffects(String sideEffects) {
    this.sideEffects = sideEffects;
  }

  public String getPrecautions() {
    return precautions;
  }

  public void setPrecautions(String precautions) {
    this.precautions = precautions;
  }

  public String getStorageConditions() {
    return storageConditions;
  }

  public void setStorageConditions(String storageConditions) {
    this.storageConditions = storageConditions;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getCountryOfOrigin() {
    return countryOfOrigin;
  }

  public void setCountryOfOrigin(String countryOfOrigin) {
    this.countryOfOrigin = countryOfOrigin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MedicineInformation that = (MedicineInformation) o;

    if (!id.equals(that.id)) return false;
    if (!strength.equals(that.strength)) return false;
    if (!indications.equals(that.indications)) return false;
    if (!contraindications.equals(that.contraindications)) return false;
    if (dosageForm != that.dosageForm) return false;
    if (!sideEffects.equals(that.sideEffects)) return false;
    if (!precautions.equals(that.precautions)) return false;
    if (!storageConditions.equals(that.storageConditions)) return false;
    if (!description.equals(that.description)) return false;
    if (!expiryDate.equals(that.expiryDate)) return false;
    return countryOfOrigin.equals(that.countryOfOrigin);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + strength.hashCode();
    result = 31 * result + indications.hashCode();
    result = 31 * result + contraindications.hashCode();
    result = 31 * result + dosageForm.hashCode();
    result = 31 * result + sideEffects.hashCode();
    result = 31 * result + precautions.hashCode();
    result = 31 * result + storageConditions.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + expiryDate.hashCode();
    result = 31 * result + countryOfOrigin.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "MedicineInformation{" +
        "id=" + id +
        ", strength=" + strength +
        ", indications='" + indications + '\'' +
        ", contraindications='" + contraindications + '\'' +
        ", dosageForm=" + dosageForm +
        ", sideEffects='" + sideEffects + '\'' +
        ", precautions='" + precautions + '\'' +
        ", storageConditions='" + storageConditions + '\'' +
        ", description='" + description + '\'' +
        ", expiryDate=" + expiryDate +
        ", countryOfOrigin='" + countryOfOrigin + '\'' +
        '}';
  }
}
