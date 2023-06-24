package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidDosageFormConstraint;
import alfarezyyd.pharmacy.model.entity.option.DosageForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDosageFormValidator implements ConstraintValidator<ValidDosageFormConstraint, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    try {
      DosageForm.fromValue(value);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
