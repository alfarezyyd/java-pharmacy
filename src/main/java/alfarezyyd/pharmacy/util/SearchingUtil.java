package alfarezyyd.pharmacy.util;

import alfarezyyd.pharmacy.model.entity.Identifiable;

import java.util.LinkedList;

public class SearchingUtil<T extends Identifiable> {
  public static <T extends Identifiable> T searchOperation(LinkedList<T> linkedListOfElement, Long id) {
    long startIndex = 0L;
    long endIndex = linkedListOfElement.size() - 1;
    while (startIndex < endIndex) {
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

}
