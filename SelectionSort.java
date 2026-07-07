package data3000_a2;

import java.util.*;

public class SelectionSort {
    public static long sort(ArrayList<Employee> list) {
        long start = System.currentTimeMillis();

        //This will get the number of elements within the list.
        int e = list.size();
        //This gets the smallest elements from the list.
        for (int i = 0; i < e - 1; i++) {
        	//This gets an index from the list.
        	int min_idx = i;
        	//This for loop will iterate through all elements of the data.
        	for (int j = i + 1; j < e; j++) {
        		//This gets the data.
        		float salaryJ = list.get(j).calcHourlySalary();
                float salaryMin = list.get(min_idx).calcHourlySalary();
                //This sorts the data from smallest to biggest.
                if (salaryJ < salaryMin) {
                	min_idx = j;
                }
        	}
        	//This moves the elements to their correct position within the sorted array.
        	Employee temp = list.get(i);
            list.set(i, list.get(min_idx));
            list.set(min_idx, temp);
        }                
        
        return System.currentTimeMillis() - start;
    }
}

//These resources were used to create this algorithm and understand the individual components.
//https://www.geeksforgeeks.org/dsa/selection-sort-algorithm-2/
//https://www.geeksforgeeks.org/java/list-size-method-in-java-with-examples/
//I used this AI application to help create the sorting algorithm.
//https://copilot.microsoft.com/
