package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidGenderConstraint;
import alfarezyyd.pharmacy.model.entity.option.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGenderValidator implements ConstraintValidator<ValidGenderConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return Gender.fromValue(value) != null;
  }
}
