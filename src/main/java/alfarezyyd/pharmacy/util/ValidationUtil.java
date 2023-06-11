package alfarezyyd.pharmacy.util;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationUtil {
  private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private static Validator validator = validatorFactory.getValidator();

  public static ValidatorFactory getValidatorFactory() {
    return validatorFactory;
  }

  public static Validator getValidator() {
    return validator;
  }
}
