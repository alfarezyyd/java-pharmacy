package alfarezyyd.pharmacy.helper;

import alfarezyyd.pharmacy.exception.ClientError;
import alfarezyyd.pharmacy.model.entity.Customer;
import alfarezyyd.pharmacy.model.entity.Employee;
import alfarezyyd.pharmacy.model.entity.Medicine;
import alfarezyyd.pharmacy.util.SortingUtil;

import java.util.Comparator;

import java.util.LinkedList;

public class SortingMapping {
  public static void mappingCustomerSorting(String sortedBy, String algorithm, ClientError clientError, LinkedList<Customer> allCustomer) {
    switch (algorithm) {
      case "quick-sort" -> {
        switch (sortedBy) {
          case "full-name" -> SortingUtil.QuickSort.quickSort(allCustomer, Comparator.comparing(Customer::getFullName));
          case "date-of-birth" ->
              SortingUtil.QuickSort.quickSort(allCustomer, Comparator.comparing(Customer::getDateOfBirth));
          case "created-at" ->
              SortingUtil.QuickSort.quickSort(allCustomer, Comparator.comparing(Customer::getCreatedAt));
          default ->
              clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
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
          default ->
              clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
        }
      }
      case "insertion-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.InsertionSort.insertionSort(allCustomer, Comparator.comparing(Customer::getFullName));
          case "date-of-birth" ->
              SortingUtil.InsertionSort.insertionSort(allCustomer, Comparator.comparing(Customer::getDateOfBirth));
          case "created-at" ->
              SortingUtil.InsertionSort.insertionSort(allCustomer, Comparator.comparing(Customer::getCreatedAt));
          default ->
              clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
        }
      }
      case "heap-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.HeapSort.heapSort(allCustomer, Comparator.comparing(Customer::getFullName));
          case "date-of-birth" ->
              SortingUtil.HeapSort.heapSort(allCustomer, Comparator.comparing(Customer::getDateOfBirth));
          case "created-at" ->
              SortingUtil.HeapSort.heapSort(allCustomer, Comparator.comparing(Customer::getCreatedAt));
          default ->
              clientError.addActionError("get all customer with sorted by " + sortedBy, "invalid! data customer can't sorted by " + sortedBy);
        }
      }
      default ->
          clientError.addActionError("get all customer sorted with algorithm " + algorithm, "invalid! data customer can't sorted by algorithm " + algorithm);
    }
  }

  public static void mappingEmployeeSorting(String sortedBy, String algorithm, ClientError clientError, LinkedList<Employee> allEmployee) {
    switch (algorithm) {
      case "quick-sort" -> {
        switch (sortedBy) {
          case "full-name" -> SortingUtil.QuickSort.quickSort(allEmployee, Comparator.comparing(Employee::getFullName));
          case "hire-date" -> SortingUtil.QuickSort.quickSort(allEmployee, Comparator.comparing(Employee::getHireDate));
          case "created-at" ->
              SortingUtil.QuickSort.quickSort(allEmployee, Comparator.comparing(Employee::getCreatedAt));
          default ->
              clientError.addActionError("get all employee with sorted by " + sortedBy, "invalid! data employee can't sorted by " + sortedBy);
        }
      }
      case "selection-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.SelectionSort.selectionSort(allEmployee, Comparator.comparing(Employee::getFullName));
          case "hire-date" ->
              SortingUtil.SelectionSort.selectionSort(allEmployee, Comparator.comparing(Employee::getHireDate));
          case "created-at" ->
              SortingUtil.SelectionSort.selectionSort(allEmployee, Comparator.comparing(Employee::getCreatedAt));
          default ->
              clientError.addActionError("get all employee with sorted by " + sortedBy, "invalid! data employee can't sorted by " + sortedBy);
        }
      }
      case "insertion-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.InsertionSort.insertionSort(allEmployee, Comparator.comparing(Employee::getFullName));
          case "hire-date" ->
              SortingUtil.InsertionSort.insertionSort(allEmployee, Comparator.comparing(Employee::getHireDate));
          case "created-at" ->
              SortingUtil.InsertionSort.insertionSort(allEmployee, Comparator.comparing(Employee::getCreatedAt));
          default ->
              clientError.addActionError("get all employee with sorted by " + sortedBy, "invalid! data employee can't sorted by " + sortedBy);
        }
      }
       case "heap-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.HeapSort.heapSort(allEmployee, Comparator.comparing(Employee::getFullName));
          case "hire-date" ->
              SortingUtil.HeapSort.heapSort(allEmployee, Comparator.comparing(Employee::getHireDate));
          case "created-at" ->
              SortingUtil.HeapSort.heapSort(allEmployee, Comparator.comparing(Employee::getCreatedAt));
          default ->
              clientError.addActionError("get all employee with sorted by " + sortedBy, "invalid! data employee can't sorted by " + sortedBy);
        }
      }
      default ->
          clientError.addActionError("get all employee sorted with algorithm " + algorithm, "invalid! data employee can't sorted by algorithm" + algorithm);
    }
  }

  public static void mappingMedicineSorting(String sortedBy, String algorithm, ClientError clientError, LinkedList<Medicine> allMedicine) {
    switch (algorithm) {
      case "quick-sort" -> {
        switch (sortedBy) {
          case "full-name" -> SortingUtil.QuickSort.quickSort(allMedicine, Comparator.comparing(Medicine::getFullName));
          case "price" -> SortingUtil.QuickSort.quickSort(allMedicine, Comparator.comparing(Medicine::getPrice));
          case "created-at" ->
              SortingUtil.QuickSort.quickSort(allMedicine, Comparator.comparing(Medicine::getCreatedAt));
          default ->
              clientError.addActionError("get all medicine with sorted by " + sortedBy, "invalid! data medicine can't sorted by " + sortedBy);
        }
      }
      case "selection-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.SelectionSort.selectionSort(allMedicine, Comparator.comparing(Medicine::getFullName));
          case "price" ->
              SortingUtil.SelectionSort.selectionSort(allMedicine, Comparator.comparing(Medicine::getPrice));
          case "created-at" ->
              SortingUtil.SelectionSort.selectionSort(allMedicine, Comparator.comparing(Medicine::getCreatedAt));
          default ->
              clientError.addActionError("get all medicine with sorted by " + sortedBy, "invalid! data medicine can't sorted by " + sortedBy);
        }
      }
      case "insertion-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.InsertionSort.insertionSort(allMedicine, Comparator.comparing(Medicine::getFullName));
          case "price" ->
              SortingUtil.InsertionSort.insertionSort(allMedicine, Comparator.comparing(Medicine::getPrice));
          case "created-at" ->
              SortingUtil.InsertionSort.insertionSort(allMedicine, Comparator.comparing(Medicine::getCreatedAt));
          default ->
              clientError.addActionError("get all medicine with sorted by " + sortedBy, "invalid! data medicine can't sorted by " + sortedBy);
        }
      }
       case "heap-sort" -> {
        switch (sortedBy) {
          case "full-name" ->
              SortingUtil.HeapSort.heapSort(allMedicine, Comparator.comparing(Medicine::getFullName));
          case "price" ->
              SortingUtil.HeapSort.heapSort(allMedicine, Comparator.comparing(Medicine::getPrice));
          case "created-at" ->
              SortingUtil.HeapSort.heapSort(allMedicine, Comparator.comparing(Medicine::getCreatedAt));
          default ->
              clientError.addActionError("get all medicine with sorted by " + sortedBy, "invalid! data medicine can't sorted by " + sortedBy);
        }
      }
      default ->
          clientError.addActionError("get all medicine sorted with algorithm " + algorithm, "invalid! data medicine can't sorted by algorithm" + algorithm);
    }
  }
}
