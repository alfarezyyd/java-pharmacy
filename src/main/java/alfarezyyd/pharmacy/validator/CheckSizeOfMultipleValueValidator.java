package alfarezyyd.pharmacy.validator;

import alfarezyyd.pharmacy.constraint.CheckSizeOfMultipleValueConstraint;
import alfarezyyd.pharmacy.model.web.orderMedicine.OrderMedicineRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckSizeOfMultipleValueValidator implements ConstraintValidator<CheckSizeOfMultipleValueConstraint, OrderMedicineRequest> {
  @Override
  public boolean isValid(OrderMedicineRequest value, ConstraintValidatorContext context) {
    if (value.getAllMedicineId() == null || value.getAllQuantity() == null) {
      return true;
    }

    return value.getAllQuantity().size() == value.getAllMedicineId().size();
  }
}
