package data3000_a2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class EmployeeProgram {

    public static ArrayAndTime selectionName;
    public static ArrayAndTime quickName;
    public static ArrayAndTime selectionSalary;
    public static ArrayAndTime quickSalary;

    private static final Scanner SCANNER = new Scanner(System.in);
    public static String filePath = "None";
    public static String findPerson = "None";

    // plain class instead of record
    public static class ArrayAndTime {
        private final ArrayList<Employee> sortedArray;
        private final long sortingTime;

        public ArrayAndTime(ArrayList<Employee> sortedArray, long sortingTime) {
            this.sortedArray = sortedArray;
            this.sortingTime = sortingTime;
        }

        public ArrayList<Employee> sortedArray() {
            return sortedArray;
        }

        public long sortingTime() {
            return sortingTime;
        }
    }
    public static void main(String[] args) {

        // welcome panel
        JOptionPane.showMessageDialog(null,
                "Employee Data Sorting and Searching Program!\n\nPress OK to Start",
                "Welcome to", JOptionPane.INFORMATION_MESSAGE);

        while (filePath.equals("None")) {
            System.out.print("Enter the full path of employee data file <> ");
            filePath = SCANNER.nextLine();

            try {
                    selectionName = buildSortedArray(new BufferedReader(new FileReader(filePath)), "selection");
                    quickName = buildSortedArray(new BufferedReader(new FileReader(filePath)), "quick");
                    selectionSalary = buildSortedArray(new BufferedReader(new FileReader(filePath)), "selection");
                    quickSalary = buildSortedArray(new BufferedReader(new FileReader(filePath)), "quick");

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                filePath = "None"; // this is probably not a good way to do this lol
            }
        }

        System.out.println("Read employee data from file " + filePath);

        System.out.println("\nThe performance of our sorting algorithms");
        System.out.println("###########################################");
        System.out.println("Selection Sort Time > " + selectionName.sortingTime() + " ms");
        System.out.println("Quick Sort Time > " + quickName.sortingTime() + " ms");
        System.out.println("###########################################");

        System.out.println("\nWrite employee data sorted by their hourly salaries into file\n<>...\n");
        writeEmployeesToFile(selectionSalary.sortedArray(), "out/sortedemployeeBySalary.csv");

        System.out.println("Write employee data sorted by their names into file <>...");
        writeEmployeesToFile(quickName.sortedArray(), "out/sortedemployeeByName.csv");


        System.out.print("Enter the name of the employee to search <> ");
        findPerson = SCANNER.nextLine();

        //changed to sort by name
        int index = BinarySearch.search(
            quickName.sortedArray().toArray(new Employee[0]),
            new Employee(0, findPerson, 0, 0f, 0f, 0f, 0f));
        System.out.println(index != -1 ? "Employee found at index " + index : "Employee not found.");    
}

    // builds and sorts array based on FilePath, will return sorted array AND sorting time
    // DRY compliant cuz im lazy!
    public static ArrayAndTime buildSortedArray(BufferedReader br, String sortMethod) {
        long startTime = 0; // will start LITERALLY as soon as sorting starts, should cut out non-causal delays
        long endTime = 0;
        ArrayList<Employee> employees = new ArrayList<>();

        // yea, try-catch is redundant, but its ok!
        try (BufferedReader reader = br) {

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

            if (sortMethod.equals("selection")) {
                startTime = System.currentTimeMillis();
                SelectionSort.sort(employees);
                endTime = System.currentTimeMillis();
            } else if (sortMethod.equals("quick")) {
                startTime = System.currentTimeMillis();
                QuickSort.sort(employees);
                endTime = System.currentTimeMillis();
            } else {
                throw new IllegalArgumentException("Invalid sort method: " + sortMethod);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return new ArrayAndTime(employees, endTime - startTime);
    }

    public static void writeEmployeesToFile(ArrayList<Employee> employees, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                bw.write(employee.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
