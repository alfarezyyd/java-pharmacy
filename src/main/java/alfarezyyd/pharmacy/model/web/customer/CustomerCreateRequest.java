package alfarezyyd.pharmacy.model.web.customer;

import alfarezyyd.pharmacy.constraint.ValidDateConstraint;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerCreateRequest {
  @Valid
  @JsonProperty("address")
  private AddressCreateRequest addressCreateRequest;
  @NotBlank
  @Size(max = 255)
  @JsonProperty("full_name")
  private String fullName;
  @NotBlank
  @JsonProperty("date_of_birth")
  @ValidDateConstraint
  private String dateOfBirth;
  @NotBlank
  private String gender;
  @NotBlank
  private String phone;

  public AddressCreateRequest getAddressCreateRequest() {
    return addressCreateRequest;
  }


  public String getFullName() {
    return fullName;
  }


  public String getDateOfBirth() {
    return dateOfBirth;
  }


  public String getGender() {
    return gender;
  }


  public String getPhone() {
    return phone;
  }
}
