package alfarezyyd.pharmacy.model.entity;

import alfarezyyd.pharmacy.model.entity.trait.Identifiable;

import java.sql.Timestamp;
import java.util.Objects;

public class Medicine implements Identifiable {
  private Long id;
  private String fullName;
  private String brand;
  private Integer price;
  private Integer stock;
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



  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
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

    Medicine medicine = (Medicine) o;

    if (!id.equals(medicine.id)) return false;
    if (!fullName.equals(medicine.fullName)) return false;
    if (!brand.equals(medicine.brand)) return false;
    if (!price.equals(medicine.price)) return false;
    if (!stock.equals(medicine.stock)) return false;
    if (!createdAt.equals(medicine.createdAt)) return false;
    return Objects.equals(updatedAt, medicine.updatedAt);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + fullName.hashCode();
    result = 31 * result + brand.hashCode();
    result = 31 * result + price.hashCode();
    result = 31 * result + stock.hashCode();
    result = 31 * result + createdAt.hashCode();
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    return result;
  }
}
