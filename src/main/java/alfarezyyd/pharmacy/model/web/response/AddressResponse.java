package alfarezyyd.pharmacy.model.web.response;


public class AddressResponse {
  private Long id;
  private String street;
  private String city;
  private String state;
  private String country;
  private String postalCode;
  private Boolean isDefault;
  private String description;
  private String createdAt;
  private String updatedAt;
  private String deletedAt;

  public Long getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public Boolean getDefault() {
    return isDefault;
  }

  public String getDescription() {
    return description;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public void setStreet(String street) {
    this.street = street;
  }


  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setCountry(String country) {
    this.country = country;
  }


  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public void setDefault(Boolean aDefault) {
    isDefault = aDefault;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }


  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }

}
