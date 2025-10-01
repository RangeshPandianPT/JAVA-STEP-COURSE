abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public abstract double calculateBonus();

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

class Manager extends Employee {
    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.20;
    }
}

class Developer extends Employee {
    public Developer(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10;
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        Employee e1 = new Manager("Alice", 50000);
        e1.displayDetails();
        System.out.println("Bonus: " + e1.calculateBonus());
        System.out.println();

        Employee e2 = new Developer("Bob", 40000);
        e2.displayDetails();
        System.out.println("Bonus: " + e2.calculateBonus());
    }
}
