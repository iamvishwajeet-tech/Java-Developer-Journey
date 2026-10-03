import java.util.Scanner;
import java.util.ArrayList;

class Employee{
        private int id;
        private String name;
        private String department;
        private double salary;


       public Employee(int id, String name,String department,double salary){
            setId(id);
            setName(name);
            setDepartment(department);
            setSalary(salary);

        }
        
        //----------------------------------------------------
        //ID Validation
        //----------------------------------------------------

        public void setId(int id){

            if(id<=0){
                throw new IllegalArgumentException("Invalid Id !");
            }
            this.id=id;
        }

        public int getId(){
        return id;
       }

        //----------------------------------------------------
        //name Validation
        //------------------------------------------------------
        public void setName(String name){

            if(name==null||name.trim().isEmpty()){
                throw new IllegalArgumentException("Name cannot be empty !");
            }

            if(!name.trim().matches("[a-zA-Z ]+")){
                throw new IllegalArgumentException("Name only contains letter and spaces !");
            }

            if(name.length()<2||name.length()>30){
                throw new IllegalArgumentException("Name length between 2 and 30 !");
            }

             this.name=name.trim();
        
        }

        public String getName(){
        return name;
       }
         
        //--------------------------------------------------
        //Department Validation
        //--------------------------------------------------

        public void setDepartment(String department){

           if(department==null||department.trim().isEmpty()){

             throw new IllegalArgumentException("Department cannot be empty !");

           }

            if(!department.equalsIgnoreCase("IT")&&
               !department.equalsIgnoreCase("Finance")&&
               !department.equalsIgnoreCase("Accounting")&&
               !department.equalsIgnoreCase("HR")&&
               
               !department.equalsIgnoreCase("Manager")&&
               !department.equalsIgnoreCase("Sales")&&
               !department.equalsIgnoreCase("CEO")&&
               !department.equalsIgnoreCase("Product Manager")){

                throw new IllegalArgumentException("Invalid Department !");
        
        }

        this.department=department.trim();

    }

    public String getDepartment(){
        return department;
       }

    //----------------------------------------------------------
    //Salary Validation
    //-----------------------------------------------------------

    public void setSalary(double salary){

        if(salary<=0){
            throw new IllegalArgumentException("Salary cannot be negative !");
        }

        if(salary>10000000){
            throw new IllegalArgumentException("Salary cannot greater than 1 crore!");
        }
        this.salary=salary;
   
       }

        public Double getSalary(){
        return salary;
       }

       
      
       

       //-----------------------------------------------------
       //Increase Salary
       //-----------------------------------------------------

       public void increaseSalary(double percentage){

        if(percentage<=0||percentage>100){
            throw new IllegalArgumentException("Invalid Percentage !");
        }

        salary=salary+(salary*percentage/100);
       }

       //--------------------------------------------------------------
       //Annual Salary
       //-------------------------------------------------------------

       public double getAnnualSalary(){
           return salary*12;
       }

       


       //---------------------------------------------------------------
       //Display  Employee
       //--------------------------------------------------------------

