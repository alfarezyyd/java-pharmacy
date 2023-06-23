package alfarezyyd.pharmacy.util;


public class StringUtil {
  public static String toSnakeCase(String camelCase) {
    return camelCase.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
  }
}
