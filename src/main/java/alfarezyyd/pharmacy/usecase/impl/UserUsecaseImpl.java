package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.OperationError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.helper.Transaction;
import alfarezyyd.pharmacy.model.entity.Employee;
import alfarezyyd.pharmacy.model.entity.User;
import alfarezyyd.pharmacy.model.web.authentication.LoginRequest;
import alfarezyyd.pharmacy.model.web.response.UserResponse;
import alfarezyyd.pharmacy.model.web.user.UserCreateRequest;
import alfarezyyd.pharmacy.model.web.user.UserUpdateRequest;
import alfarezyyd.pharmacy.repository.EmployeeRepository;
import alfarezyyd.pharmacy.repository.UserRepository;
import alfarezyyd.pharmacy.usecase.UserUsecase;
import alfarezyyd.pharmacy.util.HashingUtil;
import alfarezyyd.pharmacy.util.SearchUtil;
import alfarezyyd.pharmacy.util.StringUtil;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Set;

public class UserUsecaseImpl implements UserUsecase {
  private final UserRepository userRepository;
  private final HikariDataSource hikariDataSource;
  private final EmployeeRepository employeeRepository;

  public UserUsecaseImpl(UserRepository userRepository, HikariDataSource hikariDataSource, EmployeeRepository employeeRepository) {
    this.userRepository = userRepository;
    this.hikariDataSource = hikariDataSource;
    this.employeeRepository = employeeRepository;
  }

  @Override
  public LinkedList<UserResponse> getAllUser(ServerError serverError) {
    LinkedList<UserResponse> allUserResponse = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<User> allUser = userRepository.getAllUser(connection);
      for (User user : allUser) {
        allUserResponse.add(Model.convertToUserResponse(user));
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allUserResponse;
  }

  @Override
  public void createUser(ServerError serverError, ClientError clientError, UserCreateRequest userCreateRequest) {
    Set<ConstraintViolation<UserCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(userCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<UserCreateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
      return;
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
      Employee employee = SearchUtil.binarySearch(allEmployee, userCreateRequest.getEmployeeId());
      if (employee == null) {
        clientError.addActionError("create user", "employee not found");
        return;
      }

      User user = new User();
      user.setEmployeeId(userCreateRequest.getEmployeeId());
      user.setUsername(userCreateRequest.getUsername());
      user.setEmail(userCreateRequest.getEmail());
      user.setPassword(HashingUtil.hashWithSHA256(userCreateRequest.getPassword()));
      userRepository.createUser(connection, user);
    } catch (SQLException e) {
      if (e.getSQLState().equals("23000")) {
        clientError.addActionError("create new user", "user with employee id " + userCreateRequest.getEmployeeId() + " has created previously");
      }
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (OperationError e) {
      serverError.addOperationError(e.getOperation(), e.getErrorMessage());
    }
  }

  @Override
  public void updateUser(ServerError serverError, ClientError clientError, UserUpdateRequest userUpdateRequest) {
    Set<ConstraintViolation<UserUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(userUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<UserUpdateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
      return;
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      connection.setAutoCommit(false);
      LinkedList<User> allUser = userRepository.getAllUser(connection);
      User user = SearchUtil.binarySearch(allUser, userUpdateRequest.getId());
      if (user == null) {
        clientError.addActionError("update user", "user not found");
        return;
      }
      user.setUsername(userUpdateRequest.getUsername());
      user.setPassword(HashingUtil.hashWithSHA256(userUpdateRequest.getPassword()));
      user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
      userRepository.updateUser(connection, user);
      if (!user.getEmail().equals(userUpdateRequest.getEmail())) {
        userRepository.updateEmailUser(connection, userUpdateRequest.getEmail(), user.getId());
      }
      Transaction.commitOrRollback(serverError, clientError, connection);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (OperationError e) {
      serverError.addOperationError(e.getOperation(), e.getErrorMessage());
    }
  }

  @Override
  public void deleteUser(ServerError serverError, ClientError clientError, Long userId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<User> allUser = userRepository.getAllUser(connection);
      User user = SearchUtil.binarySearch(allUser, userId);
      if (user == null) {
        clientError.addActionError("delete user", "user not found");
        return;
      }
      userRepository.deleteUser(connection, userId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public String userLogin(ServerError serverError, ClientError clientError, LoginRequest loginRequest) {
    Set<ConstraintViolation<LoginRequest>> constraintViolations = ValidationUtil.getValidator().validate(loginRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<LoginRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
    }
    String employeePosition = null;
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<User> allUser = userRepository.getAllUser(connection);
      User user = SearchUtil.sequentialSearchByEmail(allUser, loginRequest.getEmail());
      if (user == null) {
        clientError.addActionError("login", "failed! user not found");
        return null;
      }

      boolean isUserValid = user.getPassword().equals(HashingUtil.hashWithSHA256(loginRequest.getPassword()));
      if (isUserValid) {
        userRepository.updateLastLoginUser(connection, new Timestamp(System.currentTimeMillis()), user.getId());
        LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
        Employee employee = SearchUtil.binarySearch(allEmployee, user.getEmployeeId());
        if (employee != null) {
          employeePosition = employee.getPosition();
        }
      }
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    } catch (OperationError e) {
      serverError.addOperationError(e.getOperation(), e.getErrorMessage());
    }
    return employeePosition;
  }
}
