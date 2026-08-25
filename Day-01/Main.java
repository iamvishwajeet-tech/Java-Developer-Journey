import java.util.ArrayList;
import java.util.Scanner;

class Employee{
    private int id;
    private String name;
    private String department;
    private double salary;

 public Employee(int id,String name,String department,double salary){
    
    if(id<=0){
        throw new IllegalArgumentException("Invalid Employee Id !");
    }
    
   
     if(name==null|| name.trim().isEmpty()){
        throw new IllegalArgumentException("Name cannot be empty !");
     }
     
     if(name.trim().length()<2||name.trim().length()>30){
        throw new IllegalArgumentException("Name must be between 2 and 30 character !");
     }
     
     if(!name.matches("[a-zA-Z ]+")){
         throw new IllegalArgumentException("Name can contain only letters and spaces !");
     }
 
     
         if(department==null||department.trim().isEmpty()){
            throw new IllegalArgumentException("Department cannot be empty !");
         }


         if(!department.matches("[a-zA-Z ]+")){
            throw new IllegalArgumentException("Department can contain only letters and spaces !");
         }

         if(!department.equalsIgnoreCase("IT")&&
         !department.equalsIgnoreCase("Finance")&&
        !department.equalsIgnoreCase("Accounting")&&
         !department.equalsIgnoreCase("HR")&&
           !department.equalsIgnoreCase("Sales")&&
              !department.equalsIgnoreCase("Manager")) {
     
                throw new IllegalArgumentException("Invalid Department !");

     }

    
    
      if(salary<=0){
        throw new IllegalArgumentException("Salary must me greater than 0");
      }

      if(salary>10000000){
        throw new IllegalArgumentException("Salary cannot exceed crore !");
      }

        this.id=id;
        this.name=name;
        this.department=department;
        this.salary=salary;

  }

  public void increasesalary(double percentage){
    if(percentage<=0){
        throw new IllegalArgumentException("Percentage must be greater than 0");
    }
    salary=salary+(salary*percentage/100);

  }

  public void decreasesalary(double percentage){
    if(percentage<=0){
        throw new IllegalArgumentException("Invalid percentage!");
    }
    salary=salary-(salary*percentage/100);
  }


public void addbonous(double bonus){
    if(bonus<=0){
        throw new IllegalArgumentException("Bonus is not less than zero!");

    }
    salary=salary+bonus;
}

public double getAnnualsalary(){
    return salary*12;
}

  public void displayemp(){
       System.out.println("ID:"+id);
       System.out.println("name:"+name);
       System.out.println("Department:"+department);
       System.out.println("Salary:"+salary);
       System.out.println("---------------------------");

  }
}

public class Main{
    public static void main(String[] args){

      System.out.println("===========Day 1 Java Practice========== ");
        Scanner sc=new Scanner(System.in);
      
      
      ArrayList<Employee>employees=new ArrayList<>();

      System.out.println("How many employees?");

      int n=sc.nextInt();
      sc.nextLine();
      for(int i=1;i<=n;i++){
         System.out.println("\nEnter the "+i+" information ");
        
         System.out.print("Enter ID:");
         int id=sc.nextInt();
         sc.nextLine();

         System.out.print("\nEnter the Employee name:");
         String name=sc.nextLine();

         System.out.print("\nEnter the Department:");
         String department=sc.nextLine();

         System.out.print("\nEnter the Salary:");
         double salary=sc.nextDouble();
         sc.nextLine();

         Employee emp=new Employee(id,name,department,salary);

         employees.add(emp);
      }
    
      
    System.out.println("\n=========All Employees========");


    for(Employee emp:employees){
        emp.displayemp();
    }

    sc.close();

    }
}
