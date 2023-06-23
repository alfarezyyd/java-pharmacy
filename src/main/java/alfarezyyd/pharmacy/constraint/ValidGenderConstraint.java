package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidDosageFormValidator;
import alfarezyyd.pharmacy.validator.ValidGenderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidGenderValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGenderConstraint {
  String message() default "invalid gender!, must be one of this : {Man, Woman}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
