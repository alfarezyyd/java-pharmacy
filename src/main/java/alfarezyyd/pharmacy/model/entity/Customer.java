package alfarezyyd.pharmacy.model.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class Customer {
  private Long id;
  private String fullName;
  private LocalDate dateOfBirth;
  private Gender gender;
  private String phone;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Timestamp deletedAt;

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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Customer customer = (Customer) o;

    if (!id.equals(customer.id)) return false;
    if (!fullName.equals(customer.fullName)) return false;
    if (!dateOfBirth.equals(customer.dateOfBirth)) return false;
    if (gender != customer.gender) return false;
    if (!phone.equals(customer.phone)) return false;
    if (!Objects.equals(createdAt, customer.createdAt)) return false;
    if (!Objects.equals(updatedAt, customer.updatedAt)) return false;
    return Objects.equals(deletedAt, customer.deletedAt);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + fullName.hashCode();
    result = 31 * result + dateOfBirth.hashCode();
    result = 31 * result + gender.hashCode();
    result = 31 * result + phone.hashCode();
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", fullName='" + fullName + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", gender=" + gender +
        ", phone='" + phone + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", deletedAt=" + deletedAt +
        '}';
  }
}
