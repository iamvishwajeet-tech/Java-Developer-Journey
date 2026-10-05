import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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

    // =========================
    // ID
    // =========================

    public void setId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID must be greater than 0"
            );
        }

        this.id = id;
    }

    public int getId() {
        return id;
    }

    // =========================
    // NAME
    // =========================

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

    public String getName() {
        return name;
    }

    // =========================
    // DEPARTMENT
    // =========================

    public void setDepartment(String department) {

        if (department == null ||
                department.trim().isEmpty()) {

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

    public String getDepartment() {
        return department;
    }

    // =========================
    // SALARY
    // =========================

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

    public double getSalary() {
        return salary;
    }

    // =========================
    // INCREASE SALARY
    // =========================

    public void increaseSalary(double percentage) {

        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException(
                    "Percentage must be between 1 and 100"
            );
        }

        salary = salary + (salary * percentage / 100);
    }

    // =========================
    // ANNUAL SALARY
    // =========================

    public double getAnnualSalary() {
        return salary * 12;
    }

    // =========================
    // DISPLAY
    // =========================

    public void displayEmployee() {

        System.out.println("-----------------------------");
        System.out.println("ID         : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
        System.out.println("Salary     : ₹" + salary);
        System.out.println("Annual     : ₹" + getAnnualSalary());
        System.out.println("-----------------------------");
    }
}


// =====================================================
// MAIN CLASS
// =====================================================

public class Main {

    static Scanner sc = new Scanner(System.in);

    /*
     * Day 6:
     * Employee ID = Key
     * Employee Object = Value
     */
    static HashMap<Integer, Employee> employees =
            new HashMap<>();


    // =====================================================
    // ADD EMPLOYEE
    // =====================================================

    public static void addEmployee() {

        try {

            System.out.println("\n===== ADD EMPLOYEE =====");

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            // Day 6: Duplicate ID check using HashMap
            if (employees.containsKey(id)) {

                System.out.println(
                        "Employee ID already exists!"
                );

                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee employee =
                    new Employee(
                            id,
                            name,
                            department,
                            salary
                    );

            // Add to HashMap
            employees.put(id, employee);

            System.out.println(
                    "Employee added successfully!"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // GET EMPLOYEE BY ID
    // =====================================================

    public static Employee getEmployeeById(int id) {

        return employees.get(id);
    }


    // =====================================================
    // SEARCH EMPLOYEE
    // =====================================================

    public static void searchEmployee() {

        System.out.println(
                "\n===== SEARCH EMPLOYEE ====="
        );

        System.out.print(
                "Enter Employee ID: "
        );

        int id = sc.nextInt();

        /*
         * HashMap directly finds employee
         * using ID as key.
         */
        Employee employee =
                employees.get(id);

        if (employee != null) {

            System.out.println(
                    "\nEmployee Found!"
            );

            employee.displayEmployee();

        } else {

            System.out.println(
                    "Employee with ID "
                    + id
                    + " not found."
            );
        }
    }


    // =====================================================
    // DISPLAY ALL EMPLOYEES
    // =====================================================

    public static void displayAllEmployees() {

        System.out.println(
                "\n===== ALL EMPLOYEES ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }

        for (Employee employee :
                employees.values()) {

            employee.displayEmployee();
        }
    }


    // =====================================================
    // UPDATE EMPLOYEE
    // =====================================================

    public static void updateEmployee() {

        System.out.println(
                "\n===== UPDATE EMPLOYEE ====="
        );

        System.out.print(
                "Enter Employee ID: "
        );

        int id = sc.nextInt();
        sc.nextLine();

        Employee employee =
                employees.get(id);

        if (employee == null) {

            System.out.println(
                    "Employee not found."
            );

            return;
        }

        System.out.println("\n1. Update Name");
        System.out.println("2. Update Department");
        System.out.println("3. Update Salary");
        System.out.println("4. Cancel");

        System.out.print(
                "Enter your choice: "
        );

        int choice = sc.nextInt();
        sc.nextLine();

        try {

            switch (choice) {

                case 1:

                    System.out.print(
                            "Enter New Name: "
                    );

                    String newName =
                            sc.nextLine();

                    employee.setName(newName);

                    System.out.println(
                            "Name updated successfully!"
                    );

                    break;


                case 2:

                    System.out.print(
                            "Enter New Department: "
                    );

                    String newDepartment =
                            sc.nextLine();

                    employee.setDepartment(
                            newDepartment
                    );

                    System.out.println(
                            "Department updated successfully!"
                    );

                    break;


                case 3:

                    System.out.print(
                            "Enter New Salary: "
                    );

                    double newSalary =
                            sc.nextDouble();

                    employee.setSalary(
                            newSalary
                    );

                    System.out.println(
                            "Salary updated successfully!"
                    );

                    break;


                case 4:

                    System.out.println(
                            "Update cancelled."
                    );

                    break;


                default:

                    System.out.println(
                            "Invalid choice!"
                    );
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // INCREASE SALARY
    // =====================================================

    public static void increaseSalary() {

        System.out.println(
                "\n===== INCREASE SALARY ====="
        );

        System.out.print(
                "Enter Employee ID: "
        );

        int id = sc.nextInt();

        Employee employee =
                employees.get(id);

        if (employee == null) {

            System.out.println(
                    "Employee not found."
            );

            return;
        }

        System.out.print(
                "Enter Increase Percentage: "
        );

        double percentage =
                sc.nextDouble();

        try {

            double oldSalary =
                    employee.getSalary();

            employee.increaseSalary(
                    percentage
            );

            double newSalary =
                    employee.getSalary();

            System.out.println(
                    "\nSalary updated successfully!"
            );

            System.out.println(
                    "Old Salary : ₹" + oldSalary
            );

            System.out.println(
                    "New Salary : ₹" + newSalary
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // DELETE EMPLOYEE
    // =====================================================

    public static void deleteEmployee() {

        System.out.println(
                "\n===== DELETE EMPLOYEE ====="
        );

        System.out.print(
                "Enter Employee ID: "
        );

        int id = sc.nextInt();

        if (!employees.containsKey(id)) {

            System.out.println(
                    "Employee not found."
            );

            return;
        }

        Employee removed =
                employees.remove(id);

        System.out.println(
                "Employee deleted successfully!"
        );

        System.out.println(
                "Deleted: "
                + removed.getName()
        );
    }


    // =====================================================
    // DEPARTMENT-WISE EMPLOYEES
    // =====================================================

    public static void displayByDepartment() {

        System.out.println(
                "\n===== DEPARTMENT SEARCH ====="
        );

        System.out.print(
                "Enter Department: "
        );

        sc.nextLine();

        String department =
                sc.nextLine();

        boolean found = false;

        for (Employee employee :
                employees.values()) {

            if (employee.getDepartment()
                    .equalsIgnoreCase(department)) {

                employee.displayEmployee();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No employees found in "
                    + department
                    + " department."
            );
        }
    }


    // =====================================================
    // HIGHEST SALARY
    // =====================================================

    public static void findHighestSalaryEmployee() {

        System.out.println(
                "\n===== HIGHEST SALARY EMPLOYEE ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }

        Employee highest = null;

        for (Employee employee :
                employees.values()) {

            if (highest == null ||
                    employee.getSalary()
                    > highest.getSalary()) {

                highest = employee;
            }
        }

        highest.displayEmployee();
    }


    // =====================================================
    // EMPLOYEE COUNT
    // =====================================================

    public static void displayEmployeeCount() {

        System.out.println(
                "\n===== EMPLOYEE COUNT ====="
        );

        System.out.println(
                "Total Employees: "
                + employees.size()
        );
    }


    // =====================================================
    // SALARY STATISTICS
    // =====================================================

    public static void salaryStatistics() {

        System.out.println(
                "\n===== SALARY STATISTICS ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }

        double totalSalary = 0;

        double highestSalary = 0;

        double lowestSalary =
                Double.MAX_VALUE;


        for (Employee employee :
                employees.values()) {

            double salary =
                    employee.getSalary();

            totalSalary += salary;

            if (salary > highestSalary) {

                highestSalary = salary;
            }

            if (salary < lowestSalary) {

                lowestSalary = salary;
            }
        }


        double averageSalary =
                totalSalary / employees.size();


        System.out.println(
                "Total Salary   : ₹"
                + totalSalary
        );

        System.out.println(
                "Average Salary : ₹"
                + averageSalary
        );

        System.out.println(
                "Highest Salary : ₹"
                + highestSalary
        );

        System.out.println(
                "Lowest Salary  : ₹"
                + lowestSalary
        );
    }


    // =====================================================
    // SORT BY SALARY
    // =====================================================

    public static void sortBySalary() {

        System.out.println(
                "\n===== SORT BY SALARY ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }

        /*
         * HashMap itself is not designed
         * for sorted output.
         *
         * Therefore we create an ArrayList
         * of Employee objects.
         */
        ArrayList<Employee> list =
                new ArrayList<>(
                        employees.values()
                );


        System.out.println(
                "1. Low to High"
        );

        System.out.println(
                "2. High to Low"
        );

        System.out.print(
                "Enter choice: "
        );

        int choice = sc.nextInt();


        if (choice == 1) {

            list.sort(
                    Comparator.comparingDouble(
                            Employee::getSalary
                    )
            );

        } else if (choice == 2) {

            list.sort(
                    Comparator.comparingDouble(
                            Employee::getSalary
                    ).reversed()
            );

        } else {

            System.out.println(
                    "Invalid choice!"
            );

            return;
        }


        for (Employee employee : list) {

            employee.displayEmployee();
        }
    }


    // =====================================================
    // SALARY RANGE SEARCH
    // =====================================================

    public static void findEmployeesBySalaryRange() {

        System.out.println(
                "\n===== SALARY RANGE SEARCH ====="
        );

        System.out.print(
                "Enter Minimum Salary: "
        );

        double minSalary =
                sc.nextDouble();

        System.out.print(
                "Enter Maximum Salary: "
        );

        double maxSalary =
                sc.nextDouble();


        if (minSalary <= 0 ||
                maxSalary <= 0) {

            System.out.println(
                    "Salary must be greater than 0."
            );

            return;
        }


        if (minSalary > maxSalary) {

            System.out.println(
                    "Invalid salary range."
            );

            return;
        }


        boolean found = false;


        for (Employee employee :
                employees.values()) {

            double salary =
                    employee.getSalary();

            if (salary >= minSalary &&
                    salary <= maxSalary) {

                employee.displayEmployee();

                found = true;
            }
        }


        if (!found) {

            System.out.println(
                    "No employees found."
            );
        }
    }


    // =====================================================
    // DEPARTMENT STATISTICS
    // =====================================================

    public static void departmentStatistics() {

        System.out.println(
                "\n===== DEPARTMENT STATISTICS ====="
        );

        int it = 0;
        int hr = 0;
        int finance = 0;
        int marketing = 0;
        int sales = 0;


        for (Employee employee :
                employees.values()) {

            String department =
                    employee.getDepartment();


            if (department.equalsIgnoreCase("IT")) {

                it++;

            } else if (
                    department.equalsIgnoreCase("HR")) {

                hr++;

            } else if (
                    department.equalsIgnoreCase("Finance")) {

                finance++;

            } else if (
                    department.equalsIgnoreCase("Marketing")) {

                marketing++;

            } else if (
                    department.equalsIgnoreCase("Sales")) {

                sales++;
            }
        }


        System.out.println(
                "IT        : " + it
        );

        System.out.println(
                "HR        : " + hr
        );

        System.out.println(
                "Finance   : " + finance
        );

        System.out.println(
                "Marketing : " + marketing
        );

        System.out.println(
                "Sales     : " + sales
        );
    }


    // =====================================================
    // TOP 3 HIGHEST PAID EMPLOYEES
    // =====================================================

    public static void topThreeHighestPaidEmployees() {

        System.out.println(
                "\n===== TOP 3 HIGHEST PAID ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }


        ArrayList<Employee> list =
                new ArrayList<>(
                        employees.values()
                );


        list.sort(
                Comparator.comparingDouble(
                        Employee::getSalary
                ).reversed()
        );


        int limit =
                Math.min(3, list.size());


        for (int i = 0; i < limit; i++) {

            Employee employee =
                    list.get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + employee.getName()
                    + " - ₹"
                    + employee.getSalary()
            );
        }
    }


    // =====================================================
    // DISPLAY EMPLOYEE IDs
    // =====================================================

    public static void displayAllEmployeeIds() {

        System.out.println(
                "\n===== EMPLOYEE IDs ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }

        for (Integer id :
                employees.keySet()) {

            System.out.println(id);
        }
    }


    // =====================================================
    // DISPLAY USING ENTRYSET
    // =====================================================

    public static void displayUsingEntrySet() {

        System.out.println(
                "\n===== ID + EMPLOYEE ====="
        );

        if (employees.isEmpty()) {

            System.out.println(
                    "No employees available."
            );

            return;
        }

        for (Map.Entry<Integer, Employee> entry :
                employees.entrySet()) {

            Integer id = entry.getKey();

            Employee employee =
                    entry.getValue();

            System.out.println(
                    "ID: "
                    + id
                    + " → "
                    + employee.getName()
            );
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        while (true) {

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "       EMPLOYEE MANAGEMENT SYSTEM"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "1.  Add Employee"
            );

            System.out.println(
                    "2.  Display All Employees"
            );

            System.out.println(
                    "3.  Search Employee"
            );

            System.out.println(
                    "4.  Update Employee"
            );

            System.out.println(
                    "5.  Increase Salary"
            );

            System.out.println(
                    "6.  Delete Employee"
            );

            System.out.println(
                    "7.  Department-wise Employees"
            );

            System.out.println(
                    "8.  Highest Salary Employee"
            );

            System.out.println(
                    "9.  Employee Count"
            );

            System.out.println(
                    "10. Salary Statistics"
            );

            System.out.println(
                    "11. Sort Employees by Salary"
            );

            System.out.println(
                    "12. Salary Range Search"
            );

            System.out.println(
                    "13. Department Statistics"
            );

            System.out.println(
                    "14. Top 3 Highest Paid Employees"
            );

            System.out.println(
                    "15. Display All Employee IDs"
            );

            System.out.println(
                    "16. Display Using EntrySet"
            );

            System.out.println(
                    "17. Exit"
            );

            System.out.print(
                    "\nEnter your choice: "
            );


            int choice = sc.nextInt();


            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    displayAllEmployees();
                    break;

                case 3:
                    searchEmployee();
                    break;

                case 4:
                    updateEmployee();
                    break;

                case 5:
                    increaseSalary();
                    break;

                case 6:
                    deleteEmployee();
                    break;

                case 7:
                    displayByDepartment();
                    break;

                case 8:
                    findHighestSalaryEmployee();
                    break;

                case 9:
                    displayEmployeeCount();
                    break;

                case 10:
                    salaryStatistics();
                    break;

                case 11:
                    sortBySalary();
                    break;

                case 12:
                    findEmployeesBySalaryRange();
                    break;

                case 13:
                    departmentStatistics();
                    break;

                case 14:
                    topThreeHighestPaidEmployees();
                    break;

                case 15:
                    displayAllEmployeeIds();
                    break;

                case 16:
                    displayUsingEntrySet();
                    break;

                case 17:

                    System.out.println(
                            "\nThank you for using "
                            + "Employee Management System!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice! "
                            + "Please select 1-17."
                    );
            }
        }
    }
}
