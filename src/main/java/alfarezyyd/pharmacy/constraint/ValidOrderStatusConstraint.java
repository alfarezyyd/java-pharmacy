package alfarezyyd.pharmacy.constraint;

import alfarezyyd.pharmacy.validator.ValidOrderStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValidOrderStatusValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrderStatusConstraint {
  String message() default "invalid order status! must be one of this : {On Process, Sent, Received, Cancelled}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
