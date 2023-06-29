package alfarezyyd.pharmacy.controller;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.response.UserResponse;
import alfarezyyd.pharmacy.model.web.user.UserCreateRequest;
import alfarezyyd.pharmacy.model.web.user.UserUpdateRequest;
import alfarezyyd.pharmacy.usecase.UserUsecase;
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

@WebServlet(urlPatterns = "/api/users")
public class UserController extends HttpServlet {
  private UserUsecase userUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    userUsecase = (UserUsecase) config.getServletContext().getAttribute("userUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    LinkedList<UserResponse> allUserResponse = userUsecase.getAllUser(serverError);
    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, allUserResponse);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ClientError clientError = new ClientError();
    ServerError serverError = new ServerError();
    try {
      UserCreateRequest userCreateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), UserCreateRequest.class);
      userUsecase.createUser(serverError, clientError, userCreateRequest);
    } catch (JsonParseException | MismatchedInputException e) {
      clientError.addActionError("create new user", e.getOriginalMessage());
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
      UserUpdateRequest userUpdateRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), UserUpdateRequest.class);
      userUsecase.updateUser(serverError, clientError, userUpdateRequest);
    } catch (JsonParseException | MismatchedInputException e) {
      clientError.addActionError("update user", e.getOriginalMessage());
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
    String userId = req.getParameter("user-id");
    try {
      Long userIdLong = Long.parseLong(userId);
      userUsecase.deleteUser(serverError, clientError, userIdLong);
    } catch (NumberFormatException e) {
      clientError.addActionError("delete user!", "failed! query param {user-id} not a number");
    }
    if (ExceptionCheck.isExceptionOccurred(serverError, clientError, resp)) {
      return;
    }
    ResponseWriter.writeToResponseBodySuccess(resp, null);
  }
}
