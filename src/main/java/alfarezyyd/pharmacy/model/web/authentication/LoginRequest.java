package alfarezyyd.pharmacy.model.web.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
  @Email
  @NotBlank
  private String email;
  @NotBlank
  @Size(max = 100)
  private String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
