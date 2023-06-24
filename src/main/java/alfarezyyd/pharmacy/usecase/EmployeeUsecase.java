package alfarezyyd.pharmacy.usecase;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.web.employee.EmployeeCreateRequest;
import alfarezyyd.pharmacy.model.web.employee.EmployeeUpdateRequest;
import alfarezyyd.pharmacy.model.web.response.EmployeeResponse;

import java.util.LinkedList;

public interface EmployeeUsecase {
  LinkedList<EmployeeResponse> getAllEmployee(ServerError serverError, ClientError clientError, String sortedBy, String algorithm);

  EmployeeResponse getDetailEmployee(ServerError serverError, ClientError clientError, Long employeeId);

  void createEmployee(ServerError serverError, ClientError clientError, EmployeeCreateRequest employeeCreateRequest);

  void updateEmployee(ServerError serverError, ClientError clientError, EmployeeUpdateRequest employeeUpdateRequest);

  void deleteEmployee(ServerError serverError, ClientError clientError, Long employeeId);
}
