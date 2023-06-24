package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.authentication.LoginRequest;

public interface AuthenticationUsecase {
  Boolean userLogin(ServerError serverError, ClientError clientError, LoginRequest loginRequest);
}
