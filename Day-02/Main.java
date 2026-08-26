import java.util.ArrayList;
import java.util.Scanner;

class Employee {

    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        setId(id);
        setName(name);
        setDepartment(department);
        setSalary(salary);
    }

    // ID validation
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Employee ID must be greater than 0"
            );
        }

        this.id = id;
    }

    // Name validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Name cannot be empty"
            );
        }

        if (!name.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException(
                    "Name can contain only letters and spaces"
            );
        }

        this.name = name.trim();
    }

    // Department validation
    public void setDepartment(String department) {

        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Department cannot be empty"
            );
        }

        if (!department.equalsIgnoreCase("IT")
                && !department.equalsIgnoreCase("HR")
                && !department.equalsIgnoreCase("Finance")
                && !department.equalsIgnoreCase("Marketing")
                && !department.equalsIgnoreCase("Sales")) {

            throw new IllegalArgumentException(
                    "Invalid department"
            );
        }

        this.department = department.trim();
    }

    // Salary validation
    public void setSalary(double salary) {

        if (salary <= 0) {
            throw new IllegalArgumentException(
                    "Salary must be greater than 0"
            );
        }

        if (salary > 10000000) {
            throw new IllegalArgumentException(
                    "Salary cannot exceed 1 crore"
            );
        }

        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    // Increase salary
    public void increaseSalary(double percentage) {

        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException(
                    "Salary increase must be between 0 and 100%"
            );
        }

        salary = salary + (salary * percentage / 100);
    }

    // Annual salary
    public double getAnnualSalary() {
        return salary * 12;
    }

    // Display employee
    public void displayEmployee() {

        System.out.println("---------------------------");
        System.out.println("ID         : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
        System.out.println("Salary     : ₹" + salary);
        System.out.println("Annual     : ₹" + getAnnualSalary());
        System.out.println("---------------------------");
    }
}


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Employee> employees = new ArrayList<>();

        System.out.print("How many employees do you want to add? ");
        int n = sc.nextInt();
        sc.nextLine();

        // =========================
        // ADD EMPLOYEES
        // =========================

        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter Employee " + i + " Information");

            try {

                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                // Duplicate ID check
                boolean duplicate = false;

                for (Employee emp : employees) {
                    if (emp.getId() == id) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    System.out.println("ID already exists!");
                    i--;
                    continue;
                }

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Department: ");
                String department = sc.nextLine();

                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                Employee emp =
                        new Employee(id, name, department, salary);

                employees.add(emp);

                System.out.println("Employee added successfully!");

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

                i--;
            }
        }

        // =========================
        // DISPLAY ALL EMPLOYEES
        // =========================

        System.out.println("\n===== ALL EMPLOYEES =====");

        for (Employee emp : employees) {
            emp.displayEmployee();
        }


        // =========================
        // SEARCH EMPLOYEE
        // =========================

        System.out.print("\nEnter Employee ID to search: ");
        int searchId = sc.nextInt();

        boolean found = false;

        for (Employee emp : employees) {

            if (emp.getId() == searchId) {

                System.out.println("\nEmployee Found!");
                emp.displayEmployee();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found!");
        }


        // =========================
        // INCREASE SALARY BY ID
        // =========================

        System.out.print("\nEnter Employee ID for salary increase: ");
        int salaryId = sc.nextInt();

        System.out.print("Enter salary increase percentage: ");
        double percentage = sc.nextDouble();

        found = false;

        for (Employee emp : employees) {

            if (emp.getId() == salaryId) {

                try {

                    emp.increaseSalary(percentage);

                    System.out.println(
                            "\nSalary updated successfully!"
                    );

                    emp.displayEmployee();

                    found = true;

                } catch (IllegalArgumentException e) {

                    System.out.println(
                            "Error: " + e.getMessage()
                    );
                }

                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found!");
        }


        // =========================
        // FINAL EMPLOYEE LIST
        // =========================

        System.out.println("\n===== UPDATED EMPLOYEE LIST =====");

        for (Employee emp : employees) {
            emp.displayEmployee();
        }

        sc.close();
    }
}
