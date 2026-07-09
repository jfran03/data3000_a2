package data3000_a2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Headless test runner (no JOptionPane) to verify sample-run behavior.
 */
public class SampleTest {

    public static void main(String[] args) throws Exception {
        String file = args.length > 0 ? args[0] : "data/employeesWithoutRepeat.txt";
        String searchName = args.length > 1 ? args[1] : "Liz";

        ArrayList<Employee> employees = readEmployees(file);

        ArrayList<Employee> forSelection = new ArrayList<>(employees);
        ArrayList<Employee> forQuick = new ArrayList<>(employees);

        long selStart = System.currentTimeMillis();
        Employee[] selArr = forSelection.toArray(new Employee[0]);
        SelectionSort.sort(selArr, selArr.length);
        long selTime = System.currentTimeMillis() - selStart;

        long quickStart = System.currentTimeMillis();
        QuickSort.sort(forQuick);
        long quickTime = System.currentTimeMillis() - quickStart;

        Employee[] byName = forQuick.toArray(new Employee[0]);
        Employee searchKey = new Employee(0, searchName, 0, 0f, 0f, 0f, 0f);
        int index = BinarySearch.search(byName, searchKey);

        System.out.println("Read employee data from file " + file);
        System.out.println();
        System.out.println("The performance of our sorting algorithms");
        System.out.println("###########################################");
        System.out.println("Selection Sort Time > " + selTime + " ms");
        System.out.println("Quick Sort Time > " + quickTime + " ms");
        System.out.println("###########################################");
        System.out.println();
        System.out.println("Enter the name of the employee to search <> " + searchName);
        if (index != -1) {
            System.out.println("Employee found at index " + index);
        } else {
            System.out.println("Employee not found.");
        }

        new java.io.File("out").mkdirs();
        writeCsv("out/sortedemployeeBySalary.csv", selArr, selArr.length);
        writeCsv("out/sortedemployeeByName.csv", byName, byName.length);
    }

    private static void writeCsv(String path, Employee[] arr, int count) throws Exception {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(path))) {
            for (int i = 0; i < count; i++) {
                bw.write(arr[i].toString());
                bw.newLine();
            }
        }
    }

    private static ArrayList<Employee> readEmployees(String filePath) throws Exception {
        ArrayList<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                employees.add(new Employee(
                        Integer.parseInt(p[0]),
                        p[1],
                        (int) Float.parseFloat(p[2]),
                        Float.parseFloat(p[3]),
                        Float.parseFloat(p[4]),
                        Float.parseFloat(p[5]),
                        Float.parseFloat(p[6])
                ));
            }
        }
        return employees;
    }
}
