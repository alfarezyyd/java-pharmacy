package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.address.AddressCreateRequest;
import alfarezyyd.pharmacy.model.web.address.AddressUpdateRequest;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/addresses/customers")
public class AddressController extends HttpServlet {
  private AddressUsecase addressUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    addressUsecase = (AddressUsecase) config.getServletContext().getAttribute("addressUsecase");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    AddressCreateRequest addressCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), AddressCreateRequest.class);
    String customerId = req.getParameter("customer-id");
    try {
      Long customerIdLong = Long.valueOf(customerId);
      addressUsecase.createAddress(serverError, clientError, addressCreateRequest, customerIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("create new address", "invalid! query param {customer-id} must number");
    }

    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    AddressUpdateRequest addressUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), AddressUpdateRequest.class);
    addressUsecase.updateAddress(serverError, clientError, addressUpdateRequest);
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    String addressId = req.getParameter("address-id");
    String customerId = req.getParameter("customer-id");
    try {
      Long addressIdLong = Long.valueOf(addressId);
      Long customerIdLong = Long.valueOf(customerId);
      addressUsecase.deleteAddress(serverError, clientError, addressIdLong, customerIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("delete address", "invalid! query param {address-id} and {customer-id} must a number");
    }
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }
}
