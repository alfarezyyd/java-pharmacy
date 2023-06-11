package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet(urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
  private CustomerUsecase customerUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    customerUsecase = (CustomerUsecase) config.getServletContext().getAttribute("customerUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String deleted = req.getParameter("deleted");
    try {
      boolean isDeleted = Boolean.parseBoolean(deleted);
      if (isDeleted) {
        LinkedList<CustomerResponse> allDeletedCustomer = customerUsecase.getAllDeletedCustomer();
        Model.writeToResponseBodySuccess(resp, allDeletedCustomer);
        return;
      }
      LinkedList<CustomerResponse> allCustomer = customerUsecase.getAllCustomer();
      Model.writeToResponseBodySuccess(resp, allCustomer);
    } catch (NumberFormatException e) {
      ClientError.addActionError("get all deleted data", "invalid! query param delete mus true");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CustomerCreateRequest customerCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), CustomerCreateRequest.class);
    customerUsecase.createCustomer(customerCreateRequest);
    if (ExceptionCheck.exceptionCheck(resp)) {
      return;
    }
    Model.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CustomerUpdateRequest customerUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), CustomerUpdateRequest.class);
    customerUsecase.updateCustomer(customerUpdateRequest);

    if (ExceptionCheck.exceptionCheck(resp)) {
      return;
    }
    Model.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String customerId = req.getParameter("delete");
    try {
      Long customerIdLong = Long.parseLong(customerId);
      System.out.println(customerIdLong);
      customerUsecase.deleteCustomer(customerIdLong);
      Model.writeToResponseBodySuccess(resp, null);
    } catch (NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }
}
