package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.employee.EmployeeCreateRequest;
import alfarezyyd.pharmacy.model.web.employee.EmployeeUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.EmployeeResponse;
import alfarezyyd.pharmacy.usecase.EmployeeUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import com.fasterxml.jackson.core.JsonParseException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet(urlPatterns = "/api/employees/*")
public class EmployeeController extends HttpServlet {
  private EmployeeUsecase employeeUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    employeeUsecase = (EmployeeUsecase) config.getServletContext().getAttribute("employeeUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    String pathInfo = req.getPathInfo();
    LinkedList<EmployeeResponse> allEmployee = new LinkedList<>();
    if (pathInfo != null && pathInfo.equals("/details")) {
      try {
        String employeeId = req.getParameter("employee-id");
        Long employeeIdLong = Long.valueOf(employeeId);
        allEmployee.add(employeeUsecase.getDetailEmployee(serverError, clientError, employeeIdLong));
      } catch (NumberFormatException e) {
        clientError.addActionError("get detail employee", "failed! query param {employee-id} not a number");
      }
    } else {
      allEmployee = employeeUsecase.getAllEmployee(serverError);
    }
    if (ExceptionCheck.isExceptionOccured(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, allEmployee);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      EmployeeCreateRequest employeeCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), EmployeeCreateRequest.class);
      employeeUsecase.createEmployee(serverError, clientError, employeeCreateRequest);
    } catch (JsonParseException e) {
      clientError.addActionError("create new employee", e.getOriginalMessage());
    }
    if (ExceptionCheck.isExceptionOccured(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      EmployeeUpdateRequest employeeUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), EmployeeUpdateRequest.class);
      employeeUsecase.updateEmployee(serverError, clientError, employeeUpdateRequest);
    } catch (JsonParseException e) {
      clientError.addActionError("update employee", e.getOriginalMessage());
    }
    if (ExceptionCheck.isExceptionOccured(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      String employeeId = req.getParameter("employee-id");
      Long employeeIdLong = Long.valueOf(employeeId);
      employeeUsecase.deleteEmployee(serverError, clientError, employeeIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("delete employee", "invalid! query param {employee-id} must number");
    }

    if (ExceptionCheck.isExceptionOccured(serverError, clientError, resp)){
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }
}
