package alfarezyyd.pharmacy.model.web.response;

import alfarezyyd.pharmacy.model.entity.Gender;


public class CustomerResponse {
  private Long id;
  private AddressResponse addressResponse;
  private String fullName;
  private String dateOfBirth;
  private Gender gender;
  private String phone;
  private String createdAt;
  private String updatedAt;
  private String deletedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AddressResponse getAddressResponse() {
    return addressResponse;
  }

  public void setAddressResponse(AddressResponse addressResponse) {
    this.addressResponse = addressResponse;
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }
}
