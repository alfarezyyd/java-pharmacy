package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ResponseWriter {
  public static void writeToResponseBodySuccess(HttpServletResponse resp, Object responseData) {
    try {
      Map<String, Object> webResponse = new HashMap<>();
      webResponse.put("code", "200 OK");
      webResponse.put("message", "Success!");
      webResponse.put("data", responseData);
      webResponse.put("errors", null);
      resp.setHeader("Content-Type", "application/json");
      resp.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(webResponse));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void writeToResponseBodyClientError(HttpServletResponse resp, ClientError clientError) {
    Map<String, Map<String, LinkedList<?>>> errorsResponse = new HashMap<>();
    errorsResponse.put("client_errors", clientError.getClientErrors());
    try {
      Map<String, Object> webResponse = new HashMap<>();
      webResponse.put("code", "400 BAD_REQUEST");
      webResponse.put("message", "Failed! Error has Occured");
      webResponse.put("data", null);
      webResponse.put("errors", errorsResponse);
      resp.setHeader("Content-Type", "application/json");
      resp.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(webResponse));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public static void writeToResponseBodyServerError(HttpServletResponse resp) {
    try {
      Map<String, Object> webResponse = new HashMap<>();
      webResponse.put("code", "500 INTERNAL_SERVER_ERROR");
      webResponse.put("message", "Failed! Error has Occured");
      webResponse.put("data", null);
      webResponse.put("errors", "Internal Server Error");
      resp.setHeader("Content-Type", "application/json");
      resp.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(webResponse));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
