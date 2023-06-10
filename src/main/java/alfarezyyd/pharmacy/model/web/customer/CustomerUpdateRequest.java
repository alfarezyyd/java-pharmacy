package alfarezyyd.pharmacy.model.web.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerUpdateRequest {
  private Long id;
  @JsonProperty("full_name")
  private String fullName;
  @JsonProperty("date_of_birth")
  private String dateOfBirth;
  private String gender;
  private String phone;

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
}
