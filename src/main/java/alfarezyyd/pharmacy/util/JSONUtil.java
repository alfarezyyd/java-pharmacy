package alfarezyyd.pharmacy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JSONUtil {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
  public static ObjectMapper getObjectMapper(){
    return OBJECT_MAPPER;
  }
}
