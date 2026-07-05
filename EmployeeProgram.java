package data3000_a2;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class EmployeeProgram {

    public static ArrayList<Employee> employeeSortArrayOne = new ArrayList<>();
    public static ArrayList<Employee> employeeSortArrayTwo = new ArrayList<>();

    private static final Scanner SCANNER = new Scanner(System.in);
    public static String filePath;

    public static void main(String[] args) {

        // welcome panel
        JOptionPane.showMessageDialog(null,
                "Employee Data Sorting and Searching Program!\n\nPress OK to Start",
                "Welcome to", JOptionPane.INFORMATION_MESSAGE);

        System.out.print("Enter the full path of employee data file <> ");
        filePath = SCANNER.nextLine();

        // try-catch for file existing
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                employeeSortArrayOne.add(new Employee(
                        Integer.parseInt(p[0]),
                        p[1],
                        (int) Float.parseFloat(p[2]),
                        Float.parseFloat(p[3]),
                        Float.parseFloat(p[4]),
                        Float.parseFloat(p[5]),
                        Float.parseFloat(p[6])
                ));
                employeeSortArrayTwo.add(new Employee(
                        Integer.parseInt(p[0]),
                        p[1],
                        (int) Float.parseFloat(p[2]),
                        Float.parseFloat(p[3]),
                        Float.parseFloat(p[4]),
                        Float.parseFloat(p[5]),
                        Float.parseFloat(p[6])
                ));
            }
            System.out.println("Read employee data from file " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // this isn't the smartest implementation, but it is in fact the simplest.

        long selectionTime = SelectionSort.sort(employeeSortArrayOne);
        long quickTime = QuickSort.sort(employeeSortArrayTwo);

        System.out.println("\nThe performance of our sorting algorithms");
        System.out.println("###########################################");
        System.out.println("Selection Sort Time > " + selectionTime + " ms");
        System.out.println("Quick Sort Time > " + quickTime + " ms");
        System.out.println("###########################################");
    }
}
