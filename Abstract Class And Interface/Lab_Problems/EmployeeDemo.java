abstract class Employee {
    protected String name;
    protected double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    abstract double calculateBonus();
}

interface Payable {
    void generatePaySlip();
}

class Manager extends Employee implements Payable {
    private String department;

    Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    double calculateBonus() {
        return salary * 0.20;  // 20% bonus
    }

    @Override
    public void generatePaySlip() {
        System.out.println("Pay Slip for Manager");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Total Pay: " + (salary + calculateBonus()));
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Manager manager = new Manager("Anitha", 75000, "Finance");
        manager.generatePaySlip();
    }
}
