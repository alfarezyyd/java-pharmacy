package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidPaymentMethodConstraint;
import alfarezyyd.pharmacy.model.entity.option.PaymentMethod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPaymentMethodValidator implements ConstraintValidator<ValidPaymentMethodConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return PaymentMethod.fromValue(value) != null;

  }
}
