package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.config.DependencyContainer;
import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet(urlPatterns = "/api/customers/*")
public class CustomerController extends HttpServlet {
  private CustomerUsecase customerUsecase = DependencyContainer.getInstance().getCustomerUsecase();

  @Override
  public void init(ServletConfig config) throws ServletException {
    customerUsecase = (CustomerUsecase) config.getServletContext().getAttribute("customerUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    String pathInfo = req.getPathInfo();
    LinkedList<CustomerResponse> allCustomer = new LinkedList<>();
    if (pathInfo != null && pathInfo.equals("/details")) {
      try {
        String customerId = req.getParameter("customer-id");
        Long customerIdLong = Long.valueOf(customerId);
        allCustomer.add(customerUsecase.getDetailCustomer(serverError, clientError, customerIdLong));
      } catch (NumberFormatException e) {
        clientError.addActionError("get detail customer", "failed! query param {customer-id} not a number");
      }
    } else {
      String sortedBy = req.getParameter("sorted-by");
      String algorithm = req.getParameter("algorithm");
      allCustomer = customerUsecase.getAllCustomer(serverError, clientError, sortedBy, algorithm);
    }

    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, allCustomer);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      CustomerCreateRequest customerCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), CustomerCreateRequest.class);
      customerUsecase.createCustomer(serverError, clientError, customerCreateRequest);
    } catch (JsonParseException | MismatchedInputException e){
      clientError.addActionError("create new customer", e.getOriginalMessage());
    }
    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      CustomerUpdateRequest customerUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), CustomerUpdateRequest.class);
      customerUsecase.updateCustomer(serverError, clientError, customerUpdateRequest);
    } catch (JsonParseException | MismatchedInputException e) {
      clientError.addActionError("update customer", e.getOriginalMessage());
    }
    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    String customerId = req.getParameter("customer-id");
    try {
      Long customerIdLong = Long.parseLong(customerId);
      customerUsecase.deleteCustomer(serverError, clientError, customerIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("delete customer!", "failed! query param {customer-id} not a number");
    }
    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }
}