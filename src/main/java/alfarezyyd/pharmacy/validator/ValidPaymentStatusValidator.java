package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidPaymentStatusConstraint;
import alfarezyyd.pharmacy.model.entity.option.PaymentStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPaymentStatusValidator implements ConstraintValidator<ValidPaymentStatusConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      PaymentStatus.fromValue(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
