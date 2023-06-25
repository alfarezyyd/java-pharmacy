package alfarezyyd.pharmacy.repository.impl;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Employee;
import alfarezyyd.pharmacy.model.entity.option.Gender;
import alfarezyyd.pharmacy.model.entity.option.Position;
import alfarezyyd.pharmacy.repository.EmployeeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class EmployeeRepositoryImpl implements EmployeeRepository {
  @Override
  public LinkedList<Employee> getAllEmployee(Connection connection) throws DatabaseError {
    String sqlSyntax = """
        SELECT * FROM employees
        """;
    LinkedList<Employee> allEmployee = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax);
         ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFullName(resultSet.getString("full_name"));
        employee.setGender(Gender.fromValue(resultSet.getString("gender")));
        employee.setHireDate(resultSet.getDate("hire_date"));
        employee.setPosition(Position.fromValue(resultSet.getString("position")));
        employee.setStartDate(resultSet.getDate("start_date"));
        employee.setEndDate(resultSet.getDate("end_date"));
        employee.setCreatedAt(resultSet.getTimestamp("created_at"));
        employee.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        allEmployee.add(employee);
      }
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
    return allEmployee;
  }

  @Override
  public void createEmployee(Connection connection, Employee employee) throws DatabaseError {
    String sqlSyntax = """
        INSERT INTO employees(full_name, gender, hire_date, position, start_date, end_date) VALUES (?,?,?,?,?,?)
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, employee.getFullName());
      preparedStatement.setString(2, employee.getGender().getValue());
      preparedStatement.setDate(3, employee.getHireDate());
      preparedStatement.setString(4, employee.getPosition().getValue());
      preparedStatement.setDate(5, employee.getStartDate());
      preparedStatement.setDate(6, employee.getEndDate());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void updateEmployee(Connection connection, Employee employee) throws DatabaseError {
    String sqlSyntax = """
        UPDATE employees SET full_name=?, gender=?, hire_date=?, position=?, start_date=?, end_date=?, updated_at=? WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setString(1, employee.getFullName());
      preparedStatement.setString(2, employee.getGender().getValue());
      preparedStatement.setDate(3, employee.getHireDate());
      preparedStatement.setString(4, employee.getPosition().getValue());
      preparedStatement.setDate(5, employee.getStartDate());
      preparedStatement.setDate(6, employee.getEndDate());
      preparedStatement.setTimestamp(7, employee.getUpdatedAt());
      preparedStatement.setLong(8, employee.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }

  @Override
  public void deleteEmployee(Connection connection, Long employeeId) throws DatabaseError {
    String sqlSyntax = """
        DELETE FROM employees WHERE id=?
        """;
    try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSyntax)) {
      preparedStatement.setLong(1, employeeId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new DatabaseError(e.getMessage(), e.getErrorCode(), e.getSQLState());
    }
  }
}
