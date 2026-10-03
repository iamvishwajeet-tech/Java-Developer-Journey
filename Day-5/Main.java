import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

class Employee{
    private int id;
    private String name;
    private String department;
    private double salary;

    //constructor
   public Employee(int id, String name,String department,double salary){

         setid(id);
         setname(name);
         setdepartment(department);
         setsalary(salary);

   }

   //ID Validation

   public void setid(int id){

       if(id<=0){
           throw new IllegalArgumentException("Invalid ID !");
       }
       this.id=id;

   }

   public int getid(){
    return id;
   }

   //Name Validation
   public void setname(String name){

      if(name==null||name.trim().isEmpty()){
          throw new IllegalArgumentException("Name cannot be empty !");
      }

      if(!name.matches("[a-zA-Z ]+")){
          throw new IllegalArgumentException("Name can contain only letter and spaces !");
      }

      if(name.trim().length()<2||name.trim().length()>40){
           throw new IllegalArgumentException("Name can be range between 2 and 40 !");

      }

      this.name=name.trim();
   }


         public String getname(){
                 return name; 
             }

      //Department Validation
      
      public void setdepartment(String department){

         if(department==null||department.trim().isEmpty()){

            throw new IllegalArgumentException("Department cannot be empty !");
         }

        if(!department.equalsIgnoreCase("IT")&&
        !department.equalsIgnoreCase("Accounting")&&
        !department.equalsIgnoreCase("Finance")&&
        !department.equalsIgnoreCase("Sales")&&
        !department.equalsIgnoreCase("HR")&&
        !department.equalsIgnoreCase("Manager")&&
        !department.equalsIgnoreCase("Consultant")&&
        !department.equalsIgnoreCase("CEO")){
   
            throw new IllegalArgumentException("Invalid Department !");

        }

              this.department=department.trim();
    
      }

      public String getdepartment(){
          return department;
      }

      //Salary Validation

      public void setsalary(double salary){

          if(salary<=0){
               throw new IllegalArgumentException("Salary cannot be negative !");
          }

          if(salary>10000000){
               throw new IllegalArgumentException("Salary cannot be greater than 1 crore!");
          }

          this.salary=salary;
      }

      public double getsalary(){
          return salary;
      }

      //===========================================================
      //Increase Salary
      //============================================================
      public void increaseSalary(double percentage){

        if(percentage<=0||percentage>100){
            throw new IllegalArgumentException("Percentage must be between 1 and 100 !");
        }

          salary=salary+(salary*percentage/100);

        

      }

      //======================================================
      //Annual Salary
      //======================================================

      public double getAnnualSalary(){
           return salary*12;
      } 

      //==========================================================
      //Display
      //=========================================================

