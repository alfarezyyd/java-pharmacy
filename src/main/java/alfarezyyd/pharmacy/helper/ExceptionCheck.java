package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ExceptionCheck {
  public static Boolean isExceptionOccurred(ServerError serverError, ClientError clientError, HttpServletResponse resp) {
    if (clientError.hasErrors()) {
      ResponseWriter.writeToResponseBodyClientError(resp, clientError);
      return true;
    } else if (serverError.hasErrors()) {
      ResponseWriter.writeToResponseBodyServerError(resp);
      Map<String, Map<String, LinkedList<?>>> errorsResponse = new HashMap<>();
      errorsResponse.put("server_errors", serverError.getServerErrors());
      Path pathOfLog = Path.of("src/main/java/alfarezyyd/pharmacy/log/log.txt");
      try (BufferedWriter bufferedWriter = Files.newBufferedWriter(pathOfLog, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
        bufferedWriter.write(LocalDateTime.now() + " " + errorsResponse + "\n");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return true;
    }
    return false;
  }
}
