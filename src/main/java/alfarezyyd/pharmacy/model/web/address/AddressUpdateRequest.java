package alfarezyyd.pharmacy.model.web.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressUpdateRequest {
  @NotBlank
  private Long id;
  @NotBlank
  @JsonProperty("customer_id")
  private Long customerId;
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
  @Size(max = 10)
  private String postalCode;

  @JsonProperty("is_default")
  @NotNull
  private Boolean isDefault;
  @NotBlank
  private String description;

  public Long getId() {
    return id;
  }


  public Long getCustomerId() {
    return customerId;
  }

  @NotNull
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
}
