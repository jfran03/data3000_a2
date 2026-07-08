package data3000_a2;

public class SelectionSort {

    // Generic selection sort on an array of Comparable objects    
    public static <T extends Comparable<T>> void sort(T[] array, int count) {
        // Get the number of elements to sort
        for (int i = 0; i < count - 1; i++) {
             // Start with the current index as the smallest
            int minIdx = i;
            // Loop through the rest of the array to find a smaller salary
            for (int j = i + 1; j < count; j++) {
                // Compare hourly salaries of the two employees
                if (compareSalary(array[j], array[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            // Move the smallest element to its correct position
            T temp = array[i];
            array[i] = array[minIdx];
            array[minIdx] = temp;
        }
    }
     // Compare objects by hourly salary (assignment requirement for Employee)
    private static <T extends Comparable<T>> int compareSalary(T a, T b) {
        if (a instanceof Employee && b instanceof Employee) {
            Employee ea = (Employee) a;
            Employee eb = (Employee) b;
            return Float.compare(ea.calcHourlySalary(), eb.calcHourlySalary());
        }
        return a.compareTo(b);
    }
}
//These resources were used to create this algorithm and understand the individual components.
//https://www.geeksforgeeks.org/dsa/selection-sort-algorithm-2/
//https://www.geeksforgeeks.org/java/list-size-method-in-java-with-examples/
//I used this AI application to help create the sorting algorithm.
//https://copilot.microsoft.com/
