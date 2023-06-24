package alfarezyyd.pharmacy.util;

import alfarezyyd.pharmacy.model.entity.trait.HasCustomerId;
import alfarezyyd.pharmacy.model.entity.trait.Identifiable;
import alfarezyyd.pharmacy.model.entity.User;

import java.util.LinkedList;

public class SearchUtil {
  public static <T extends Identifiable> T binarySearch(LinkedList<T> linkedListOfElement, Long id) {
    long startIndex = 0L;
    long endIndex = linkedListOfElement.size() - 1;
    while (startIndex <= endIndex) {
      long midIndex = (startIndex + endIndex) / 2;
      T currentValue = linkedListOfElement.get((int) midIndex);
      if (currentValue.getId().equals(id)) {
        return currentValue;
      } else if (currentValue.getId() < id) {
        startIndex = midIndex + 1;
      } else {
        endIndex = midIndex - 1;
      }
    }
    return null;
  }

  public static <T extends HasCustomerId> LinkedList<T> sequentialSearchByCustomerId(LinkedList<T> linkedListOfElement, Long customerId) {
    LinkedList<T> foundedElement = new LinkedList<>();
    for (var element : linkedListOfElement) {
      if (element.getCustomerId().equals(customerId)) {
        foundedElement.add(element);
      }
    }
    return foundedElement;
  }


  public static User sequentialSearchByEmail(LinkedList<User> linkedListOfUser, String userEmail) {
    for (var user : linkedListOfUser) {
      if (user.getEmail().equals(userEmail)) {
        return user;
      }
    }
    return null;
  }

    public static User sequentialSearchByEmployeeId(LinkedList<User> linkedListOfUser, Long employeeId) {
    for (var user : linkedListOfUser) {
      if (user.getEmployeeId().equals(employeeId)) {
        return user;
      }
    }
    return null;
  }
}
