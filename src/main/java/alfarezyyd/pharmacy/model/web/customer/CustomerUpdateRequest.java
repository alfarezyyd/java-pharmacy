package alfarezyyd.pharmacy.model.web.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerUpdateRequest {
  @NotBlank
  private Long id;
  @JsonProperty("full_name")
  @NotBlank
  @Size(max = 255)
  private String fullName;
  @JsonProperty("date_of_birth")
  @NotBlank
  private String dateOfBirth;
  @NotBlank

  private String gender;
  @NotBlank

  private String phone;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return "CustomerUpdateRequest{" +
        "id=" + id +
        ", fullName='" + fullName + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", gender='" + gender + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }
}
