package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidDosageFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidDosageFormValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPaymentStatusConstraint {
  String message() default "invalid payment method!, must be one of this : {Paid, Not Paid Yet}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
