package data3000_a2;

import java.util.*;

public class BinarySearch {
    public static int search(ArrayList<Employee> employees, String name){
        
        // naive implementation, replace w/ binary search

        for (Employee employee : employees) {
            if (employee.name.equals(name)) {
                return employees.indexOf(employee); //LOL
            }
        }

        return -1;
    }
}
