package alfarezyyd.pharmacy.util;

import java.util.Comparator;
import java.util.List;

public class SortingUtil {
  public static class QuickSort {
    public static <T> void quickSort(List<T> linkedList, Comparator<T> comparator) {
      quickSortLogic(linkedList, 0, linkedList.size() - 1, comparator);
    }

    private static <T> void quickSortLogic(List<T> linkedList, int low, int high, Comparator<T> comparator) {
      if (low < high) {
        int partitionIndex = partition(linkedList, low, high, comparator);
        quickSortLogic(linkedList, low, partitionIndex - 1, comparator); // Partisi Kiri
        quickSortLogic(linkedList, partitionIndex + 1, high, comparator); // Partisi Kanan
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

  public static class InsertionSort {
    public static <T> void insertionSort(List<T> linkedList, Comparator<T> comparator) {
      insertionSortLogic(linkedList, comparator);
    }

    private static <T> void insertionSortLogic(List<T> linkedList, Comparator<T> comparator) {
      for (int i = 1; i < linkedList.size(); i++) {
        T currentElement = linkedList.get(i);
        int j = i - 1;
        while (j >= 0 && comparator.compare(currentElement, linkedList.get(j)) <= 0) {
          linkedList.set((j + 1), linkedList.get(j));
          j--;
        }
        // Swapping
        linkedList.set(j + 1, currentElement);
      }
    }
  }

  public static class HeapSort {
    public static <T> void heapSort(List<T> linkedList, Comparator<T> comparator) {
      heapSortLogic(linkedList, linkedList.size(), comparator);
    }

    private static <T> void heapSortLogic(List<T> linkedList, int length, Comparator<T> comparator) {
      // Rearrange Array
      for (int i = length / 2 - 1; i >= 0; i--) {
        heapify(linkedList, length, i, comparator);
      }
      // One by one extract an element from heap
      for (int i = length - 1; i > 0; i--) {
        // Move current root to end
        swap(linkedList, i, 0);
        // call max heapify on the reduced heap
        heapify(linkedList, i, 0, comparator);
      }
    }

    private static <T> void heapify(List<T> linkedList, int length, int currentIndex, Comparator<T> comparator) {
      int largestIndex = currentIndex; // Initialize largest as root
      int leftChild = 2 * currentIndex + 1; // left = 2*i + 1
      int rightChild = 2 * currentIndex + 2; // right = 2*i + 2
      // If left child is larger than root
      if (leftChild < length && comparator.compare(linkedList.get(largestIndex), linkedList.get(leftChild)) <= 0) {
        largestIndex = leftChild;
      }
      // If right child is larger than largest so far
      if (rightChild < length && comparator.compare(linkedList.get(largestIndex), linkedList.get(rightChild)) <= 0) {
        largestIndex = rightChild;
      }
      // If largest is not root
      if (largestIndex != currentIndex) {
        swap(linkedList, currentIndex, largestIndex);
        // Recursively heapify the affected subtree
        heapify(linkedList, length, largestIndex, comparator);
      }
    }
  }

  private static <T> void swap(List<T> linkedList, int i, int j) {
    T temp = linkedList.get(i);
    linkedList.set(i, linkedList.get(j));
    linkedList.set(j, temp);
  }
}
