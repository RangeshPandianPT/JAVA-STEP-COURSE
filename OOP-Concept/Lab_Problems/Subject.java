class Subject {
    private String subjectCode;
    private String subjectName;
    private int maxMarks;
    private int passMarks;

    public Subject(String subjectCode, String subjectName, int maxMarks, int passMarks) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.maxMarks = maxMarks;
        this.passMarks = passMarks;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getPassMarks() {
        return passMarks;
    }

    public int getMaxMarks() {
        return maxMarks;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private int grade;
    private double[] marks;
    private double totalMarks;
    private double percentage;

    private static int totalStudents = 0;

    public Student(String studentName, int grade) {
        this.studentName = studentName;
        this.grade = grade;
        this.marks = new double[5];
        this.studentId = generateStudentId();
        totalStudents++;
    }

    public void setMarks(int subjectIndex, double mark) {
        marks[subjectIndex] = mark;
    }

    public void calculateTotal() {
        totalMarks = 0;
        for (double m : marks) {
            totalMarks += m;
        }
    }

    public void calculatePercentage() {
        percentage = totalMarks / marks.length;
    }

    public boolean isPass(Subject[] subjects) {
        for (int i = 0; i < subjects.length; i++) {
            if (marks[i] < subjects[i].getPassMarks()) {
                return false;
            }
        }
        return true;
    }

    public void displayResult(Subject[] subjects) {
        calculateTotal();
        calculatePercentage();
        System.out.println("Student ID   : " + studentId);
        System.out.println("Name         : " + studentName);
        System.out.println("Grade        : " + grade);
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i].getSubjectName() + " : " + marks[i]);
        }
        System.out.println("Total Marks  : " + totalMarks);
        System.out.println("Percentage   : " + percentage);
        System.out.println("Result       : " + (isPass(subjects) ? "Pass" : "Fail"));
    }

    public double getPercentage() {
        return percentage;
    }

    public String getStudentName() {
        return studentName;
    }

    public static Student getTopStudent(Student[] students) {
        Student top = null;
        double maxPercent = -1;
        for (Student s : students) {
            if (s != null) {
                s.calculateTotal();
                s.calculatePercentage();
                if (s.getPercentage() > maxPercent) {
                    maxPercent = s.getPercentage();
                    top = s;
                }
            }
        }
        return top;
    }

    public static double getClassAverage(Student[] students) {
        double total = 0;
        int count = 0;
        for (Student s : students) {
            if (s != null) {
                s.calculateTotal();
                s.calculatePercentage();
                total += s.getPercentage();
                count++;
            }
        }
        return (count == 0) ? 0 : total / count;
    }

    public static double getPassPercentage(Student[] students, Subject[] subjects) {
        int passed = 0, total = 0;
        for (Student s : students) {
            if (s != null) {
                total++;
                if (s.isPass(subjects)) {
                    passed++;
                }
            }
        }
        return (total == 0) ? 0 : (passed * 100.0) / total;
    }

    private static String generateStudentId() {
        return String.format("S%03d", totalStudents + 1);
    }
}

class Teacher {
    private String teacherId;
    private String teacherName;
    private String subject;
    private int studentsHandled;

    private static int totalTeachers = 0;

    public Teacher(String teacherName, String subject) {
        this.teacherName = teacherName;
        this.subject = subject;
        this.teacherId = generateTeacherId();
        this.studentsHandled = 0;
        totalTeachers++;
    }

    public void assignGrades(Student student, Subject subject, double marks, int subjectIndex) {
        student.setMarks(subjectIndex, marks);
        studentsHandled++;
        System.out.println("Teacher " + teacherName + " assigned " + marks + " in " + subject.getSubjectName() + " to " + student.getStudentName());
    }

    public void displayTeacherInfo() {
        System.out.println("Teacher ID   : " + teacherId);
        System.out.println("Name         : " + teacherName);
        System.out.println("Subject      : " + subject);
        System.out.println("Students Handled: " + studentsHandled);
    }

    public static int getTotalTeachers() {
        return totalTeachers;
    }

    private static String generateTeacherId() {
        return String.format("T%03d", totalTeachers + 1);
    }
}

public class SchoolSystem {
    public static void main(String[] args) {
        Subject[] subjects = new Subject[5];
        subjects[0] = new Subject("SUB001", "Math", 100, 35);
        subjects[1] = new Subject("SUB002", "Science", 100, 35);
        subjects[2] = new Subject("SUB003", "English", 100, 35);
        subjects[3] = new Subject("SUB004", "History", 100, 35);
        subjects[4] = new Subject("SUB005", "Computer", 100, 35);

        Student[] students = new Student[3];
        students[0] = new Student("Alice", 10);
        students[1] = new Student("Bob", 10);
        students[2] = new Student("Charlie", 10);

        Teacher[] teachers = new Teacher[5];
        teachers[0] = new Teacher("Mr. Smith", "Math");
        teachers[1] = new Teacher("Mrs. Brown", "Science");
        teachers[2] = new Teacher("Ms. Johnson", "English");
        teachers[3] = new Teacher("Mr. Lee", "History");
        teachers[4] = new Teacher("Mrs. White", "Computer");

        teachers[0].assignGrades(students[0], subjects[0], 90, 0);
        teachers[1].assignGrades(students[0], subjects[1], 85, 1);
        teachers[2].assignGrades(students[0], subjects[2], 70, 2);
        teachers[3].assignGrades(students[0], subjects[3], 80, 3);
        teachers[4].assignGrades(students[0], subjects[4], 95, 4);

        teachers[0].assignGrades(students[1], subjects[0], 50, 0);
        teachers[1].assignGrades(students[1], subjects[1], 40, 1);
        teachers[2].assignGrades(students[1], subjects[2], 55, 2);
        teachers[3].assignGrades(students[1], subjects[3], 60, 3);
        teachers[4].assignGrades(students[1], subjects[4], 65, 4);

        teachers[0].assignGrades(students[2], subjects[0], 30, 0);
        teachers[1].assignGrades(students[2], subjects[1], 25, 1);
        teachers[2].assignGrades(students[2], subjects[2], 40, 2);
        teachers[3].assignGrades(students[2], subjects[3], 45, 3);
        teachers[4].assignGrades(students[2], subjects[4], 50, 4);

        for (Student s : students) {
            s.displayResult(subjects);
        }

        for (Teacher t : teachers) {
            t.displayTeacherInfo();
        }

        Student top = Student.getTopStudent(students);
        System.out.println("Top Student: " + top.getStudentName() + " with " + top.getPercentage() + "%");

        System.out.println("Class Average: " + Student.getClassAverage(students));
        System.out.println("Pass Percentage: " + Student.getPassPercentage(students, subjects) + "%");
        System.out.println("Total Teachers: " + Teacher.getTotalTeachers());
    }
}
