package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidShippingMethodValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidShippingMethodValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidShippingMethodConstraint {
  String message() default "invalid shipping method! must be one of this : {Regular, Express, One Day}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
