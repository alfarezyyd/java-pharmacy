package alfarezyyd.pharmacy.util;

import alfarezyyd.pharmacy.exception.OperationError;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {
  public static String hashWithSHA256(String originalString) throws OperationError {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      final byte[] hashBytes = messageDigest.digest(originalString.getBytes(StandardCharsets.UTF_8));
      return bytesToHex(hashBytes);
    } catch (NoSuchAlgorithmException e) {
      throw new OperationError("hashing password", e.getMessage());
    }
  }

  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
