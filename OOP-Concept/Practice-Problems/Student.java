public class Student {
    private String studentId;
    private String name;
    private double grade;
    private String course;

    public Student() {
        this.studentId = "Unknown";
        this.name = "Unknown";
        this.grade = 0.0;
        this.course = "Not Assigned";
    }

    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Numeric Grade: " + grade);
        System.out.println("Letter Grade: " + calculateLetterGrade());
    }

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setStudentId("S001");
        student1.setName("Alice");
        student1.setGrade(85.5);
        student1.setCourse("Mathematics");

        Student student2 = new Student("S002", "Bob", 72.3, "Computer Science");

        System.out.println("Using Getters for Student 1:");
        System.out.println("ID: " + student1.getStudentId());
        System.out.println("Name: " + student1.getName());
        System.out.println("Grade: " + student1.getGrade());
        System.out.println("Course: " + student1.getCourse());
        System.out.println();

        System.out.println("Student Details:");
        student1.displayStudent();
        student2.displayStudent();
    }
}
