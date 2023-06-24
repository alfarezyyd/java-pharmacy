package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ExceptionCheck {
  public static Boolean isExceptionOccured(ServerError serverError, ClientError clientError, HttpServletResponse resp) {
    if (clientError.hasErrors()) {
      ResponseWriter.writeToResponseBodyClientError(resp, clientError);
      return true;
    } else if (serverError.hasErrors()) {
      ResponseWriter.writeToResponseBodyServerError(resp);
      Map<String, Map<String, LinkedList<?>>> errorsResponse = new HashMap<>();
      errorsResponse.put("server_errors", serverError.getServerErrors());
      System.out.println(errorsResponse);
      return true;
    }
    return false;
  }
}
