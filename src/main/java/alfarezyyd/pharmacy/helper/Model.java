package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.model.entity.*;
import alfarezyyd.pharmacy.model.web.response.*;

public class Model {
  public static CustomerResponse convertToCustomerResponse(Customer customer) {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setId(customer.getId());
    customerResponse.setFullName(customer.getFullName());
    customerResponse.setDateOfBirth(customer.getDateOfBirth().toString());
    customerResponse.setGender(customer.getGender());
    customerResponse.setPhone(customer.getPhone());
    customerResponse.setCreatedAt(String.valueOf(customer.getCreatedAt()));
    customerResponse.setUpdatedAt(customer.getUpdatedAt() != null ? customer.getUpdatedAt().toString() : null);
    customerResponse.setDeletedAt(customer.getDeletedAt() != null ? customer.getDeletedAt().toString() : null);
    return customerResponse;
  }

  public static AddressResponse convertToAddressResponse(Address address) {
    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setId(address.getId());
    addressResponse.setStreet(address.getStreet());
    addressResponse.setCity(address.getCity());
    addressResponse.setState(address.getState());
    addressResponse.setCountry(address.getCountry());
    addressResponse.setPostalCode(address.getPostalCode());
    addressResponse.setDefault(address.getDefault());
    addressResponse.setDescription(address.getDescription());
    addressResponse.setCreatedAt(address.getCreatedAt().toString());
    addressResponse.setUpdatedAt(address.getUpdatedAt() != null ? address.getUpdatedAt().toString() : null);
    addressResponse.setDeletedAt(address.getDeletedAt() != null ? address.getDeletedAt().toString() : null);
    return addressResponse;
  }

  public static MedicineResponse convertToMedicineResponse(Medicine medicine, MedicineInformationResponse medicineInformationResponse) {
    MedicineResponse medicineResponse = new MedicineResponse();
    medicineResponse.setId(medicine.getId());
    medicineResponse.setName(medicine.getName());
    medicineResponse.setBrand(medicine.getBrand());
    medicineResponse.setPrice(medicine.getPrice());
    medicineResponse.setStock(medicine.getStock());
    medicineResponse.setMedicineInformationResponse(medicineInformationResponse);
    medicineResponse.setCreatedAt(medicine.getCreatedAt().toString());
    medicineResponse.setUpdatedAt(medicine.getUpdatedAt() != null ? medicine.getUpdatedAt().toString() : null);
    medicineResponse.setDeletedAt(medicine.getDeletedAt() != null ? medicine.getDeletedAt().toString() : null);
    return medicineResponse;
  }

  public static MedicineInformationResponse convertToMedicineInformationResponse(MedicineInformation medicineInformation) {
    MedicineInformationResponse medicineInformationResponse = new MedicineInformationResponse();
    medicineInformationResponse.setStrength(medicineInformation.getStrength());
    medicineInformationResponse.setIndications(medicineInformation.getIndications());
    medicineInformationResponse.setContraindications(medicineInformation.getContraindications());
    medicineInformationResponse.setSideEffects(medicineInformation.getSideEffects());
    medicineInformationResponse.setPrecautions(medicineInformation.getPrecautions());
    medicineInformationResponse.setStorageConditions(medicineInformation.getStorageConditions());
    medicineInformationResponse.setExpiryDate(String.valueOf(medicineInformation.getExpiryDate()));
    medicineInformationResponse.setCountryOfOrigin(medicineInformation.getCountryOfOrigin());
    return medicineInformationResponse;
  }

  public static OrderResponse convertToOrderResponse(Order order) {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setId(order.getId());
    orderResponse.setCustomerId(order.getCustomerId());
    orderResponse.setTotalAmount(order.getTotalAmount());
    orderResponse.setPaymentMethod(order.getPaymentMethod());
    orderResponse.setPaymentStatus(order.getPaymentStatus().getValue());
    orderResponse.setOrderStatus(order.getOrderStatus().getValue());
    orderResponse.setShippingMethod(order.getShippingMethod().getValue());
    orderResponse.setTrackingNumber(order.getTrackingNumber());
    orderResponse.setCreatedAt(order.getCreatedAt().toString());
    orderResponse.setUpdatedAt(order.getUpdatedAt() != null ? order.getUpdatedAt().toString() : null);
    return orderResponse;
  }



}
