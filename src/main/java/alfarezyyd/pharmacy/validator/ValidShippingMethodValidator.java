package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidShippingMethodConstraint;
import alfarezyyd.pharmacy.model.entity.option.ShippingMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidShippingMethodValidator implements ConstraintValidator<ValidShippingMethodConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return ShippingMethod.fromValue(value) != null;
  }
}
