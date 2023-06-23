package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.ValidMedicineIdConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ValidMedicineIdValidator implements ConstraintValidator<ValidMedicineIdConstraint, LinkedList<Long>> {

  @Override
  public boolean isValid(LinkedList<Long> values, ConstraintValidatorContext context) {
    if (values == null) {
      return true;
    }

    Set<Long> setOfLong = new HashSet<>();
    for (Long value : values) {
      if (setOfLong.contains(value)) {
        return false;
      }
      setOfLong.add(value);
    }
    return true;
  }
}
