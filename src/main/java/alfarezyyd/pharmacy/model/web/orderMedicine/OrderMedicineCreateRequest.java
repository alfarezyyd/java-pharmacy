package alfarezyyd.pharmacy.model.web.orderMedicine;

import alfarezyyd.pharmacy.constraint.CheckSizeOfMultipleValueConstraint;
import alfarezyyd.pharmacy.constraint.ValidMedicineIdConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedList;

@CheckSizeOfMultipleValueConstraint
public class OrderMedicineCreateRequest {
  @NotNull
  @JsonProperty("all_medicine_id")
  @ValidMedicineIdConstraint
  private LinkedList<Long> allMedicineId;
  @NotNull
  @JsonProperty("all_quantity")
  private LinkedList<Long> allQuantity;
  @NotNull
  @JsonProperty("all_total_price")
  private LinkedList<Long> allTotalPrice;

  public LinkedList<Long> getAllMedicineId() {
    return allMedicineId;
  }

  public LinkedList<Long> getAllQuantity() {
    return allQuantity;
  }

  public LinkedList<Long> getAllTotalPrice() {
    return allTotalPrice;
  }
}
