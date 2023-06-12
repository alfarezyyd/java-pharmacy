package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionCheck {
  public static Boolean exceptionCheck(ServerError serverError, ClientError clientError, HttpServletResponse resp) {
    if (serverError.hasErrors() || clientError.hasErrors()) {
      Model.writeToResponseBodyError(serverError, clientError, resp);
      return true;
    }
    return false;
  }
}
