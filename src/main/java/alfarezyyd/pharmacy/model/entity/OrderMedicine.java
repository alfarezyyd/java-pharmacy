package alfarezyyd.pharmacy.model.entity;

import java.util.LinkedList;

public class OrderMedicine {
  private Long orderId;
  private LinkedList<Long> allMedicineId;
  private LinkedList<Long> allQuantity;
  private LinkedList<Long> allTotalPrice;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public LinkedList<Long> getAllMedicineId() {
    return allMedicineId;
  }

  public void setAllMedicineId(LinkedList<Long> allMedicineId) {
    this.allMedicineId = allMedicineId;
  }

  public LinkedList<Long> getAllQuantity() {
    return allQuantity;
  }

  public void setAllQuantity(LinkedList<Long> allQuantity) {
    this.allQuantity = allQuantity;
  }

  public LinkedList<Long> getAllTotalPrice() {
    return allTotalPrice;
  }

  public void setAllTotalPrice(LinkedList<Long> allTotalPrice) {
    this.allTotalPrice = allTotalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrderMedicine that = (OrderMedicine) o;

    if (!orderId.equals(that.orderId)) return false;
    if (!allMedicineId.equals(that.allMedicineId)) return false;
    if (!allQuantity.equals(that.allQuantity)) return false;
    return allTotalPrice.equals(that.allTotalPrice);
  }

  @Override
  public int hashCode() {
    int result = orderId.hashCode();
    result = 31 * result + allMedicineId.hashCode();
    result = 31 * result + allQuantity.hashCode();
    result = 31 * result + allTotalPrice.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "OrderMedicine{" +
        "orderId=" + orderId +
        ", allMedicineId=" + allMedicineId +
        ", allQuantity=" + allQuantity +
        ", allTotalPrice=" + allTotalPrice +
        '}';
  }
}
