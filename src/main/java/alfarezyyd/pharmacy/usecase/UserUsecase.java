package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.entity.option.Position;
import alfarezyyd.pharmacy.model.web.authentication.LoginRequest;
import alfarezyyd.pharmacy.model.web.response.UserResponse;
import alfarezyyd.pharmacy.model.web.user.UserCreateRequest;
import alfarezyyd.pharmacy.model.web.user.UserUpdateRequest;

import java.util.LinkedList;

public interface UserUsecase {
  LinkedList<UserResponse> getAllUser(ServerError serverError);

  void createUser(ServerError serverError, ClientError clientError, UserCreateRequest userCreateRequest);

  void updateUser(ServerError serverError, ClientError clientError, UserUpdateRequest userUpdateRequest);

  void deleteUser(ServerError serverError, ClientError clientError, Long userId);

  Position userLogin(ServerError serverError, ClientError clientError, LoginRequest loginRequest);
}
