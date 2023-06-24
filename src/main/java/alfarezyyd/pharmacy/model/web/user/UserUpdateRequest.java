package alfarezyyd.pharmacy.model.web.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserUpdateRequest {
  @NotNull
  private Long id;
  @Size(max = 255)
  @NotBlank
  private String username;
  @Size(max = 255)
  @Email
  @NotBlank
  private String email;
  @NotBlank
  @Size(max = 100)
  private String password;

  public Long getId() {
    return id;
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
