package alfarezyyd.pharmacy.listener;

import alfarezyyd.pharmacy.config.Database;
import alfarezyyd.pharmacy.config.DependencyContainer;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.usecase.MedicineUsecase;
import alfarezyyd.pharmacy.usecase.OrderUsecase;
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
    sce.getServletContext().setAttribute("customerUsecase", customerUsecase);
    sce.getServletContext().setAttribute("medicineUsecase", medicineUsecase);
    sce.getServletContext().setAttribute("addressUsecase", addressUsecase);
    sce.getServletContext().setAttribute("orderUsecase", orderUsecase);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    Database.getHikariDataSource().close();
    ValidationUtil.getValidatorFactory().close();
  }
}
