import java.util.ArrayList;
import java.util.Scanner;

class Employee{
    private int id;
    private String name;
    private String department;
    private double salary;

   public Employee(int id,String name,String department,double salary){
        setid(id);
        setname(name);
        setdepartment(department);
        setsalary(salary);
    }

    //ID Validation
    public void setid(int id){
        if(id<=0){
            throw new IllegalArgumentException("Invalid ID!");
        }
        this.id=id;
    }

    //Name Validation
    public void setname(String name){
        if(name==null||name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be Empty!");
        }
        if(!name.matches("[a-zA-Z ]+")){
            throw new IllegalArgumentException("Name must be contain letter and spaces !");
        }
        if(name.trim().length()<2||name.trim().length()>30){
            throw new IllegalArgumentException("Length of name must be between 2 to 30 !");
        }
        this.name=name.trim();
    }

    //Department Validation
    public void setdepartment(String department){
        if(department==null||department.trim().isEmpty()){
            throw new IllegalArgumentException("Department cannot be empty !");
        }
        if(!department.matches("[a-zA-Z ]+")){
            throw new IllegalArgumentException("Department only contain letter and spaces !");
        }

        if(!department.equalsIgnoreCase("IT")&&
           !department.equalsIgnoreCase("Finance")&&
        !department.equalsIgnoreCase("Accounting")&&
        !department.equalsIgnoreCase("HR")&&
        !department.equalsIgnoreCase("Manager")&&
        !department.equalsIgnoreCase("Sales")){

            throw new IllegalArgumentException("invalid Department !");
           
    }
    this.department=department;
}

    //Salary Validation
    public void setsalary(double salary){
        if(salary<=0){
            throw new IllegalArgumentException("Salary cannot be negative or zero !");

        }
        if(salary>10000000){
            throw new IllegalArgumentException("Salary cannot greater than 1 crore !");
        }
        this.salary=salary;
    }

    //Getters
    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    
    }
    public String getdepartment(){
        return department;
    }
    public double getsalary(){
        return salary;
    }

    public void increaseSalary(double percentage){
        if(percentage<=0||percentage>100){
            throw new IllegalArgumentException("Invalid Percentage !");
        }
        salary=salary+(salary*percentage/100);
        
    }
     // Annual salary
    public double getAnnualSalary() {
        return salary * 12;
    }

    public void displayEmployee(){
        
        System.out.println("--------------------------------");
        System.out.println("ID            :"+id);
        System.out.println("Name          :"+name);
        System.out.println("Department    :"+department);
        System.out.println("Salary        :"+salary);
        System.out.println("Annual salary :"+getAnnualSalary());
        System.out.println("--------------------------------");

    }


}
   

public class Main {
  
      static Scanner sc=new Scanner(System.in);

        static ArrayList<Employee>employees=new ArrayList<>();
        
        

   //=======================
   //Delete Employee
   //=======================

   public static void deleteEmployee(){
      System.out.print("Enter the ID to delete:");
      int deleteId=sc.nextInt();
      boolean found=false;
      
      for(Employee emp:employees){
        if(emp.getid()==deleteId){
            employees.remove(emp);

            found=true;

            System.out.println("Employee deleted Successfully!");
            break;
        }
      }

      if(!found){
        System.out.println("Employee ID not Found !");
      }
    }



        //----------------------------------
        //ADD Employees
        //----------------------------------
      
        public static void addEmployee(){
        
            System.out.println("How many Employees do you want to add ? :");
            int n=sc.nextInt();
            sc.nextLine();

            for(int i=1;i<=n;i++){

            System.out.println("\nEnter Employeee "+i+" information");
            System.out.println("----------------------------------");

            try{
                System.out.print("Enter ID             :");
                int id=sc.nextInt();
                sc.nextLine();

                //Duplicate ID check
                boolean duplicate=false;

                for(Employee emp:employees){
                    if(emp.getid()==id){
                        duplicate=true;
                        break;
                    }
                }
                if(duplicate){
                    System.out.println("ID Already exists!");
                    i--;
                    continue;
                }
               
                System.out.print("Enter name           :");
                String name=sc.nextLine();

                System.out.print("Enter the Department :");
                String department=sc.nextLine();

                System.out.print("Enter the Salary     :");
                double salary=sc.nextDouble();

                sc.nextLine();

                Employee emp=new Employee(id,name,department,salary);

                employees.add(emp);
                System.out.println("Employee Added Successfully !");
                System.out.println("----------------------------------");

            } catch(IllegalArgumentException e){

                System.out.println("Error: "+e.getMessage());
                i--;
            }
        }
    }

       //===========================
        //Search Employee
        //===========================

        public static void searchEmployee(){
        System.out.print("\nEnter the Employee ID to search:");
        int searchid=sc.nextInt();

        boolean found=false;

        for(Employee emp:employees){
            if(emp.getid()==searchid){
                 System.out.println("\nEmployee found");
                 emp.displayEmployee();

                 found=true;
                 break;
            }
        }
        if(!found){
            System.out.println("Employeee not found");

        }
    }

        //==============================
        //Display All Employee
        //==============================
     public static void displayAllEmployee(){

        System.out.println("\n========All Employees==========");
        
        if(employees.isEmpty()) {
    System.out.println("No employees available!");
    return;
}

        for(Employee emp:employees){
            emp.displayEmployee();
        }
    }


        //===========================
        //Increase salary by ID
        //===========================
        
        public static void increaseSalary(){

          System.out.print("\nEnter the ID for salary Increase:");
          int salaryId=sc.nextInt();

           System.out.print("\nEnter the percentage for salary Increase:");
                double percentage=sc.nextDouble();

          boolean found =false;
          for(Employee emp:employees){
            if(emp.getid()==salaryId){

                try{
                    emp.increaseSalary(percentage);
                    System.out.println("\n Salary updated Successfully!");

                    emp.displayEmployee();
                    found=true;

                }catch(IllegalArgumentException e){
                    System.out.println("Error:"+e.getMessage());
                }
                    break;

                }
                }

                if(!found){
                    System.out.println("Employee Not Found!");

                }
            }

          public static void main(String[] args){

            while(true){

            System.out.println("===========Employee management System==========");
            System.out.println("--------------------------------------------------");

            //Option
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employee");
            System.out.println("3. Search Employee");
            System.out.println("4. Increase Employee Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
        
            System.out.println("Enter Your Choice :");
            int choice=sc.nextInt();

            switch(choice){
                case 1:
                    //ADD employee
                    addEmployee();
                    break;
                case 2:
                    //Display All employee
                    displayAllEmployee();
                    break;
                case 3:
                    //Search Employee
                    searchEmployee();
                    break;

                case 4:
                    //Increse Employee salary
                    increaseSalary();
                    break;
                    
                case 5:
                    //Delete Employee
                    deleteEmployee();
                    break;
                    
                case 6:
                    //Exit
                    System.out.println("Thank You ! Program Exited.");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Invalid Choice !");    
            }
        
        }
    }
}



        
        
        
        
                
            


            
        
        
          
        




         
        
    
    

