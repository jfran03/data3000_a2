package data3000_a2;

import java.util.*;

public class QuickSort {

    // Generic quicksort - T must implement Comparable
    public static <T extends Comparable<T>> long sort(ArrayList<T> list) {
        long start = System.currentTimeMillis();
        quickSort(list, 0, list.size() - 1);
        return System.currentTimeMillis() - start;
    }

    private static <T extends Comparable<T>> void quickSort(ArrayList<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(ArrayList<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}