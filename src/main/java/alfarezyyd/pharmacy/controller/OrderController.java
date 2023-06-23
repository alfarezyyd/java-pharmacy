package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.order.OrderCreateRequest;
import alfarezyyd.pharmacy.model.web.order.OrderUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.OrderResponse;
import alfarezyyd.pharmacy.usecase.OrderUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet(urlPatterns = "/api/orders")
public class OrderController extends HttpServlet {
  private OrderUsecase orderUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    orderUsecase = (OrderUsecase) config.getServletContext().getAttribute("orderUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    LinkedList<OrderResponse> allOrderByCustomerId = new LinkedList<>();
    String customerId = req.getParameter("customer-id");
    try {
      Long customerIdLong = Long.valueOf(customerId);
      allOrderByCustomerId = orderUsecase.getAllOrderByCustomerId(serverError, customerIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("get all order by customer", "invalid! query param {customer-id} must number");
    }
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, allOrderByCustomerId);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    OrderCreateRequest orderCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), OrderCreateRequest.class);
    orderUsecase.createOrder(serverError, clientError, orderCreateRequest);
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    OrderUpdateRequest orderUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), OrderUpdateRequest.class);
    orderUsecase.updateOrder(serverError, clientError, orderUpdateRequest);
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    String orderId = req.getParameter("order-id");
    String customerId = req.getParameter("customer-id");
    try {
      Long orderIdLong = Long.parseLong(orderId);
      Long customerIdLong = Long.parseLong(customerId);
      orderUsecase.deleteOrder(serverError, clientError, orderIdLong, customerIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("delete order", "invalid! query param {order-id} and {customer-id} must number");
    }
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }
}
