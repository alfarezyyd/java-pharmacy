package alfarezyyd.pharmacy.model.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreateRequest {
  @JsonProperty("employee_id")
  @NotNull
  private Long employeeId;
  @NotBlank
  @Size(max = 255)
  private String username;
  @NotBlank
  @Email
  @Size(max = 255)
  private String email;
  @NotBlank
  @Size(max = 100)
  private String password;


  public Long getEmployeeId() {
    return employeeId;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
