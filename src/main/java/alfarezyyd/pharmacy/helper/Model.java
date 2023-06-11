package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.exception.ServerError;
import alfarezyyd.pharmacy.model.entity.Address;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.web.response.AddressResponse;
import alfarezyyd.pharmacy.model.web.response.CustomerResponse;
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

  public static void writeToResponseBodyError(HttpServletResponse resp) {
    Map<String, LinkedList<Object>> errorResponse = new HashMap<>();
    errorResponse.put("client_errors", ClientError.getAllClientError());
    errorResponse.put("server_errors", ServerError.getAllServerError());
    try {
      Map<String, Object> webResponse = new HashMap<>();
      webResponse.put("code", HttpStatus.BAD_REQUEST);
      webResponse.put("message", "Failed! Error has Occured");
      webResponse.put("data", null);
      webResponse.put("errors", errorResponse);
      resp.setHeader("Content-Type", "application/json");
      resp.getWriter().println(JSONUtil.getObjectMapper().writeValueAsString(webResponse));
      ClientError.getValidationErrors().clear();
      ClientError.getActionErrors().clear();
      ServerError.getDatabaseErrors().clear();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
