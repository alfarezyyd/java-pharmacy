package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionCheck {
  public static Boolean exceptionCheck(HttpServletResponse resp) {
    if (ClientError.hasErrors() || ServerError.hasErrors()){
      Model.writeToResponseBodyError(resp);
      return true;
    }
    return false;
  }
}
