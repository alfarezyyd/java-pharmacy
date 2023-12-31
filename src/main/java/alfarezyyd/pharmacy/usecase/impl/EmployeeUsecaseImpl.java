package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.helper.SortingMapping;
import alfarezyyd.pharmacy.model.entity.Employee;
import alfarezyyd.pharmacy.model.entity.User;
import alfarezyyd.pharmacy.model.entity.option.Gender;
import alfarezyyd.pharmacy.model.entity.option.Position;
import alfarezyyd.pharmacy.model.web.employee.EmployeeCreateRequest;
import alfarezyyd.pharmacy.model.web.employee.EmployeeUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.EmployeeResponse;
import alfarezyyd.pharmacy.repository.EmployeeRepository;
import alfarezyyd.pharmacy.repository.UserRepository;
import alfarezyyd.pharmacy.usecase.EmployeeUsecase;
import alfarezyyd.pharmacy.util.SearchUtil;
import alfarezyyd.pharmacy.util.StringUtil;
import alfarezyyd.pharmacy.util.ValidationUtil;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.validation.ConstraintViolation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Set;

public class EmployeeUsecaseImpl implements EmployeeUsecase {
  private final EmployeeRepository employeeRepository;
  private final HikariDataSource hikariDataSource;
  private final UserRepository userRepository;

  public EmployeeUsecaseImpl(EmployeeRepository employeeRepository, HikariDataSource hikariDataSource, UserRepository userRepository) {
    this.employeeRepository = employeeRepository;
    this.hikariDataSource = hikariDataSource;
    this.userRepository = userRepository;
  }

  @Override
  public LinkedList<EmployeeResponse> getAllEmployee(ServerError serverError, ClientError clientError, String sortedBy, String algorithm) {
    LinkedList<EmployeeResponse> employeeResponses = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
      if (sortedBy != null) {
        algorithm = algorithm == null ? "quick-sort" : algorithm;
        SortingMapping.mappingEmployeeSorting(sortedBy, algorithm, clientError, allEmployee);
      }
      for (Employee employee : allEmployee) {
        employeeResponses.add(Model.convertToEmployeeResponse(employee, null));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return employeeResponses;
  }

  @Override
  public EmployeeResponse getDetailEmployee(ServerError serverError, ClientError clientError, Long employeeId) {
    EmployeeResponse employeeResponse = new EmployeeResponse();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
      Employee employee = SearchUtil.binarySearch(allEmployee, employeeId);
      if (employee == null) {
        clientError.addActionError("get detail employee", "employee not found");
        return null;
      }
      LinkedList<User> allUser = userRepository.getAllUser(connection);
      User user = SearchUtil.sequentialSearchByEmployeeId(allUser, employeeId);
      if (user == null) {
        clientError.addActionError("get detail employee", "user hasn't been created");
        return null;
      }
      employeeResponse = Model.convertToEmployeeResponse(employee, Model.convertToUserResponse(user));
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return employeeResponse;
  }

  @Override
  public void createEmployee(ServerError serverError, ClientError clientError, EmployeeCreateRequest employeeCreateRequest) {
    Set<ConstraintViolation<EmployeeCreateRequest>> constraintViolations = ValidationUtil.getValidator().validate(employeeCreateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<EmployeeCreateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
      return;
    }

    try (Connection connection = hikariDataSource.getConnection()) {
      Employee employee = new Employee();
      employee.setFullName(employeeCreateRequest.getFullName());
      employee.setGender(Gender.fromValue(employeeCreateRequest.getGender()));
      employee.setHireDate(Date.valueOf(employeeCreateRequest.getHireDate()));
      employee.setPosition(Position.fromValue(employeeCreateRequest.getPosition()));
      employee.setStartDate(Date.valueOf(employeeCreateRequest.getStartDate()));
      if (employeeCreateRequest.getEndDate() != null) {
        employee.setEndDate(Date.valueOf(employeeCreateRequest.getEndDate()));
      }
      employeeRepository.createEmployee(connection, employee);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateEmployee(ServerError serverError, ClientError clientError, EmployeeUpdateRequest employeeUpdateRequest) {
    Set<ConstraintViolation<EmployeeUpdateRequest>> constraintViolations = ValidationUtil.getValidator().validate(employeeUpdateRequest);
    if (!constraintViolations.isEmpty()) {
      for (ConstraintViolation<EmployeeUpdateRequest> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        clientError.addValidationError(StringUtil.toSnakeCase(propertyPath), constraintViolation.getMessage());
      }
      return;
    }
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
      Employee employee = SearchUtil.binarySearch(allEmployee, employeeUpdateRequest.getId());
      if (employee == null) {
        clientError.addActionError("update employee", "employee not found");
        return;
      }
      employee.setFullName(employeeUpdateRequest.getFullName());
      employee.setGender(Gender.fromValue(employeeUpdateRequest.getGender()));
      employee.setHireDate(Date.valueOf(employeeUpdateRequest.getHireDate()));
      employee.setPosition(Position.fromValue(employeeUpdateRequest.getPosition()));
      employee.setStartDate(Date.valueOf(employeeUpdateRequest.getStartDate()));
      if (employeeUpdateRequest.getEndDate() != null) {
        employee.setEndDate(Date.valueOf(employeeUpdateRequest.getEndDate()));
      }
      employee.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
      employeeRepository.updateEmployee(connection, employee);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteEmployee(ServerError serverError, ClientError clientError, Long employeeId) {
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
      Employee employee = SearchUtil.binarySearch(allEmployee, employeeId);
      if (employee == null) {
        clientError.addActionError("delete employee", "employee not found");
        return;
      }
      employeeRepository.deleteEmployee(connection, employeeId);
    } catch (SQLException e) {
      serverError.addDatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
