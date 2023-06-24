package alfarezyyd.pharmacy.util;

import java.util.Comparator;
import java.util.List;

public class SortingUtil {
  public static class QuickSort {
    public static <T> void quickSort(List<T> linkedList, Comparator<T> comparator) {
      quickSortRecursive(linkedList, 0, linkedList.size() - 1, comparator);
    }

    private static <T> void quickSortRecursive(List<T> linkedList, int low, int high, Comparator<T> comparator) {
      if (low < high) {
        int partitionIndex = partition(linkedList, low, high, comparator);
        quickSortRecursive(linkedList, low, partitionIndex - 1, comparator);
        quickSortRecursive(linkedList, partitionIndex + 1, high, comparator);
      }
    }

    private static <T> int partition(List<T> linkedList, int low, int high, Comparator<T> comparator) {
      T pivot = linkedList.get(high);
      int i = low - 1;

      for (int j = low; j < high; j++) {
        if (comparator.compare(linkedList.get(j), pivot) <= 0) {
          i++;
          swap(linkedList, i, j);
        }
      }

      swap(linkedList, i + 1, high);
      return i + 1;
    }
  }

  public static class SelectionSort {
    public static <T> void selectionSort(List<T> linkedList, Comparator<T> comparator) {
      selectionSortLogic(linkedList, comparator);
    }

    private static <T> void selectionSortLogic(List<T> linkedList, Comparator<T> comparator) {
      for (int i = 0; i < linkedList.size() - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < linkedList.size(); j++) {
          if (comparator.compare(linkedList.get(j), linkedList.get(minIndex)) <= 0) {
            minIndex = j;
          }
        }
        // Swapping
        swap(linkedList, i, minIndex);
      }
    }
  }

  public static class BubbleSort {

  }

  private static <T> void swap(List<T> linkedList, int i, int j) {
    T temp = linkedList.get(i);
    linkedList.set(i, linkedList.get(j));
    linkedList.set(j, temp);
  }
}