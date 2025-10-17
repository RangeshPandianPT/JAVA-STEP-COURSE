public class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee Details: ID=" + id + ", Name=" + name + ", Salary=₹" + salary;
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Karthik", 45000);
        Employee e2 = new Employee(102, "Priya", 52000);
        Employee e3 = new Employee(103, "Arun", 48000);

        System.out.println(e1);
        System.out.println("Class Name: " + e1.getClass().getName());

        System.out.println(e2);
        System.out.println("Class Name: " + e2.getClass().getName());

        System.out.println(e3);
        System.out.println("Class Name: " + e3.getClass().getName());
    }
}
