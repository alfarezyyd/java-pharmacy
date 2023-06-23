package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidMedicineIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidMedicineIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMedicineIdConstraint {
  String message() default "invalid medicine id! medicine id must be unique";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
