package alfarezyyd.pharmacy.model.web.medicine;

import alfarezyyd.pharmacy.model.web.medicineInformation.MedicineInformationCreateRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicineCreateRequest {
  @Valid
  @JsonProperty("medicine_information")
  private MedicineInformationCreateRequest medicineInformation;
  @NotBlank
  private String name;
  @NotBlank
  private String brand;
  @NotNull
  private Integer price;
  @NotNull
  private Integer stock;

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

  public MedicineInformationCreateRequest getMedicineInformationCreateRequest() {
    return medicineInformation;
  }
}
