package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidDosageFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidDosageFormValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPaymentMethodConstraint {
  String message() default "invalid payment method!, must be one of this : {Credit, Transfer, COD}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
