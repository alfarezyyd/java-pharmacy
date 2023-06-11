package alfarezyyd.pharmacy.config;

import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.repository.impl.AddressRepositoryImpl;
import alfarezyyd.pharmacy.repository.impl.CustomerRepositoryImpl;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.usecase.impl.AddressUsecaseImpl;
import alfarezyyd.pharmacy.usecase.impl.CustomerUsecaseImpl;


public class DependencyContainer {
  private static DependencyContainer dependencyContainer;
  private final CustomerUsecase customerUsecase;
  private final AddressUsecase addressUsecase;

  private DependencyContainer() {
    // Address
    AddressRepository addressRepository = new AddressRepositoryImpl();
    addressUsecase = new AddressUsecaseImpl(addressRepository, Database.getHikariDataSource());

    // Customer
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    customerUsecase = new CustomerUsecaseImpl(customerRepository, addressUsecase, addressRepository, Database.getHikariDataSource());
  }

  public static DependencyContainer getInstance() {
    if (dependencyContainer == null) {
      return new DependencyContainer();
    }
    return dependencyContainer;
  }

  public CustomerUsecase getCustomerUsecase() {
    return customerUsecase;
  }
}
