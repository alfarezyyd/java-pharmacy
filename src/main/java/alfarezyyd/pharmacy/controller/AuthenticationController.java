package alfarezyyd.pharmacy.controller;


import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.ExceptionCheck;
import alfarezyyd.pharmacy.helper.ResponseWriter;
import alfarezyyd.pharmacy.model.web.authentication.LoginRequest;
import alfarezyyd.pharmacy.usecase.UserUsecase;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/api/authentication/*")
public class AuthenticationController extends HttpServlet {
  private UserUsecase userUsecase;

  @Override
  public void init(ServletConfig config) throws ServletException {
    userUsecase = (UserUsecase) config.getServletContext().getAttribute("userUsecase");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServerError serverError = new ServerError();
    ClientError clientError = new ClientError();
    HttpSession httpSession = req.getSession(true);
    String pathInfo = req.getPathInfo();
    if (pathInfo != null) {
      if (pathInfo.equals("/login")) {
        LoginRequest loginRequest = JSONUtil.getObjectMapper().readValue(req.getReader(), LoginRequest.class);
        String employeePosition = userUsecase.userLogin(serverError, clientError, loginRequest);
        if (employeePosition != null) {
          httpSession.setAttribute("position", employeePosition);
          httpSession.setAttribute("email", loginRequest.getEmail());
          ResponseWriter.writeToResponseBodySuccess(resp, "Login Succeeded!");
        }
      } else if (pathInfo.equals("/logout")) {
        httpSession.invalidate();
        ResponseWriter.writeToResponseBodySuccess(resp, "Logout Succeeded!");
      }
    } else {
      clientError.addActionError("authentication", "please visit /login or /logout");
    }
    ExceptionCheck.isExceptionOccurred(serverError, clientError, resp);
  }
}
