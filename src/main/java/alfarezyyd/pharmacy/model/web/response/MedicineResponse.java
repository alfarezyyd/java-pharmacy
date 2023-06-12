package alfarezyyd.pharmacy.model.web.response;

public class MedicineResponse {
  private Long id;
  private String name;
  private String description;
  private String brand;
  private Integer price;
  private Integer stock;
  private String createdAt;
  private String updatedAt;
  private String deletedAt;

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
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
}
