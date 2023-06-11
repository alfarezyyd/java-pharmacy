package alfarezyyd.pharmacy.model.web.customer;

import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerCreateRequest {
  @Valid
  @JsonAlias
  private AddressCreateRequest address;
  @NotBlank
  @Size(max = 255)
  @JsonProperty("full_name")
  private String fullName;
  @NotBlank
  @JsonProperty("date_of_birth")
  private String dateOfBirth;
  @NotBlank
  private String gender;
  @NotBlank
  private String phone;

  public AddressCreateRequest getAddressCreateRequest() {
    return address;
  }

  public void setAddressCreateRequest(AddressCreateRequest addressCreateRequest) {
    this.address = addressCreateRequest;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "CustomerCreateRequest{" +
        "addressCreateRequest=" + address +
        ", fullName='" + fullName + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", gender='" + gender + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }
}
