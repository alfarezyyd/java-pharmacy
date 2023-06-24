package alfarezyyd.pharmacy.config;

import alfarezyyd.pharmacy.repository.*;
import alfarezyyd.pharmacy.repository.impl.*;
import alfarezyyd.pharmacy.usecase.*;
import alfarezyyd.pharmacy.usecase.impl.*;
import com.zaxxer.hikari.HikariDataSource;


public class DependencyContainer {

  private static DependencyContainer dependencyContainer;
  private final CustomerUsecase customerUsecase;
  private final AddressUsecase addressUsecase;
  private final MedicineUsecase medicineUsecase;
  private final OrderUsecase orderUsecase;
  private final EmployeeUsecase employeeUsecase;
  private final UserUsecase userUsecase;

  private DependencyContainer() {
    // Data Source
    HikariDataSource hikariDataSource = Database.getHikariDataSource();

    // Address
    AddressRepository addressRepository = new AddressRepositoryImpl();
    // Customer
    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    addressUsecase = new AddressUsecaseImpl(addressRepository, hikariDataSource, customerRepository);
    customerUsecase = new CustomerUsecaseImpl(customerRepository, addressUsecase, addressRepository, hikariDataSource);

    // Medicine Information
    MedicineInformationRepository medicineInformationRepository = new MedicineInformationRepositoryImpl();
    MedicineInformationUsecase medicineInformationUsecase = new MedicineInformationUsecaseImpl(medicineInformationRepository, hikariDataSource);

    // Medicine
    MedicineRepository medicineRepository = new MedicineRepositoryImpl();
    medicineUsecase = new MedicineUsecaseImpl(medicineRepository, hikariDataSource, medicineInformationUsecase);

    // OrderMedicine
    OrderMedicineRepository orderMedicineRepository = new OrderMedicineRepositoryImpl();
    OrderMedicineUsecase orderMedicineUsecase = new OrderMedicineUsecaseImpl(orderMedicineRepository, medicineRepository);

    // Order
    OrderRepository orderRepository = new OrderRepositoryImpl();
    orderUsecase = new OrderUsecaseImpl(customerRepository, orderMedicineUsecase, orderRepository, hikariDataSource);

    // Employee
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    employeeUsecase = new EmployeeUsecaseImpl(employeeRepository, hikariDataSource);

    // User
    UserRepository userRepository = new UserRepositoryImpl();
    userUsecase = new UserUsecaseImpl(userRepository, hikariDataSource, employeeRepository);
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

  public MedicineUsecase getMedicineUsecase() {
    return medicineUsecase;
  }

  public AddressUsecase getAddressUsecase() {
    return addressUsecase;
  }

  public OrderUsecase getOrderUsecase() {
    return orderUsecase;
  }

  public EmployeeUsecase getEmployeeUsecase() {
    return employeeUsecase;
  }

  public UserUsecase getUserUsecase() {
    return userUsecase;
  }
}
