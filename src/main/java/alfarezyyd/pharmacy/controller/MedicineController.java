package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.medicine.MedicineCreateRequest;
import alfarezyyd.pharmacy.model.web.medicine.MedicineUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.MedicineResponse;
import alfarezyyd.pharmacy.usecase.MedicineUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet(urlPatterns = "/api/medicines/*")
public class MedicineController extends HttpServlet {
  private MedicineUsecase medicineUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    medicineUsecase = (MedicineUsecase) config.getServletContext().getAttribute("medicineUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    String requestURI = req.getRequestURI();
    LinkedList<MedicineResponse> allMedicine;
    if (requestURI.equals("deleted")) {
      allMedicine = medicineUsecase.getAllDeletedMedicine(serverError, clientError);
    } else {
      allMedicine = medicineUsecase.getAllMedicine(serverError, clientError);
    }
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, allMedicine);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    MedicineCreateRequest medicineCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), MedicineCreateRequest.class);
    medicineUsecase.createMedicine(serverError, clientError, medicineCreateRequest);
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    MedicineUpdateRequest medicineUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), MedicineUpdateRequest.class);
    medicineUsecase.updateMedicine(serverError, clientError, medicineUpdateRequest);
    if (ExceptionCheck.exceptionCheck(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    String medicineId = req.getParameter("medicine-id");
    try {
      Long medicineIdLong = Long.parseLong(medicineId);
      medicineUsecase.deleteMedicine(serverError, clientError, medicineIdLong);
      ResponseWriter.writeToResponseBodySuccess(resp, null);
    } catch (NumberFormatException e) {
      clientError.addActionError("delete medicine", "invalid! query param {medicine-id} must number");
    }
  }
}
