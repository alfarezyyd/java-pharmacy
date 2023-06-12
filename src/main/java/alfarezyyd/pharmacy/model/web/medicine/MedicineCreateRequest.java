package alfarezyyd.pharmacy.model.web.medicine;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicineCreateRequest {
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotBlank
  private String brand;
  @NotNull
  private Integer price;
  @NotNull
  private Integer stock;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
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
}
