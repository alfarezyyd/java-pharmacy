package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidDateConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidDateValidator implements ConstraintValidator<ValidDateConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      LocalDate.parse(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
