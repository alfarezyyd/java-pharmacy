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

  public void setId(Long id) {
    this.id = id;
  }

  public void setAddressResponse(AddressResponse addressResponse) {
    this.addressResponse = addressResponse;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }
}
