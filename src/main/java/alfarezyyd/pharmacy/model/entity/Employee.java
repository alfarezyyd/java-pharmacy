package alfarezyyd.pharmacy.model.entity;

import alfarezyyd.pharmacy.model.entity.option.Gender;
import alfarezyyd.pharmacy.model.entity.option.Position;
import alfarezyyd.pharmacy.model.entity.trait.Identifiable;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Employee implements Identifiable {
  private Long id;
  private String fullName;
  private Gender gender;
  private Date hireDate;
  private Position position;
  private Date startDate;
  private Date endDate;
  private Timestamp createdAt;
  private Timestamp updatedAt;

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

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Employee employee = (Employee) o;

    if (!id.equals(employee.id)) return false;
    if (!fullName.equals(employee.fullName)) return false;
    if (gender != employee.gender) return false;
    if (!hireDate.equals(employee.hireDate)) return false;
    if (!position.equals(employee.position)) return false;
    if (!startDate.equals(employee.startDate)) return false;
    if (!Objects.equals(endDate, employee.endDate)) return false;
    if (!createdAt.equals(employee.createdAt)) return false;
    return Objects.equals(updatedAt, employee.updatedAt);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + fullName.hashCode();
    result = 31 * result + gender.hashCode();
    result = 31 * result + hireDate.hashCode();
    result = 31 * result + position.hashCode();
    result = 31 * result + startDate.hashCode();
    result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
    result = 31 * result + createdAt.hashCode();
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + fullName + '\'' +
        ", gender=" + gender +
        ", hireDate=" + hireDate +
        ", position='" + position + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
