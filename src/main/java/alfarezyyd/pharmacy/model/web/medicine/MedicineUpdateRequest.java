package alfarezyyd.pharmacy.model.web.medicine;

import alfarezyyd.pharmacy.model.web.medicineInformation.MedicineInformationUpdateRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicineUpdateRequest {
  @Valid
  @JsonProperty("medicine_information")
  private MedicineInformationUpdateRequest medicineInformation;
  @NotNull
  private Long id;
  @NotBlank
  @JsonProperty("full_name")
  private String fullName;
  @NotBlank
  private String brand;
  @NotNull
  private Integer price;
  @NotNull
  private Integer stock;

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
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

  public MedicineInformationUpdateRequest getMedicineInformationCreateRequest() {
    return medicineInformation;
  }
}
