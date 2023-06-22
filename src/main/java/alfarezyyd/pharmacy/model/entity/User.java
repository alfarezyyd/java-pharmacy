package alfarezyyd.pharmacy.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User implements Identifiable {
  private Long id;
  private Long customerId;
  private String username;
  private String email;
  private String password;
  private Timestamp lastLogin;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Timestamp deletedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Timestamp getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Timestamp lastLogin) {
    this.lastLogin = lastLogin;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Timestamp getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", lastLogin=" + lastLogin +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", deletedAt=" + deletedAt +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (!id.equals(user.id)) return false;
    if (!customerId.equals(user.customerId)) return false;
    if (!username.equals(user.username)) return false;
    if (!email.equals(user.email)) return false;
    if (!password.equals(user.password)) return false;
    if (!Objects.equals(lastLogin, user.lastLogin)) return false;
    if (!createdAt.equals(user.createdAt)) return false;
    if (!Objects.equals(updatedAt, user.updatedAt)) return false;
    return Objects.equals(deletedAt, user.deletedAt);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + customerId.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
    result = 31 * result + createdAt.hashCode();
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
    return result;
  }
}
