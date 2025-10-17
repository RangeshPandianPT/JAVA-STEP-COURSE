import java.util.HashSet;
import java.util.Objects;

public class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Student other = (Student) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }

    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();

        students.add(new Student(101, "Arjun"));
        students.add(new Student(102, "Priya"));
        students.add(new Student(101, "Arjun")); // duplicate based on id

        System.out.println("Students in the HashSet:");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
