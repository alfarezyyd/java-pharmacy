package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.entity.Medicine;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
import alfarezyyd.pharmacy.model.web.response.MedicineResponse;
import alfarezyyd.pharmacy.util.JSONUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.*;

public class Model {
  public static CustomerResponse convertToCustomerResponse(Customer customer) {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setId(customer.getId());
    customerResponse.setFullName(customer.getFullName());
    customerResponse.setDateOfBirth(customer.getDateOfBirth().toString());
    customerResponse.setGender(customer.getGender());
    customerResponse.setPhone(customer.getPhone());
    customerResponse.setCreatedAt(customer.getCreatedAt() != null ? customer.getCreatedAt().toString() : null);
    customerResponse.setCreatedAt(customer.getUpdatedAt() != null ? customer.getUpdatedAt().toString() : null);
    customerResponse.setCreatedAt(customer.getDeletedAt() != null ? customer.getDeletedAt().toString() : null);
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
    addressResponse.setUpdatedAt(Optional.of(address.getUpdatedAt().toString()).orElse(null));
    addressResponse.setDeletedAt(Optional.of(address.getDeletedAt().toString()).orElse(null));
    return addressResponse;
  }

  public static MedicineResponse convertToMedicineResponse(Medicine medicine) {
    MedicineResponse medicineResponse = new MedicineResponse();
    medicineResponse.setId(medicine.getId());
    medicineResponse.setName(medicine.getName());
    medicineResponse.setDescription(medicine.getDescription());
    medicineResponse.setBrand(medicine.getBrand());
    medicineResponse.setPrice(medicine.getPrice());
    medicineResponse.setStock(medicine.getStock());
    medicineResponse.setCreatedAt(medicine.getCreatedAt().toString());
    medicineResponse.setUpdatedAt(Optional.of(medicine.getUpdatedAt().toString()).orElse(null));
    medicineResponse.setDeletedAt(Optional.of(medicine.getUpdatedAt().toString()).orElse(null));
    return medicineResponse;
  }

  public static void writeToResponseBodySuccess(HttpServletResponse resp, Object responseData) {
    try {
      Map<String, Object> webResponse = new HashMap<>();
      webResponse.put("code", HttpStatus.OK);
      webResponse.put("message", "Success!");
      webResponse.put("data", responseData);
      webResponse.put("errors", null);
      resp.setHeader("Content-Type", "application/json");
      resp.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(webResponse));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void writeToResponseBodyError(ServerError serverError, ClientError clientError, HttpServletResponse resp) {
    Map<String, Map<String, LinkedList<?>>> errorsResponse = new HashMap<>();
    errorsResponse.put("client_errors", clientError.getClientErrors());
    errorsResponse.put("action_errors", serverError.getServerErrors());
    try {
      Map<String, Object> webResponse = new HashMap<>();
      webResponse.put("code", HttpStatus.BAD_REQUEST);
      webResponse.put("message", "Failed! Error has Occured");
      webResponse.put("data", null);
      webResponse.put("errors", errorsResponse);
      resp.setHeader("Content-Type", "application/json");
      resp.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(webResponse));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
