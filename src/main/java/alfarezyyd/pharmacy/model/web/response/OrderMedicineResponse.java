package alfarezyyd.pharmacy.model.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

public class OrderMedicineResponse {
  @JsonProperty("order_id")
  private Long orderId;
  @JsonProperty("all_medicine_id")
  private LinkedList<Long> allMedicineId;
  @JsonProperty("all_quantity")
  private LinkedList<Long> allQuantity;
  @JsonProperty("all_total_price")
  private LinkedList<Long> allTotalPrice;

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public void setAllMedicineId(LinkedList<Long> allMedicineId) {
    this.allMedicineId = allMedicineId;
  }

  public void setAllQuantity(LinkedList<Long> allQuantity) {
    this.allQuantity = allQuantity;
  }

  public void setAllTotalPrice(LinkedList<Long> allTotalPrice) {
    this.allTotalPrice = allTotalPrice;
  }
}
