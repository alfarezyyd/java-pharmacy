package alfarezyyd.pharmacy.listener;

import alfarezyyd.pharmacy.config.Database;
import alfarezyyd.pharmacy.config.DependencyContainer;
import alfarezyyd.pharmacy.usecase.*;
import alfarezyyd.pharmacy.util.ValidationUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializationListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    DependencyContainer dependencyContainer = DependencyContainer.getInstance();
    CustomerUsecase customerUsecase = dependencyContainer.getCustomerUsecase();
    MedicineUsecase medicineUsecase = dependencyContainer.getMedicineUsecase();
    AddressUsecase addressUsecase = dependencyContainer.getAddressUsecase();
    OrderUsecase orderUsecase = dependencyContainer.getOrderUsecase();
    EmployeeUsecase employeeUsecase = dependencyContainer.getEmployeeUsecase();
    UserUsecase userUsecase = dependencyContainer.getUserUsecase();
    sce.getServletContext().setAttribute("customerUsecase", customerUsecase);
    sce.getServletContext().setAttribute("medicineUsecase", medicineUsecase);
    sce.getServletContext().setAttribute("addressUsecase", addressUsecase);
    sce.getServletContext().setAttribute("orderUsecase", orderUsecase);
    sce.getServletContext().setAttribute("employeeUsecase", employeeUsecase);
    sce.getServletContext().setAttribute("userUsecase", userUsecase);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    Database.getHikariDataSource().close();
    ValidationUtil.getValidatorFactory().close();
  }
}
