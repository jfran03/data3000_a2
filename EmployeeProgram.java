package data3000_a2;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class EmployeeProgram{

    public static ArrayList<Employee> employees = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);
    public static String filePath;

    public static void main(String[] args){

        // welcome panel
        JOptionPane.showMessageDialog(null,
            "Employee Data Sorting and Searching Program!\n\nPress OK to Start",
            "Welcome to", JOptionPane.INFORMATION_MESSAGE);

        System.out.print("Enter the full path of employee data file <> ");
        filePath = SCANNER.nextLine();
        System.out.println("Read employee data from file " + filePath);

        // try-catch for file existing
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
            System.out.println("Loaded " + employees.size() + " employees.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // populate arraylist
        for (Employee e: employees){
             System.out.println("Employee ID: " + e.id + ", Name: " + e.name + ", Hourly Salary: " + e.calcHourlySalary());
        }
    }
}