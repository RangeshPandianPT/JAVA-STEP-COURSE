import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

abstract class Employee {
    String empId;
    String empName;
    String department;
    String designation;
    double baseSalary;
    String joinDate;
    boolean[] attendanceRecord;
    int leaveBalance;
    double performanceRating;
    static int totalEmployees = 0;
    static String companyName = "Tech Solutions Pvt Ltd";
    static double totalSalaryExpense = 0.0;
    static int workingDaysPerMonth = 30;

    Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        this.empId = empId; this.empName = empName; this.department = department;
        this.designation = designation; this.baseSalary = baseSalary; this.joinDate = joinDate;
        this.attendanceRecord = new boolean[workingDaysPerMonth];
        this.leaveBalance = 12;
        this.performanceRating = 0.5;
        totalEmployees++;
    }

    public void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= workingDaysPerMonth) attendanceRecord[day - 1] = present;
    }

    public boolean requestLeave(int days) {
        if (days <= leaveBalance) { leaveBalance -= days; return true; }
        return false;
    }

    public int attendedDays(int month) {
        int c = 0;
        for (boolean b : attendanceRecord) if (b) c++;
        return c;
    }

    public double calculateBonus() {
        double bonusPercent = 0.05 + (performanceRating * 0.15);
        return baseSalary * bonusPercent;
    }

    public abstract double calculateSalary();

    public void generatePaySlip(int month, int year) {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        double total = salary + bonus;
        totalSalaryExpense += total;
        System.out.println("---- PaySlip ----");
        System.out.println("Company: " + companyName);
        System.out.println("Employee: " + empName + " (" + empId + ")");
        System.out.println("Designation: " + designation + " | Dept: " + department);
        System.out.println("Month/Year: " + month + "/" + year);
        System.out.printf("Base Salary: %.2f\n", baseSalary);
        System.out.printf("Earnings (pro-rated): %.2f\n", salary);
        System.out.printf("Performance Bonus: %.2f\n", bonus);
        System.out.printf("Total Pay: %.2f\n", total);
        System.out.println("Leaves Remaining: " + leaveBalance);
    }
}

class FullTime extends Employee {
    double hraPercent;
    double medicalAllowance;

    FullTime(String empId, String empName, String department, String designation, double baseSalary, String joinDate) {
        super(empId, empName, department, designation, baseSalary, joinDate);
        this.hraPercent = 0.20;
        this.medicalAllowance = 2000;
    }

    public double calculateSalary() {
        int present = attendedDays(0);
        double attendanceFactor = (double) present / workingDaysPerMonth;
        double basic = baseSalary * attendanceFactor;
        double hra = basic * hraPercent;
        return Math.round((basic + hra + medicalAllowance) * 100.0) / 100.0;
    }
}

class PartTime extends Employee {
    double hourlyRate;
    int hoursWorkedThisMonth;

    PartTime(String empId, String empName, String department, String designation, double hourlyRate, String joinDate) {
        super(empId, empName, department, designation, hourlyRate * 160, joinDate);
        this.hourlyRate = hourlyRate;
        this.hoursWorkedThisMonth = 0;
    }

    public void logHours(int hours) { this.hoursWorkedThisMonth += hours; }

    public double calculateSalary() {
        double salary = hourlyRate * hoursWorkedThisMonth;
        return Math.round(salary * 100.0) / 100.0;
    }
}

class Contract extends Employee {
    double contractAmount;

    Contract(String empId, String empName, String department, String designation, double contractAmount, String joinDate) {
        super(empId, empName, department, designation, contractAmount, joinDate);
        this.contractAmount = contractAmount;
    }

    public double calculateSalary() {
        int present = attendedDays(0);
        double attendanceFactor = (double) present / workingDaysPerMonth;
        return Math.round(contractAmount * attendanceFactor * 100.0) / 100.0;
    }
}

class Department {
    String deptId;
    String deptName;
    Employee manager;
    Employee[] employees;
    double budget;

    Department(String deptId, String deptName, Employee manager, int capacity, double budget) {
        this.deptId = deptId; this.deptName = deptName; this.manager = manager; this.employees = new Employee[capacity]; this.budget = budget;
        if (manager != null) addEmployee(manager);
    }

    public boolean addEmployee(Employee e) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) { employees[i] = e; return true; }
        }
        return false;
    }

    public double departmentExpense() {
        double sum = 0;
        for (Employee e : employees) if (e != null) sum += e.calculateSalary() + e.calculateBonus();
        return sum;
    }
}

class HRSystem {
    List<Employee> allEmployees = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    public void hire(Employee e) { allEmployees.add(e); }

    public void addDepartment(Department d) { departments.add(d); }

    public void calculateCompanyPayroll(int month, int year) {
        double total = 0;
        for (Employee e : allEmployees) {
            e.generatePaySlip(month, year);
            total += e.calculateSalary() + e.calculateBonus();
        }
        Employee.totalSalaryExpense = total;
        System.out.printf("Total company payroll for %d/%d = %.2f\n\n", month, year, total);
    }

    public void getDepartmentWiseExpenses() {
        for (Department d : departments) {
            System.out.printf("Dept: %s | Expense: %.2f | Budget: %.2f\n", d.deptName, d.departmentExpense(), d.budget);
        }
    }

    public void getAttendanceReport() {
        for (Employee e : allEmployees) {
            int present = e.attendedDays(0);
            double pct = (present * 100.0) / Employee.workingDaysPerMonth;
            System.out.printf("%s (%s): Present %d/%d (%.1f%%)\n", e.empName, e.empId, present, Employee.workingDaysPerMonth, pct);
        }
    }
}

public class PayrollApp {
    public static void main(String[] args) {
        HRSystem hr = new HRSystem();
        FullTime m1 = new FullTime("E001", "Aditi Sharma", "Engineering", "Senior Engineer", 80000, "2021-03-15");
        FullTime e1 = new FullTime("E002", "Rohan Gupta", "Engineering", "Engineer", 60000, "2022-07-01");
        PartTime p1 = new PartTime("E003", "Sonal Roy", "Support", "Support Agent", 200, "2024-01-10");
        Contract c1 = new Contract("E004", "Karan Singh", "QA", "QA Contract", 45000, "2025-02-01");
        Department eng = new Department("D01", "Engineering", m1, 10, 1000000);
        eng.addEmployee(e1);
        Department sup = new Department("D02", "Support", null, 5, 200000);
        sup.addEmployee(p1);
        Department qa = new Department("D03", "QA", null, 5, 150000);
        qa.addEmployee(c1);
        hr.hire(m1); hr.hire(e1); hr.hire(p1); hr.hire(c1);
        hr.addDepartment(eng); hr.addDepartment(sup); hr.addDepartment(qa);
        for (int d = 1; d <= Employee.workingDaysPerMonth; d++) {
            m1.markAttendance(d, d % 6 != 0);
            e1.markAttendance(d, d % 7 != 0);
            p1.markAttendance(d, d % 2 == 0);
            c1.markAttendance(d, d % 5 != 0);
        }
        p1.logHours(80);
        m1.performanceRating = 0.9;
        e1.performanceRating = 0.7;
        p1.performanceRating = 0.6;
        c1.performanceRating = 0.8;
        m1.requestLeave(2);
        e1.requestLeave(1);
        hr.getAttendanceReport();
        hr.getDepartmentWiseExpenses();
        hr.calculateCompanyPayroll(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }
}
