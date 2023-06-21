package alfarezyyd.pharmacy.model.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicineResponse {
  private Long id;
  private String name;
  private String brand;
  private Integer price;
  private Integer stock;
  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("updated_at")
  private String updatedAt;
  @JsonProperty("deleted_at")
  private String deletedAt;
  @JsonProperty("medicine_information")
  private MedicineInformationResponse medicineInformationResponse;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBrand() {
    return brand;
  }

  public Integer getPrice() {
    return price;
  }

  public Integer getStock() {
    return stock;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public String getDeletedAt() {
    return deletedAt;
  }

  public MedicineInformationResponse getMedicineInformationResponse() {
    return medicineInformationResponse;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
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

  public void setMedicineInformationResponse(MedicineInformationResponse medicineInformationResponse) {
    this.medicineInformationResponse = medicineInformationResponse;
  }
}
