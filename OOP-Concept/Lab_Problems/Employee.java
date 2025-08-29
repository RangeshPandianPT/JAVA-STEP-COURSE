class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;

    private static int totalEmployees = 0;

    public Employee(String empName, String department, double baseSalary, double bonus) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary + bonus;
        this.empType = "Full-Time";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public Employee(String empName, String department, double hourlyRate, int hoursWorked) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "Part-Time";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public Employee(String empName, String department, double contractAmount) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = contractAmount;
        this.empType = "Contract";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public double calculateSalary(double baseSalary, double bonus) {
        return baseSalary + bonus;
    }

    public double calculateSalary(double hourlyRate, int hoursWorked) {
        return hourlyRate * hoursWorked;
    }

    public double calculateSalary(double contractAmount) {
        return contractAmount;
    }

    public double calculateTax(double salary, String empType) {
        if (empType.equals("Full-Time")) {
            return salary * 0.2;
        } else if (empType.equals("Part-Time")) {
            return salary * 0.1;
        } else if (empType.equals("Contract")) {
            return salary * 0.05;
        }
        return 0;
    }

    public void generatePaySlip() {
        double tax = calculateTax(baseSalary, empType);
        double netSalary = baseSalary - tax;
        System.out.println("Pay Slip for Employee: " + empName);
        System.out.println("Employee ID   : " + empId);
        System.out.println("Department    : " + department);
        System.out.println("Employee Type : " + empType);
        System.out.println("Gross Salary  : " + baseSalary);
        System.out.println("Tax Deduction : " + tax);
        System.out.println("Net Salary    : " + netSalary);
    }

    public void displayEmployeeInfo() {
        System.out.println("ID: " + empId + " | Name: " + empName + " | Department: " + department + " | Type: " + empType + " | Salary: " + baseSalary);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    private static String generateEmpId() {
        return String.format("E%03d", totalEmployees + 1);
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", "HR", 50000, 10000); 
        Employee emp2 = new Employee("Bob", "IT", 300, 120);      
        Employee emp3 = new Employee("Charlie", "Finance", 80000); 

        emp1.generatePaySlip();
        emp2.generatePaySlip();
        emp3.generatePaySlip();

        emp1.displayEmployeeInfo();
        emp2.displayEmployeeInfo();
        emp3.displayEmployeeInfo();

        System.out.println("Total Employees in Company: " + Employee.getTotalEmployees());

        double demoFullTimeSalary = emp1.calculateSalary(40000, 5000);
        double demoPartTimeSalary = emp2.calculateSalary(200, 100);
        double demoContractSalary = emp3.calculateSalary(60000);

        System.out.println("Demo Full-Time Salary Calculation: " + demoFullTimeSalary);
        System.out.println("Demo Part-Time Salary Calculation: " + demoPartTimeSalary);
        System.out.println("Demo Contract Salary Calculation: " + demoContractSalary);
    }
}
