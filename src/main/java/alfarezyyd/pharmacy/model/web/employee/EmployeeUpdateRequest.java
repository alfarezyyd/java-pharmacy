package alfarezyyd.pharmacy.model.web.employee;

import alfarezyyd.pharmacy.constraint.ValidDateConstraint;
import alfarezyyd.pharmacy.constraint.ValidGenderConstraint;
import alfarezyyd.pharmacy.constraint.ValidPositionConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeUpdateRequest {
  @NotNull
  private Long id;
  @NotBlank
  @Size(max = 255)
  @JsonProperty("full_name")
  private String fullName;
  @ValidGenderConstraint
  @NotBlank
  private String gender;
  @NotBlank
  @ValidDateConstraint
  @JsonProperty("hire_date")
  private String hireDate;
  @NotBlank
  @ValidPositionConstraint
  private String position;
  @NotBlank
  @ValidDateConstraint
  @JsonProperty("start_date")
  private String startDate;
  @ValidDateConstraint
  @JsonProperty("end_date")
  private String endDate;

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public String getGender() {
    return gender;
  }

  public String getHireDate() {
    return hireDate;
  }

  public String getPosition() {
    return position;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }
}
