package data3000_a2;

import java.util.*;

// naive implementation, change when specific algo is implemented
public class QuickSort {
    public static long sort(ArrayList<Employee> list) {
        long start = System.currentTimeMillis();
        list.sort(Comparator.comparing(n -> n.name));
        return System.currentTimeMillis() - start;
    }
}
