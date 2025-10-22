class Teacher {
    String teacherName;
    String subject;

    Teacher(String teacherName, String subject) {
        this.teacherName = teacherName;
        this.subject = subject;
    }
}

class Student {
    String studentName;
    int rollNo;
    Teacher guide;

    Student(String studentName, int rollNo, Teacher guide) {
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.guide = guide;
    }
}

public class ObjectDiagramDemo {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Karthik", "Mathematics");
        Teacher teacher2 = new Teacher("Meena", "Physics");

        Student student1 = new Student("Ravi", 101, teacher1);
        Student student2 = new Student("Priya", 102, teacher1);
        Student student3 = new Student("Arun", 103, teacher2);
        Student student4 = new Student("Divya", 104, teacher2);
    }
}
