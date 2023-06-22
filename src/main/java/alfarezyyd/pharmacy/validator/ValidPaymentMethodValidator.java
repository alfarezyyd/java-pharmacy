package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidPaymentMethodConstraint;
import alfarezyyd.pharmacy.model.entity.PaymentMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPaymentMethodValidator implements ConstraintValidator<ValidPaymentMethodConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      PaymentMethod.fromValue(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
