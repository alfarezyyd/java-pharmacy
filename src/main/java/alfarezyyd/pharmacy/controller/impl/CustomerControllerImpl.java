package alfarezyyd.pharmacy.controller.impl;

import alfarezyyd.pharmacy.controller.CustomerController;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.web.customer.CustomerCreateRequest;
import alfarezyyd.pharmacy.model.web.customer.CustomerUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.repository.impl.AddressRepositoryImpl;
import alfarezyyd.pharmacy.repository.impl.CustomerRepositoryImpl;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.usecase.impl.AddressUsecaseImpl;
import alfarezyyd.pharmacy.usecase.impl.CustomerUsecaseImpl;
import alfarezyyd.pharmacy.util.DatabaseUtil;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@WebServlet(urlPatterns = "/customers")
public class CustomerControllerImpl extends HttpServlet implements CustomerController {
  private CustomerUsecase customerUsecase;

  @Override
  public void init() throws ServletException {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    AddressRepository addressRepository = new AddressRepositoryImpl();
    AddressUsecase addressUsecase = new AddressUsecaseImpl(addressRepository, DatabaseUtil.getHikariDataSource());
    CustomerUsecase customerUsecase = new CustomerUsecaseImpl(customerRepository, addressUsecase, DatabaseUtil.getHikariDataSource());
    this.customerUsecase = customerUsecase;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    LinkedList<CustomerResponse> allCustomer = customerUsecase.getAllCustomer();
    Map<String, Object> webResponse = new HashMap<>();
    Model.writeToResponseBodySuccess(resp, allCustomer);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CustomerCreateRequest customerCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), CustomerCreateRequest.class);
    customerUsecase.createCustomer(customerCreateRequest);
    Map<String, Object> webResponse = new HashMap<>();
    Model.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    CustomerUpdateRequest customerUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), CustomerUpdateRequest.class);
    customerUsecase.updateCustomer(customerUpdateRequest);
    Model.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}
