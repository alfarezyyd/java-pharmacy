package alfarezyyd.pharmacy.config;

import alfarezyyd.pharmacy.repository.AddressRepository;
import alfarezyyd.pharmacy.repository.CustomerRepository;
import alfarezyyd.pharmacy.repository.MedicineRepository;
import alfarezyyd.pharmacy.repository.impl.AddressRepositoryImpl;
import alfarezyyd.pharmacy.repository.impl.CustomerRepositoryImpl;
import alfarezyyd.pharmacy.repository.impl.MedicineRepositoryImpl;
import alfarezyyd.pharmacy.usecase.AddressUsecase;
import alfarezyyd.pharmacy.usecase.CustomerUsecase;
import alfarezyyd.pharmacy.usecase.MedicineUsecase;
import alfarezyyd.pharmacy.usecase.impl.AddressUsecaseImpl;
import alfarezyyd.pharmacy.usecase.impl.CustomerUsecaseImpl;
import alfarezyyd.pharmacy.usecase.impl.MedicineUsecaseImpl;
import com.zaxxer.hikari.HikariDataSource;


public class DependencyContainer {

  private static DependencyContainer dependencyContainer;
  private final CustomerUsecase customerUsecase;
  private final AddressUsecase addressUsecase;
  private final MedicineUsecase medicineUsecase;
  private final HikariDataSource hikariDataSource = Database.getHikariDataSource();

  private DependencyContainer() {
    // Address
    AddressRepository addressRepository = new AddressRepositoryImpl();
    addressUsecase = new AddressUsecaseImpl(addressRepository, hikariDataSource);

    // Customer
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    customerUsecase = new CustomerUsecaseImpl(customerRepository, addressUsecase, addressRepository, hikariDataSource);

    // Medicine
    MedicineRepository medicineRepository = new MedicineRepositoryImpl();
    medicineUsecase = new MedicineUsecaseImpl(medicineRepository, hikariDataSource);
  }


  public static DependencyContainer getInstance() {
    if (dependencyContainer == null) {
      dependencyContainer = new DependencyContainer();
    }
    return dependencyContainer;
  }

  public CustomerUsecase getCustomerUsecase() {
    return customerUsecase;
  }
  public MedicineUsecase getMedicineUsecase(){return medicineUsecase;}

}
