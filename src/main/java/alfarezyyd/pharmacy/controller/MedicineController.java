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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
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
    String pathInfo = req.getPathInfo();
    LinkedList<MedicineResponse> allMedicine = new LinkedList<>();
    if (pathInfo != null && pathInfo.equals("/details")) {
      try {
        String medicineId = req.getParameter("medicine-id");
        Long medicineIdLong = Long.valueOf(medicineId);
        allMedicine.add(medicineUsecase.getDetailMedicine(serverError, clientError, medicineIdLong));
      } catch (NumberFormatException e) {
        clientError.addActionError("get detail customer!", "failed! query param {medicine-id} not a number");
      }
    } else {
      String sortedBy = req.getParameter("sorted-by");
      allMedicine = medicineUsecase.getAllMedicine(serverError, clientError, sortedBy);
    }
    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, allMedicine);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      MedicineCreateRequest medicineCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), MedicineCreateRequest.class);
      medicineUsecase.createMedicine(serverError, clientError, medicineCreateRequest);
    } catch (JsonParseException | UnrecognizedPropertyException e) {
      clientError.addActionError("create medicine", e.getOriginalMessage());
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
      MedicineUpdateRequest medicineUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), MedicineUpdateRequest.class);
      medicineUsecase.updateMedicine(serverError, clientError, medicineUpdateRequest);
    } catch (JsonParseException | UnrecognizedPropertyException e) {
      clientError.addActionError("update medicine", e.getOriginalMessage());
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
