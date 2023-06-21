package alfarezyyd.pharmacy.model.web.response;

import alfarezyyd.pharmacy.model.entity.PaymentMethod;


public class OrderResponse {
  private Long id;
  private Long customerId;
  private Float totalAmount;
  private PaymentMethod paymentMethod;
  private String paymentStatus;
  private String orderStatus;
  private String shippingMethod;
  private String trackingNumber;
  private String createdAt;
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

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
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

  public void setPaymentMethod(PaymentMethod paymentMethod) {
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
