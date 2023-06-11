package alfarezyyd.pharmacy.model.web.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressUpdateRequest {
  @NotBlank
  private Long id;
  @NotBlank
  @Size(max = 255)
  private String street;
  @NotBlank
  @Size(max = 255)
  private String city;
  @NotBlank
  @Size(max = 255)
  private String state;
  @NotBlank
  @Size(max = 255)
  private String country;
  @JsonProperty("postal_code")
  @NotBlank
  @Size(max = 255)
  private String postalCode;

  @JsonProperty("is_default")
  @NotNull
  private Boolean isDefault;
  @NotBlank
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
