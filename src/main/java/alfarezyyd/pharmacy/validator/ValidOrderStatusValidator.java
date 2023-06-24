package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidOrderStatusConstraint;
import alfarezyyd.pharmacy.model.entity.option.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidOrderStatusValidator implements ConstraintValidator<ValidOrderStatusConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      OrderStatus.fromValue(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
