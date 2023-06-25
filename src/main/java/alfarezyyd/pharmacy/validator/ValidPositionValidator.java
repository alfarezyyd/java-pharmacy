package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidPositionConstraint;
import alfarezyyd.pharmacy.model.entity.option.Position;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPositionValidator implements ConstraintValidator<ValidPositionConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return Position.fromValue(value) != null;
  }
}
