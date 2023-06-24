package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.util.SortingUtil;

import java.util.Comparator;

import java.util.LinkedList;

public class SortingHelper {
  public static void mappingCustomerSorting(String sortedBy, String algorithm, ClientError clientError, LinkedList<Customer> allCustomer) {
    switch (algorithm) {
      case "quick-sort" -> {
        switch (sortedBy) {
          case "full-name" -> SortingUtil.QuickSort.quickSort(allCustomer, Comparator.comparing(Customer::getFullName));
          case "date-of-birth" ->
              SortingUtil.QuickSort.quickSort(allCustomer, Comparator.comparing(Customer::getDateOfBirth));
          case "created-at" ->
              SortingUtil.QuickSort.quickSort(allCustomer, Comparator.comparing(Customer::getCreatedAt));
          default -> clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
        }
      }
      case "selection-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.SelectionSort.selectionSort(allCustomer, Comparator.comparing(Customer::getFullName));
          case "date-of-birth" ->
              SortingUtil.SelectionSort.selectionSort(allCustomer, Comparator.comparing(Customer::getDateOfBirth));
          case "created-at" ->
              SortingUtil.SelectionSort.selectionSort(allCustomer, Comparator.comparing(Customer::getCreatedAt));
          default -> clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
        }
      }
      default ->
          clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
    }
  }
}
