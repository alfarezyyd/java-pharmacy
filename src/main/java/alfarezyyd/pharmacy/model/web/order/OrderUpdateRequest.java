package alfarezyyd.pharmacy.model.web.order;

import alfarezyyd.pharmacy.model.entity.OrderStatus;
import alfarezyyd.pharmacy.model.entity.PaymentMethod;
import alfarezyyd.pharmacy.model.entity.PaymentStatus;
import alfarezyyd.pharmacy.model.entity.ShippingMethod;

public class OrderUpdateRequest {
  private Long id;
  private Long customerId;
  private Float totalAmount;
  private PaymentMethod paymentMethod;
  private PaymentStatus paymentStatus;
  private OrderStatus orderStatus;
  private ShippingMethod shippingMethod;
  private String trackingNumber;

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

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public ShippingMethod getShippingMethod() {
    return shippingMethod;
  }

  public String getTrackingNumber() {
    return trackingNumber;
  }

}
