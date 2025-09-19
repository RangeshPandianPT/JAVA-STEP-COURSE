import java.util.Date;

class Employee {
    protected String employeeId;
    protected String name;
    protected double baseSalary;
    protected String department;
    protected Date joiningDate;

    public Employee(String employeeId, String name, double baseSalary, String department, Date joiningDate) {
        if (employeeId == null || name == null || baseSalary <= 0 || department == null || joiningDate == null) {
            throw new IllegalArgumentException("Invalid employee parameters");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
        this.joiningDate = joiningDate;
        System.out.println("Employee " + name + " created in " + department);
    }

    public double calculateSalary() {
        return baseSalary;
    }

    public String getJobDescription() {
        return "General Employee";
    }

    public void performWork() {
        System.out.println("Employee is working");
    }

    public void attendMeeting() {
        System.out.println("Employee attending meeting");
    }

    public final String getEmployeeId() {
        return employeeId;
    }

    public final void printEmployeeDetails() {
        System.out.println("ID: " + employeeId + ", Name: " + name + ", Department: " + department +
                ", Base Salary: " + baseSalary + ", Joining: " + joiningDate);
    }

    public void takeBreak() {
        System.out.println(name + " is taking a short break");
    }

    public void clockIn() {
        System.out.println(name + " clocked in");
    }

    public void clockOut() {
        System.out.println(name + " clocked out");
    }
}

class Developer extends Employee {
    private String[] programmingLanguages;
    private String experienceLevel;
    private int projectsCompleted;

    public Developer(String employeeId, String name, double baseSalary, String department, Date joiningDate,
                     String[] programmingLanguages, String experienceLevel, int projectsCompleted) {
        super(employeeId, name, baseSalary, department, joiningDate);
        this.programmingLanguages = programmingLanguages;
        this.experienceLevel = experienceLevel;
        this.projectsCompleted = projectsCompleted;
        System.out.println("Developer profile created");
    }

    @Override
    public double calculateSalary() {
        double bonus = projectsCompleted * 500;
        if (experienceLevel.equalsIgnoreCase("Mid")) bonus += 2000;
        if (experienceLevel.equalsIgnoreCase("Senior")) bonus += 5000;
        return baseSalary + bonus;
    }

    @Override
    public String getJobDescription() {
        return "Software Developer";
    }

    @Override
    public void performWork() {
        System.out.println("Developer is coding and debugging");
    }

    @Override
    public void attendMeeting() {
        System.out.println("Developer in technical meeting");
    }

    public void writeCode() {
        System.out.println("Writing code in " + String.join(", ", programmingLanguages));
    }

    public void reviewCode() {
        System.out.println("Reviewing team's code");
    }

    public void deployApplication() {
        System.out.println("Deploying application to production");
    }
}

class Manager extends Employee {
    private int teamSize;
    private String managementLevel;
    private double budgetResponsibility;

    public Manager(String employeeId, String name, double baseSalary, String department, Date joiningDate,
                   int teamSize, String managementLevel, double budgetResponsibility) {
        super(employeeId, name, baseSalary, department, joiningDate);
        this.teamSize = teamSize;
        this.managementLevel = managementLevel;
        this.budgetResponsibility = budgetResponsibility;
        System.out.println("Manager profile created");
    }

    @Override
    public double calculateSalary() {
        double bonus = teamSize * 300;
        if (managementLevel.equalsIgnoreCase("Manager")) bonus += 4000;
        if (managementLevel.equalsIgnoreCase("Director")) bonus += 8000;
        return baseSalary + bonus;
    }

    @Override
    public String getJobDescription() {
        return "Team Manager";
    }

    @Override
    public void performWork() {
        System.out.println("Manager is coordinating team activities");
    }

    @Override
    public void attendMeeting() {
        System.out.println("Manager leading strategic meeting");
    }

    public void conductPerformanceReview() {
        System.out.println("Conducting team performance review");
    }

    public void assignTasks() {
        System.out.println("Assigning tasks to team members");
    }

    public void manageBudget() {
        System.out.println("Managing department budget of $" + budgetResponsibility);
    }
}

class Intern extends Employee {
    private String university;
    private int internshipDuration;
    private String mentor;
    private boolean isFullTime;

    public Intern(String employeeId, String name, double stipend, String department, Date joiningDate,
                  String university, int internshipDuration, String mentor, boolean isFullTime) {
        super(employeeId, name, stipend, department, joiningDate);
        this.university = university;
        this.internshipDuration = internshipDuration;
        this.mentor = mentor;
        this.isFullTime = isFullTime;
        System.out.println("Intern onboarded");
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }

    @Override
    public String getJobDescription() {
        return "Intern";
    }

    @Override
    public void performWork() {
        System.out.println("Intern is learning and assisting");
    }

    public void attendTraining() {
        System.out.println("Intern attending training session");
    }

    public void submitReport() {
        System.out.println("Submitting weekly progress report");
    }

    public void seekMentorship() {
        System.out.println("Getting guidance from mentor " + mentor);
    }
}

class EmployeeManager {
    public static double calculateTotalPayroll(Employee[] employees) {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateSalary();
        }
        return total;
    }

    public static void generateReport(Employee[] employees) {
        for (Employee e : employees) {
            System.out.println(e.getJobDescription() + " -> Salary: " + e.calculateSalary());
        }
    }
}

public class HierarchicalInheritanceDemo {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];
        employees[0] = new Developer("D001", "Alice", 50000, "IT", new Date(),
                new String[]{"Java", "Python"}, "Senior", 10);
        employees[1] = new Manager("M001", "Bob", 70000, "HR", new Date(),
                8, "Manager", 100000);
        employees[2] = new Intern("I001", "Charlie", 10000, "IT", new Date(),
                "XYZ University", 12, "D001", false);

        for (Employee e : employees) {
            e.clockIn();
            e.performWork();
            e.attendMeeting();
            e.takeBreak();
            e.clockOut();
            System.out.println("Salary: " + e.calculateSalary());
            System.out.println();
        }

        System.out.println("Total Payroll: " + EmployeeManager.calculateTotalPayroll(employees));
        EmployeeManager.generateReport(employees);

        for (Employee e : employees) {
            if (e instanceof Developer) {
                ((Developer) e).writeCode();
            } else if (e instanceof Manager) {
                ((Manager) e).assignTasks();
            } else if (e instanceof Intern) {
                ((Intern) e).attendTraining();
            }
        }
    }
}
