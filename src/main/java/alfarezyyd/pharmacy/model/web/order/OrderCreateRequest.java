package alfarezyyd.pharmacy.model.web.order;

import alfarezyyd.pharmacy.constraint.ValidOrderStatusConstraint;
import alfarezyyd.pharmacy.constraint.ValidPaymentMethodConstraint;
import alfarezyyd.pharmacy.constraint.ValidPaymentStatusConstraint;
import alfarezyyd.pharmacy.constraint.ValidShippingMethodConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderCreateRequest {
  @NotNull
  @JsonProperty("customer_id")
  private Long customerId;
  @JsonProperty("total_amount")
  @NotNull
  private Float totalAmount;
  @JsonProperty("payment_method")
  @NotBlank
  @ValidPaymentMethodConstraint
  private String paymentMethod;
  @JsonProperty("payment_status")
  @NotBlank
  @ValidPaymentStatusConstraint
  private String paymentStatus;
  @JsonProperty("order_status")
  @NotBlank
  @ValidOrderStatusConstraint
  private String orderStatus;
  @JsonProperty("shipping_method")
  @NotBlank
  @ValidShippingMethodConstraint
  private String shippingMethod;
  @JsonProperty("tracking_number")
  @NotBlank
  private String trackingNumber;

  public Long getCustomerId() {
    return customerId;
  }

  public Float getTotalAmount() {
    return totalAmount;
  }

  public String getPaymentMethod() {
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
}
