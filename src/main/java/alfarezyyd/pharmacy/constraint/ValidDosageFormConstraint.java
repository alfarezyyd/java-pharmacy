package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidDosageFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidDosageFormValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDosageFormConstraint {
  String message() default "invalid dosage form! must be one of this : {Tablet, Capsule, Caplet, Pill, Powder, Suppositoria, Rub, Liquid, Suspension, Injection, Drop, Inhaler}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
