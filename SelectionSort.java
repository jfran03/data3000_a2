package data3000_a2;

import java.util.*;

// naive implementation, change when specific algo is implemented
public class SelectionSort {
    public static long sort(ArrayList<Employee> list) {
        long start = System.currentTimeMillis();
        list.sort(Comparator.comparingInt(e -> e.id));
        return System.currentTimeMillis() - start;
    }
}
