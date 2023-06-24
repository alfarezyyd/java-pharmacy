package alfarezyyd.pharmacy.usecase.impl;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.helper.Model;
import alfarezyyd.pharmacy.model.entity.Employee;
import alfarezyyd.pharmacy.model.entity.Gender;
import alfarezyyd.pharmacy.model.web.employee.EmployeeCreateRequest;
import alfarezyyd.pharmacy.model.web.employee.EmployeeUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.EmployeeResponse;
import alfarezyyd.pharmacy.repository.EmployeeRepository;
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

  public EmployeeUsecaseImpl(EmployeeRepository employeeRepository, HikariDataSource hikariDataSource) {
    this.employeeRepository = employeeRepository;
    this.hikariDataSource = hikariDataSource;
  }

  @Override
  public LinkedList<EmployeeResponse> getAllEmployee(ServerError serverError) {
    LinkedList<EmployeeResponse> employeeResponses = new LinkedList<>();
    try (Connection connection = hikariDataSource.getConnection()) {
      LinkedList<Employee> allEmployee = employeeRepository.getAllEmployee(connection);
      for (Employee employee : allEmployee) {
        employeeResponses.add(Model.convertToEmployeeResponse(employee));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return employeeResponses;
  }

  @Override
  public EmployeeResponse getDetailEmployee(ServerError serverError, ClientError clientError, Long employeeId) {
    return null;
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
      employee.setName(employeeCreateRequest.getName());
      employee.setGender(Gender.fromValue(employeeCreateRequest.getGender()));
      employee.setHireDate(Date.valueOf(employeeCreateRequest.getHireDate()));
      employee.setPosition(employeeCreateRequest.getPosition());
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
      employee.setName(employeeUpdateRequest.getName());
      employee.setGender(Gender.fromValue(employeeUpdateRequest.getGender()));
      employee.setHireDate(Date.valueOf(employeeUpdateRequest.getHireDate()));
      employee.setPosition(employeeUpdateRequest.getPosition());
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
