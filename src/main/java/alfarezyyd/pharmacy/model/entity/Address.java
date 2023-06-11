package alfarezyyd.pharmacy.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Address {
  private Long id;
  private Long customerId;
  private String street;
  private String city;
  private String state;
  private String country;
  private String postalCode;
  private Boolean isDefault;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Boolean getDefault() {
    return isDefault;
  }

  public void setDefault(Boolean aDefault) {
    isDefault = aDefault;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(LocalDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (!id.equals(address.id)) return false;
    if (!street.equals(address.street)) return false;
    if (!city.equals(address.city)) return false;
    if (!state.equals(address.state)) return false;
    if (!country.equals(address.country)) return false;
    if (!postalCode.equals(address.postalCode)) return false;
    if (!isDefault.equals(address.isDefault)) return false;
    if (!description.equals(address.description)) return false;
    if (!createdAt.equals(address.createdAt)) return false;
    if (!Objects.equals(updatedAt, address.updatedAt)) return false;
    return Objects.equals(deletedAt, address.deletedAt);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + street.hashCode();
    result = 31 * result + city.hashCode();
    result = 31 * result + state.hashCode();
    result = 31 * result + country.hashCode();
    result = 31 * result + postalCode.hashCode();
    result = 31 * result + isDefault.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + createdAt.hashCode();
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", country='" + country + '\'' +
        ", postalCode='" + postalCode + '\'' +
        ", isDefault=" + isDefault +
        ", description='" + description + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", deletedAt=" + deletedAt +
        '}';
  }

}