      public void displayEmployee(){

        System.out.println("----------------------------------------------");
        System.out.println("ID               :"+id);
        System.out.println("Name             :"+name);
        System.out.println("Department       :"+department);
        System.out.println("Salary           :"+salary);
        System.out.println("Annual Salary    :"+getAnnualSalary());
        System.out.println("----------------------------------------------");


      }

    }

    //====================================================================
    //Main Class
    //===================================================================

    public class Main{
        static Scanner sc=new Scanner(System.in);
        static ArrayList<Employee>employees=new ArrayList<>();


        //--------------------------------------------------------
        //ADD employee
        //----------------------------------------------------
        public static void addEmployee(){

            try{
                System.out.println("=============ADD Employee============");
                
                System.out.print("ID                  :");
                int id=sc.nextInt();
                sc.nextLine();

                if(findEmployeebyId(id)!=null){
                    System.out.print("Employee ID already exist !");

                    return;
                }
                
                System.out.print("Enter Name          :");
                String name=sc.nextLine();

                System.out.print("Enter Department    :");
                String department=sc.nextLine();

                System.out.print("Enter Salary        :");
                double salary=sc.nextDouble();
                sc.nextLine();

                Employee employee=new Employee(id,name,department,salary);

                employees.add(employee);
                System.out.println("Employee Added Successfully!");

                
            }catch(IllegalArgumentException e){
                    System.out.println("Error:"+e.getMessage());
                }
            }

        //=============================================================
        //Display All Employee
        //================================================================    
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

            public static Employee findEmployeebyId(int id){
                for(Employee employee:employees){
                    if(employee.getid()==id){
                        return employee;

                    }
                }
                return null;

            }

            //==================================================
            //Search Emplloyee
            //==================================================

            public static void searchEmployee(){
                System.out.println("\n==============Search Employee========");
                System.out.print("Enter Employee ID:");
                int id=sc.nextInt();
                
                Employee employee=findEmployeebyId(id);

                if(employee!=null){
                    System.out.println("\nEmployee found !");

                    employee.displayEmployee();

                }
                else{
                    System.out.println("Employee not Found !");
                }
            }

            //===============================================================
            //Update Employee
            //===============================================================
            public static void updateEmployee(){

                System.out.println("\n===========Update Employee===========");
                System.out.print("Enter Employee ID:");

                int id=sc.nextInt();
                sc.nextLine();

                Employee employee=findEmployeebyId(id);
                if(employee==null){
                    System.out.println("Employee not Found !");
                    return;
                }

                System.out.println("\n 1. Update Name");
                System.out.println("2. Update department");
                System.out.println("3. Update Salary");
                System.out.println("4. cancel");

                System.out.print("Enter Your Choice :");

                int choice =sc.nextInt();
                sc.nextLine();

                try{
                   
                    switch(choice){
                        case 1:
                            System.out.print("Enter new Name:");
                            String newName=sc.nextLine();
                            employee.setname(newName);

                            System.out.println("Name updated Successfully !");
                            break;

                            case 2:
                                System.out.print("Enter New Department:");
                                String newDepartment=sc.nextLine();

                                employee.setdepartment(newDepartment);
                                System.out.println("DepartmentUpdated Successfully !");
                                break;

                                case 3:
                                    System.out.print("Enter New Salary:");
                                    double newSalary=sc.nextDouble();

                                    employee.setsalary(newSalary);
                                    System.out.println("Salary Updated Successful !");
                                    break;

                                case 4:
                                    System.out.println("Update Canceled !");
                                    break;
                                    
                                default:
                                    System.out.println("Invalid Choice !");

                    }
                
                    
                }catch(IllegalArgumentException e){
                        System.out.println("Error :"+e.getMessage());
                    }
                }

//==============================================================
//Inrease Salary
 //===============================================================
                public static void increaseSalary(){

                    System.out.println("\n==============Increase Salary==============");
                    System.out.print("Enter Employee ID:");

                    int id=sc.nextInt();

                    Employee employees=findEmployeebyId(id);

                    if(employees==null){
                        System.out.println("Employee not Found !");
                        return;

                    }

                    System.out.print("Enter Increase Percentage :");
                    double percentage =sc.nextDouble();

                    try{
                        double oldSalary=employees.getsalary();
                        employees.increaseSalary(percentage);
                        double newSalary=employees.getsalary();

                        System.out.println("\nSalary Updated Successfully !");
                        System.out.println("Old Salary:"+oldSalary);

                        System.out.println("New salary:"+newSalary);

                    }catch(IllegalArgumentException e){
                        System.out.println("Error:"+e.getMessage());
                    }
                }

                //===========================================================
                //Delete Employee
                //===========================================================
                public static void deleteEmployee(){

            
                        System.out.println("==========Delete Employee===========");
                        System.out.print("Enter ID: ");
                        int id=sc.nextInt();
                        
                        Employee employee=findEmployeebyId(id);
                        if(employees==null){

                            System.out.println("Employee Not Found !");
                            return;

                        }
                    
                            employees.remove(employee);
                            System.out.print("Employee Deleted Successfully !");
                        

                    }

  //========================================================
  //Display By Department
  //==========================================================
  public static void displayByDepartment(){
     
      System.out.println("===========Display By Department==========");
      System.out.print("Enter Department :");
      
      String department=sc.nextLine();
      sc.nextLine();
      boolean found=false;
      for(Employee employee:employees){

        if(employee.getdepartment().equalsIgnoreCase(department)){
            employee.displayEmployee();
        }

    }
        if(!found){
            System.out.println("No Employees Found in "+department+" department");
        }
    }

    //===================================================================
    //Find Highest Salary Employee
    //===================================================================

    public static void findHighestSalaryEmployee(){
        System.out.println("-------------Highest Salary Employee------------");

        if(employees.isEmpty()){
            System.out.println("Employee not found !");
        }
        Employee highest=employees.get(0);

        for(Employee employee:employees){
             if(employee.getsalary()>highest.getsalary()){
                     highest=employee;
             }
        }

          highest.displayEmployee();
    }

    //===================================================================
    //Employee Count
    //==================================================================

    public static void employeeCount(){

        System.out.println("-------------------Employee Count---------------------");
        
        System.out.println("Total Employee: "+employees.size());
    }

    //=====================================================================
    //Salary Statistics
    //====================================================================

    public static void salaryStatistics(){
           System.out.println("================Salary Statistics===============");

           if(employees.isEmpty()){
            System.out.println("No Employees Found!");
            return;
           }

           double totalSalary=0;
           double highestSalary=employees.get(0).getsalary();
           double lowestSalary=employees.get(0).getsalary();

           for(Employee employee:employees){
              double salary=employee.getsalary();
              totalSalary+=salary;
 
              if(salary>highestSalary){
                    highestSalary=salary;

              }

              if(salary<lowestSalary){
                lowestSalary=salary;

              }
           }

           double averageSalary=totalSalary/employees.size();

           System.out.println("Total Salary     :"+totalSalary);
           System.out.println("AverageSalary    :"+averageSalary);
           System.out.println("Highest Salary   :"+highestSalary);
           System.out.println("Lowest Salary    :"+lowestSalary);

        }

        //=============================================================
        //Sort By Salary
        //=============================================================

        public static void sortBySalary(){
            System.out.println("\n==========Sort By Salary=========");

            if(employees.isEmpty()){
                System.out.println("No Employees Available ");
                return;
            }
            System.out.println("1. Low to High");
            System.out.println("2. High to Low");
            System.out.println("3. Enetr Choice:");

            int choice=sc.nextInt();

            if(choice==1){
                employees.sort(Comparator.comparingDouble(Employee::getsalary));
                
                System.out.println("\n Sorted:Low to High");

            }else if(choice==2){
                  employees.sort(Comparator.comparingDouble(Employee::getsalary).reversed());
                  System.out.println("\nSorted:High to low");
                  

            }else {
                System.out.println("Invalid choice !");
                return;
            }
            displayAllEmployees();
        }

        //========================================================
        //Salary range Search
        //=======================================================

        public static void findEmployeeBySalaryRange(){
             System.out.println("\n=============Salary Range Search===========");
             System.out.print("Enter Minimum Salary:");
             double minSalary=sc.nextDouble();

             System.out.print("Enter Maximium Salary:");
             double maxSalary=sc.nextDouble();

             if(minSalary<=0||maxSalary<=0){
                  System.out.println("Salary must be greater than 0");
                  return;

             }

             if(minSalary>maxSalary){
                System.out.println("Invalid Salary range !");
                return;

             }

             boolean found=false;
             for(Employee employee:employees){
                double salary=employee.getsalary();

                if(salary>=minSalary&&salary<=maxSalary){
                    employee.displayEmployee();
                    found=true;
                }
             }

             if(!found){
                System.out.println("No employees Found in this salary Range !");
             }

            }

    //==================================================================
    //Department statistics
    //===================================================================

    public static void departmentStatistics(){
        System.out.println("=================Department Statistics=============");

        if(employees.isEmpty()){
            System.out.println("No Employees available !");

            return;

        }

        int it=0;
        int hr=0;
        int finance=0;
        int marketing=0;
        int sales=0;
        
        for(Employee employee:employees){

            String department=employee.getdepartment();

            if(department.equalsIgnoreCase("IT")){
                it++;

            }else if(department.equalsIgnoreCase("HR")){
                    hr++;

            }else if(department.equalsIgnoreCase("Finance")){
                finance++;

            }else if(department.equalsIgnoreCase("Marketing")){
                marketing++;
                
            }else if(department.equalsIgnoreCase("Salaes")){
                sales++;

            }

        }

        System.out.println("IT:          :"+it+" employees");
        System.out.println("HR           :"+hr+" employees");
        System.out.println("Finance      :"+finance+" employees");
        System.out.println("Marketing    :"+marketing+" employees");
        System.out.println("Sales        :"+sales+" employees");
    }

    //===================================================================
    //TOP 3 Highest paid Employees
    //===================================================================

    public static void topthreehighestPaidEmployee(){
        System.out.println("\n===============TOP 3 Highest Paid Employee============");

        if(employees.isEmpty()){

            System.out.println("No Employees Available !");
            return;
        }
         
        ArrayList<Employee>sortedEmployees=new ArrayList<>(employees);
        sortedEmployees.sort(Comparator.comparingDouble(Employee::getsalary).reversed());

        int limit=Math.min(3,sortedEmployees.size());

        for(int i=0;i<limit;i++){
            Employee employee=sortedEmployees.get(i);
            System.out.println((i+1)+". "+employee.getname()+"- "+employee.getsalary());
        }

    }

    //===============================================================
    //Main Method
    //=================================================================

  public static void main(String[] args){

    while(true){


        System.out.println("===================================================");
        System.out.println("  Employee Management System");
        System.out.println("=================================================");

        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employee");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Increase Salary");
        System.out.println("6. Delete Employee");
        System.out.println("7. Department-Wise Employee");
        System.out.println("8. Highest Salary Employee");
        System.out.println("9. Employee Count");
        System.out.println("10. Salary Statistics");
        System.out.println("11. Sort Employees By Salary");
        System.out.println("12. Salary Range Search");
        System.out.println("13. Department Statistics");
        System.out.println("14. Top 3 Highest Paid Employees");
        System.out.println("15. Exit");

        System.out.println("\nEnter your choice: ");

        int choice=sc.nextInt();

        switch(choice){

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
                employeeCount();
                break;
                
             case 10:
                salaryStatistics();
                break;
                
             case 11:
                sortBySalary();
                break;
                
              case 12:
                findEmployeeBySalaryRange();
                break;
                
              case 13:
                departmentStatistics();
                break;

              case 14:
                topthreehighestPaidEmployee();
                break; 
                
                
              case 15:
                System.out.println("\nThank you for using"+" Employee Management System");
                sc.close();
                return;
             
            default:   

                System.out.println("Invalid Choice !"+ "Please select 1-15");
                
                
                
        }      
                
    }   

        }

    }
  
    
           


        
    
      

  
                
            


        









    




