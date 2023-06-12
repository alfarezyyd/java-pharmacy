package alfarezyyd.pharmacy.listener;

import alfarezyyd.pharmacy.config.Database;
import alfarezyyd.pharmacy.config.DependencyContainer;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
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
    CustomerUsecase medicineUsecase = dependencyContainer.getCustomerUsecase();
    sce.getServletContext().setAttribute("customerUsecase", customerUsecase);
    sce.getServletContext().setAttribute("medicineUsecase", medicineUsecase);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    Database.getHikariDataSource().close();
    ValidationUtil.getValidatorFactory().close();
  }
}
