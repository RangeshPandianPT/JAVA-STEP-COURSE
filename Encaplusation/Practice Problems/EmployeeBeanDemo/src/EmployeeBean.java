import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.lang.reflect.Method;

public class EmployeeBean implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    // Default constructor
    public EmployeeBean() {}

    // Parameterized constructor
    public EmployeeBean(String employeeId, String firstName, String lastName, double salary,
                        String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary);
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public Date getHireDate() { return hireDate; }
    public boolean isActive() { return isActive; }

    // Setters
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary must be positive");
        this.salary = salary;
    }
    public void setDepartment(String department) { this.department = department; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public void setActive(boolean active) { isActive = active; }

    // Computed properties
    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        LocalDate hireLocalDate = hireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(hireLocalDate, LocalDate.now()).getYears();
    }

    public String getFormattedSalary() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(salary);
    }

    // Derived property setter
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) return;
        String[] parts = fullName.split(" ", 2);
        this.firstName = parts[0];
        this.lastName = (parts.length > 1) ? parts[1] : "";
    }

    // Override toString
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + ''' +
                ", fullName='" + getFullName() + ''' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + ''' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", isActive=" + isActive +
                '}';
    }

    // Equals and hashCode based on employeeId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    // Demo main
    public static void main(String[] args) {
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFirstName("Alice");
        emp1.setLastName("Johnson");
        emp1.setSalary(70000);
        emp1.setDepartment("IT");
        emp1.setHireDate(new Date(120, 0, 1)); // Jan 1, 2020
        emp1.setActive(true);

        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Smith", 85000, "HR", new Date(118, 5, 15), true);

        System.out.println(emp1);
        System.out.println(emp2);

        // Computed properties
        System.out.println("Full Name: " + emp1.getFullName());
        System.out.println("Years of Service: " + emp1.getYearsOfService());
        System.out.println("Formatted Salary: " + emp1.getFormattedSalary());

        // Validation test
        try {
            emp1.setSalary(-5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation works: " + e.getMessage());
        }

        // Collection example
        EmployeeBean[] employees = {emp1, emp2, new EmployeeBean("E003", "Carol", "White", 60000, "Finance", new Date(121, 3, 10), false)};

        Arrays.sort(employees, (a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        System.out.println("Sorted by salary:");
        for (EmployeeBean e : employees) {
            System.out.println(e);
        }

        System.out.println("Active employees:");
        for (EmployeeBean e : employees) {
            if (e.isActive()) System.out.println(e);
        }

        // Reflection utilities
        System.out.println("\nUsing JavaBeanProcessor:");
        JavaBeanProcessor.printAllProperties(emp1);

        EmployeeBean copy = new EmployeeBean();
        JavaBeanProcessor.copyProperties(emp1, copy);
        System.out.println("Copied Employee: " + copy);
    }
}

// Utility class
class JavaBeanProcessor {
    public static void printAllProperties(EmployeeBean emp) {
        try {
            Method[] methods = emp.getClass().getMethods();
            for (Method m : methods) {
                if ((m.getName().startsWith("get") || m.getName().startsWith("is"))
                        && m.getParameterCount() == 0) {
                    Object value = m.invoke(emp);
                    String propertyName = m.getName().startsWith("get")
                            ? m.getName().substring(3)
                            : m.getName().substring(2);
                    System.out.println(propertyName + ": " + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            Method[] methods = source.getClass().getMethods();
            for (Method m : methods) {
                if ((m.getName().startsWith("get") || m.getName().startsWith("is"))
                        && m.getParameterCount() == 0) {
                    Object value = m.invoke(source);
                    String propertyName = m.getName().startsWith("get")
                            ? m.getName().substring(3)
                            : m.getName().substring(2);
                    try {
                        Method setter = source.getClass().getMethod("set" + propertyName, m.getReturnType());
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
