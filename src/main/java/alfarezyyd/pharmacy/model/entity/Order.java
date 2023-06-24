package alfarezyyd.pharmacy.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Order  implements Identifiable, HasCustomerId {
  private Long id;
  private Long customerId;
  private Float totalAmount;
  private PaymentMethod paymentMethod;
  private PaymentStatus paymentStatus;
  private OrderStatus orderStatus;
  private ShippingMethod shippingMethod;
  private String trackingNumber;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }



  public Float getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Float totalAmount) {
    this.totalAmount = totalAmount;
  }



  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public ShippingMethod getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(ShippingMethod shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
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

    Order order = (Order) o;

    if (!id.equals(order.id)) return false;
    if (!customerId.equals(order.customerId)) return false;
    if (!totalAmount.equals(order.totalAmount)) return false;
    if (paymentMethod != order.paymentMethod) return false;
    if (paymentStatus != order.paymentStatus) return false;
    if (orderStatus != order.orderStatus) return false;
    if (shippingMethod != order.shippingMethod) return false;
    if (!trackingNumber.equals(order.trackingNumber)) return false;
    if (!createdAt.equals(order.createdAt)) return false;
    return Objects.equals(updatedAt, order.updatedAt);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + customerId.hashCode();
    result = 31 * result + totalAmount.hashCode();
    result = 31 * result + paymentMethod.hashCode();
    result = 31 * result + paymentStatus.hashCode();
    result = 31 * result + orderStatus.hashCode();
    result = 31 * result + shippingMethod.hashCode();
    result = 31 * result + trackingNumber.hashCode();
    result = 31 * result + createdAt.hashCode();
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", totalAmount=" + totalAmount +
        ", paymentMethod=" + paymentMethod +
        ", paymentStatus=" + paymentStatus +
        ", orderStatus=" + orderStatus +
        ", shippingMethod=" + shippingMethod +
        ", trackingNumber='" + trackingNumber + '\'' +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
