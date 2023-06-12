package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidDateValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateConstraint {
  String message() default "invalid date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
