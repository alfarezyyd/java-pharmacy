package alfarezyyd.pharmacy.model.web.orderMedicine;

import alfarezyyd.pharmacy.constraint.CheckSizeOfMultipleValueConstraint;
import alfarezyyd.pharmacy.constraint.ValidMedicineIdConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedList;

@CheckSizeOfMultipleValueConstraint
public class OrderMedicineUpdateRequest {

  @NotNull
  @JsonProperty("order_id")
  private Long orderId;
  @NotNull
  @JsonProperty("all_medicine_id")
  @ValidMedicineIdConstraint
  private LinkedList<Long> allMedicineId;
  @NotNull
  @JsonProperty("all_quantity")
  private LinkedList<Long> allQuantity;
  @NotNull
  private LinkedList<Long> allTotalPrice;

  public Long getOrderId() {
    return orderId;
  }

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
