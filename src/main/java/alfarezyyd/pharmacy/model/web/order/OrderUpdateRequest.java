package alfarezyyd.pharmacy.model.web.order;

import alfarezyyd.pharmacy.constraint.ValidOrderStatusConstraint;
import alfarezyyd.pharmacy.constraint.ValidPaymentMethodConstraint;
import alfarezyyd.pharmacy.constraint.ValidPaymentStatusConstraint;
import alfarezyyd.pharmacy.constraint.ValidShippingMethodConstraint;
import alfarezyyd.pharmacy.model.web.orderMedicine.OrderMedicineRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class OrderUpdateRequest {
  @NotNull
  private Long id;
  @NotNull
  @ValidPaymentMethodConstraint
  @JsonProperty("payment_method")
  private String paymentMethod;
  @NotNull
  @ValidPaymentStatusConstraint
  @JsonProperty("payment_status")
  private String paymentStatus;
  @ValidOrderStatusConstraint
  @NotNull
  @JsonProperty("order_status")
  private String orderStatus;
  @ValidShippingMethodConstraint
  @NotNull
  @JsonProperty("shipping_method")
  private String shippingMethod;
  @NotNull
  @JsonProperty("tracking_number")
  private String trackingNumber;
  @JsonProperty("order_medicine")
  @Valid
  private OrderMedicineRequest orderMedicine;

  public Long getId() {
    return id;
  }

  public String getString() {
    return paymentMethod;
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

  public OrderMedicineRequest getOrderMedicine() {
    return orderMedicine;
  }
}
