package data3000_a2;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class EmployeeProgram {

    public static ArrayList<Employee> employeeSortArrayOne = new ArrayList<>();
    public static ArrayList<Employee> employeeSortArrayTwo = new ArrayList<>();

    private static final Scanner SCANNER = new Scanner(System.in);
    public static String filePath;

    // builds and sorts array based on FilePath, will return sorted array AND sorting time
    public record ArrayAndTime(ArrayList<Employee> sortedArray, long sortingTime) {}

    public static void main(String[] args) {

        // welcome panel
        JOptionPane.showMessageDialog(null,
                "Employee Data Sorting and Searching Program!\n\nPress OK to Start",
                "Welcome to", JOptionPane.INFORMATION_MESSAGE);

        System.out.print("Enter the full path of employee data file <> ");
        filePath = SCANNER.nextLine();
        System.out.println("Read employee data from file " + filePath);

        ArrayAndTime selectionName = buildSortedArray(filePath, "selection");
        ArrayAndTime quickName = buildSortedArray(filePath, "quick");

        ArrayAndTime selectionSalary = buildSortedArray(filePath, "selection");
        ArrayAndTime quickSalary = buildSortedArray(filePath, "quick");

        System.out.println("\nThe performance of our sorting algorithms");
        System.out.println("###########################################");
        System.out.println("Selection Sort Time > " + selectionName.sortingTime() + " ms");
        System.out.println("Quick Sort Time > " + quickName.sortingTime() + " ms");
        System.out.println("###########################################");

        System.out.println("\nWrite employee data sorted by their hourly salaries into file\n<>...\n");
        writeEmployeesToFile(selectionSalary.sortedArray(), "out/sortedemployeeBySalary.csv");

        System.out.println("Write employee data sorted by their names into file <>...");
        writeEmployeesToFile(selectionName.sortedArray(), "out/sortedemployeeByName.csv");
    }

    // DRY compliant cuz im lazy!
    public static ArrayAndTime buildSortedArray(String filePath, String sortMethod) {
        long startTime=0; // will start LITERALLY as soon as sorting starts, should cut out non-causal delays
        long endTime=0;
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
