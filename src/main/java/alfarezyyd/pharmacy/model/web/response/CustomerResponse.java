package alfarezyyd.pharmacy.model.web.response;

import alfarezyyd.pharmacy.model.entity.option.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;


public class CustomerResponse {
  private Long id;
  @JsonProperty("full_name")
  private String fullName;
  @JsonProperty("date_of_birth")

  private String dateOfBirth;
  private Gender gender;
  private String phone;
  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("updated_at")
  private String updatedAt;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @JsonProperty("addresses")
  private LinkedList<AddressResponse> addressResponses;

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }

  public String getPhone() {
    return phone;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LinkedList<AddressResponse> getAddressResponses() {
    return addressResponses;
  }

  public void setAddressResponses(LinkedList<AddressResponse> addressResponses) {
    this.addressResponses = addressResponses;
  }
}
