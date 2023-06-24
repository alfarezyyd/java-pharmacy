package alfarezyyd.pharmacy.repository;

import alfarezyyd.pharmacy.exception.DatabaseError;
import alfarezyyd.pharmacy.model.entity.Employee;

import java.sql.Connection;
import java.util.LinkedList;

public interface EmployeeRepository {
  LinkedList<Employee> getAllEmployee(Connection connection) throws DatabaseError;

  void createEmployee(Connection connection, Employee employee) throws DatabaseError;

  void updateEmployee(Connection connection, Employee employee) throws DatabaseError;

  void deleteEmployee(Connection connection, Long employeeId) throws DatabaseError;

}
