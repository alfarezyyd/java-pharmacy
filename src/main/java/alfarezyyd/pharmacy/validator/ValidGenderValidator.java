package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidGenderConstraint;
import alfarezyyd.pharmacy.constraint.ValidShippingMethodConstraint;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.model.entity.ShippingMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGenderValidator implements ConstraintValidator<ValidGenderConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      Gender.fromValue(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
