package alfarezyyd.pharmacy.model.web.customer;

import alfarezyyd.pharmacy.constraint.ValidDateConstraint;
import alfarezyyd.pharmacy.constraint.ValidGenderConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerUpdateRequest {
  @NotNull
  private Long id;
  @JsonProperty("full_name")
  @NotBlank
  @Size(max = 255)
  private String fullName;
  @JsonProperty("date_of_birth")
  @NotBlank
  @ValidDateConstraint
  private String dateOfBirth;
  @NotBlank
  @ValidGenderConstraint
  private String gender;
  @NotBlank
  @Size(max = 20)
  private String phone;

  public Long getId() {
    return id;
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