       public void displayEmployee(){

        System.out.println("===================Employee==============");
        System.out.println("ID              :"+id);
        System.out.println("Name            :"+name);
        System.out.println("Department      :"+department);
        System.out.println("Salary          :"+salary);
        System.out.println("Annual Salary   :"+getAnnualSalary());
        System.out.println("-------------------------------------------------");   
    }

}

        //=======================================================
        //Main
        //=======================================================

        public class Main{

            static Scanner sc=new Scanner(System.in);
            static ArrayList<Employee>employees=new ArrayList<>();
    

               //----------------------------------------------------------
               //ADD EMPLOYEE
               //-----------------------------------------------------------

               public static void addEmployee(){

                try{
                    System.out.println("\n===================Add Employee=============");

                    System.out.print("Enter ID:        ");
                    int id=sc.nextInt();
                    sc.nextLine();

                    //Duplicate ID check  

                    if(findEmployeeById(id)!=null){
                          System.out.println("Employee ID already exists !");
                          return;
                    }

                    
                    

                    System.out.print("Enter name:         ");
                    String name=sc.nextLine();
                    
        
                    System.out.print("Department      :");
                    String department=sc.nextLine();

                    System.out.print("Salary          :");
                    double salary=sc.nextDouble();
                    

                    Employee employee=new Employee(id,name,department,salary);
                    employees.add(employee);

                    System.out.println("Employee Added Successfully !");

                }catch(IllegalArgumentException e){
                    System.out.print("Error:"+e.getMessage());
                    

                    }


                }
            
           

    //-----------------------------------------------------------
    //Display All Employee
    //----------------------------------------------------------
    public static void displayAllEmployees(){
            System.out.println("===============All Employees================");

            if(employees.isEmpty()){
                System.out.println("No Employees Available");
                return;
            }
             for(Employee employee:employees){

                employee.displayEmployee();
             }
            }    
            
        //==========================================================
            //Find Employee By ID
            //=========================================================

            public static Employee findEmployeeById(int id){
                for(Employee employee:employees){
                    if(employee.getId()==id){
                        return employee;

                    }
                }
                return null;

            }    

        //===========================
        //Search Employee
        //===========================

         public static void searchEmployee(){

                System.out.println("\n==============Search Employee========");
                System.out.print("Enter Employee ID:");
                int id=sc.nextInt();
                
                Employee employee=findEmployeeById(id);

                if(employee!=null){
                    System.out.println("\nEmployee found !");

                    employee.displayEmployee();

                }
                else{
                    System.out.println("Employee not Found !");
                }
            }



       //--------------------------------------------------------------
       //Update Employee
       //---------------------------------------------------------------


              public static void updateEmployee(){
            
              System.out.println("==========UPDATE EMPLOYEE==========");

              System.out.println("Enter the Employee ID:");
              int id=sc.nextInt();
              sc.nextLine();

              Employee employee=findEmployeeById(id);

              if(employee==null){
                System.out.println("Employee Not Found  !");
                return;
              }

              System.out.println("1. Update Name");
              System.out.println("2. Update Department");
              System.out.println("3. Update Salary");
              System.out.println("4. Cancel\n");

              System.out.print("Enter Your Choice: ");
              int choice=sc.nextInt();
              sc.nextLine();
       try{
              switch(choice){
                     case 1:
                        System.out.print("Enter new name:");
                        String newName=sc.nextLine();
                        employee.setName(newName);

                        System.out.println("Name Updated Successfully !");
                        break;
                        

                     case 2:
                        System.out.print("Enter the new Department:");

                        String newDepartment=sc.nextLine();
                        employee.setDepartment(newDepartment);
                        System.out.println("Department Updated Successfully !");
                        break;
                        
                     case 3:
                        System.out.print("Enter new Salary:");

                        double newSalary=sc.nextDouble();

                        employee.setSalary(newSalary);
                      
                        System.out.println("Salary Updated Successfully !");
                        break;    


                     case 4:
                        //Cancel
                        System.out.print("Update Cancelled !");
                        break;
                        
                      default:
                        System.out.println("Invalid Choice !"); 
              }
            } catch(IllegalArgumentException e){
                System.out.println("Error:"+e.getMessage());
            }

        }


              
       

       //Increase salary

       public static void increaseSalary(){

          System.out.println("============Salary Increase===========");
          
          System.out.println("Enter the Employee ID: ");
          int id=sc.nextInt();

          Employee employee=findEmployeeById(id);
          if(employee==null){
            System.out.println("Employee not Found!");
            return;
          }
        
          System.out.print("Enter the percentage: ");
          double percentage=sc.nextDouble();

          try{
               double oldSalary=employee.getSalary();
               employee.increaseSalary(percentage);

               double newSalary=employee.getSalary();
               System.out.println("\nSalary Updated Successfully!");

               System.out.println("Old Salary: Rs."+oldSalary);
               System.out.println("New Salary: Rs."+newSalary);

          }catch(IllegalArgumentException e){
            System.out.println("Error:"+e.getMessage());
          }
        }

        //===========================================================
        // Delete Employee

        public static void deleteEmployee(){
            System.out.println("\n==================Delete Employee===================");

            System.out.println("Enter Employee ID:");
            int id=sc.nextInt();

            Employee employee=findEmployeeById(id);
            if(employee==null){
                System.out.println("Employee not found!");
                return;
        }

        employees.remove(employee);
        System.out.println("Employee Deleted Successfully !");
    }


    //===========================================================================================
    //Department-Wise-Employees

    public static void displayByDepartment(){
        System.out.println("\n================Department Search=====================");

        System.out.println("Enter Department: ");
        String department=sc.next();

        boolean found=false;

        for(Employee employee:employees){
            if(employee.getDepartment().equalsIgnoreCase(department)){
                employee.displayEmployee();
                found=true;

        }
    }

    if(!found){
        System.out.println("No Employees Found in "+department+" department");
    }
}


//===============================================================================================
//Highest Salary

public static void findHighestSalaryEmployee(){
    System.out.println("\n===================Highest Salary Employee=====================");

    if(employees.isEmpty()){
        System.out.println("No employees Available !");
        return;
    }

    Employee highest=employees.get(0);
    for(Employee employee:employees){
        if(employee.getSalary()>highest.getSalary()){
            highest=employee;
        }
    }
    highest.displayEmployee();


}

//===================================================================================
// Employee Count

public static void displayEmployeeCount(){
      System.out.println("\n=================Employee Count===================");
      System.out.println("Total Employees: "+employees.size());


}
    
//======================================================================================================
// Main


            public static void main(String[] args){
                
        
           //-----------------------------------------------------------
           //MENU
           //----------------------------------------------------------
        while(true){
               System.out.println("-----------------------------------------------------------");
               System.out.println("================Employee Management System================");
               System.out.println("-----------------------------------------------------------");

               System.out.println("1. Add employee");
               System.out.println("2. Display All Employee");
               System.out.println("3. Search Employee");
               System.out.println("4. Update Employee");
               System.out.println("5. Increase salary");
               System.out.println("6. Delete Employee");
               System.out.println("7. Department wise Employee");
               System.out.println("8. Highest Salary Employee");
               System.out.println("9. Employee Count");
               System.out.println("10. Exit\n");

               System.out.print("Enter Your Choice :");
               int choice=sc.nextInt();
               

       

        switch(choice){
            case 1:
                //Add employee
                addEmployee();
                break;

             case 2:
                //Display all Employee
                displayAllEmployees();
                break;

             case 3:
                //Search employee
                searchEmployee();
                break;   

             case 4:
                //Update employee
                updateEmployee();
                break;
                
             case 5:
                //IncreaseSalary
                increaseSalary();
                break;
                
             case 6:
                //Delete Employee
                deleteEmployee();
                break;
                
             case 7:
                //Department-wise employee
                displayByDepartment();
                break;
                
             case 8:
                //Highest Salary Employee
                findHighestSalaryEmployee();
                break;

             case 9:
                //Employee Count
                displayEmployeeCount();
                break;
                
             case 10:
                //Exit
                System.out.println("Thank you! ");
                sc.close();
                return;
                

                default:
                    System.out.println("Invalid Choice !");

        }
       }

    }
}



