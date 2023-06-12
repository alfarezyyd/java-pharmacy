package alfarezyyd.pharmacy.util;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationUtil {
  private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private static final Validator validator = validatorFactory.getValidator();

  public static ValidatorFactory getValidatorFactory() {
    return validatorFactory;
  }

  public static Validator getValidator() {
    return validator;
  }
}
