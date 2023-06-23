package alfarezyyd.pharmacy.model.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderResponse {
  private Long id;
  @JsonProperty("customer_id")
  private Long customerId;
  @JsonProperty("total_amount")
  private Float totalAmount;
  @JsonProperty("payment_method")
  private String paymentMethod;
  @JsonProperty("payment_status")
  private String paymentStatus;
  @JsonProperty("order_status")

  private String orderStatus;
  @JsonProperty("shipping_method")
  private String shippingMethod;
  @JsonProperty("tracking_number")
  private String trackingNumber;
  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("updated_at")
  private String updatedAt;

  public Long getId() {
    return id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Float getTotalAmount() {
    return totalAmount;
  }


  public String getPaymentStatus() {
    return paymentStatus;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public String getShippingMethod() {
    return shippingMethod;
  }

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public void setTotalAmount(Float totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

}
