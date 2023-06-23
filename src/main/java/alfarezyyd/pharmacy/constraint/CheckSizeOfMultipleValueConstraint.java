package alfarezyyd.pharmacy.constraint;


import alfarezyyd.pharmacy.validator.CheckSizeOfMultipleValueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckSizeOfMultipleValueValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSizeOfMultipleValueConstraint {
  String message() default "invalid size of array! size of all_medicine_id, all_quantity must be same";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
