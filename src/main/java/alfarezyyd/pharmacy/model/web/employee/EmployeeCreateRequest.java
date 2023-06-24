package alfarezyyd.pharmacy.model.web.employee;

import alfarezyyd.pharmacy.constraint.ValidDateConstraint;
import alfarezyyd.pharmacy.constraint.ValidGenderConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeCreateRequest {
  @NotBlank
  @Size(max = 255)
  private String name;
  @ValidGenderConstraint
  @NotBlank
  private String gender;
  @NotBlank
  @ValidDateConstraint
  @JsonProperty("hire_date")
  private String hireDate;
  @NotBlank
  @Size(max = 100)
  private String position;
  @NotBlank
  @ValidDateConstraint
  @JsonProperty("start_date")
  private String startDate;
  @ValidDateConstraint
  @JsonProperty("end_date")
  private String endDate;

  public String getName() {
    return name;
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
