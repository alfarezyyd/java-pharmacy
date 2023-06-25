package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidPositionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidPositionValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPositionConstraint {
  String message() default "invalid position! must be one of this : {Programmer, Pharmacists}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
