package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.order.OrderCreateRequest;
import alfarezyyd.pharmacy.model.web.order.OrderUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.OrderResponse;

import java.util.LinkedList;

public interface OrderUsecase {
  LinkedList<OrderResponse> getAllOrderByCustomerId(ServerError serverError, Long customerId);

  void createOrder(ServerError serverError, ClientError clientError, OrderCreateRequest orderCreateRequest);

  void updateOrder(ServerError serverError, ClientError clientError, OrderUpdateRequest orderUpdateRequest);

  void deleteOrder(ServerError serverError, ClientError clientError, Long orderId);
}
