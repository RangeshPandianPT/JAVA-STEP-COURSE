import java.util.*;
import java.util.stream.*;

class Subject {
    String subjectCode, subjectName;
    int credits;
    String instructor;
    Subject(String c, String n, int cr, String ins){ subjectCode=c; subjectName=n; credits=cr; instructor=ins;}
}

class Student {
    String studentId, studentName, className;
    String[] subjects;           
    double[][] marks;           
    double gpa;
    static int totalStudents = 0;
    static String schoolName = "Greenfield High";
    static String[] gradingScale = {"A:90-100","B:75-89","C:60-74","D:50-59","F:0-49"};
    static double passPercentage = 50.0;

    Student(String id, String name, String cls, String[] subjects){
        studentId=id; studentName=name; className=cls; this.subjects=subjects;
        marks = new double[subjects.length][1];
        totalStudents++;
    }
    public void addMarks(String subjectCode, double score){
        for(int i=0;i<subjects.length;i++) if(subjects[i].equalsIgnoreCase(subjectCode)){ marks[i][0]=score; return; }
        System.out.println("Subject not found for "+studentId);
    }
    private double gradePoint(double pct){
        if(pct>=90) return 4.0;
        if(pct>=75) return 3.0;
        if(pct>=60) return 2.0;
        if(pct>=50) return 1.0;
        return 0.0;
    }

    public void calculateGPA(Map<String,Subject> subjMap){
        double totalPoints=0, totalCredits=0;
        for(int i=0;i<subjects.length;i++){
            Subject s = subjMap.get(subjects[i]);
            if(s==null) continue;
            double pct = marks[i][0];
            double gp = gradePoint(pct);
            totalPoints += gp * s.credits;
            totalCredits += s.credits;
        }
        gpa = totalCredits==0?0: Math.round((totalPoints/totalCredits)*100.0)/100.0;
    }

    public String generateReportCard(Map<String,Subject> subjMap){
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Report Card: ").append(studentName).append(" (").append(studentId).append(") ---\n");
        sb.append(String.format("%-8s %-14s %-7s %-6s\n","Code","Subject","Marks","Grade"));
        for(int i=0;i<subjects.length;i++){
            Subject s=subjMap.get(subjects[i]);
            double pct = marks[i][0];
            String grade = (pct>=90?"A":pct>=75?"B":pct>=60?"C":pct>=50?"D":"F");
            sb.append(String.format("%-8s %-14s %-7.2f %-6s\n", subjects[i], s!=null?s.subjectName: "-", pct, grade));
        }
        sb.append("GPA: ").append(gpa).append("\n");
        sb.append("Promotion Eligible: ").append(checkPromotionEligibility() ? "Yes":"No").append("\n");
        return sb.toString();
    }

    public boolean checkPromotionEligibility(){
        double sum=0; int n=marks.length;
        for(double[] m: marks){ sum += m[0]; if(m[0] < passPercentage) return false; }
        return n==0?false: (sum/n >= passPercentage);
    }
}
class SchoolUtils {
    public static void setGradingScale(String[] scale){ Student.gradingScale = scale; }

    public static double calculateClassAverage(Student[] students){
        double total=0; int count=0;
        for(Student s: students){
            for(double[] m: s.marks){ total += m[0]; count++; }
        }
        return count==0?0: Math.round((total/count)*100.0)/100.0;
    }

    public static List<Student> getTopPerformers(Student[] students, int count){
        return Arrays.stream(students)
                .sorted((a,b)->Double.compare(b.gpa, a.gpa))
                .limit(count)
                .collect(Collectors.toList());
    }

    public static String generateSchoolReport(Student[] students){
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== School Report: ").append(Student.schoolName).append(" ===\n");
        sb.append("Total Students: ").append(Student.totalStudents).append("\n");
        sb.append("Class Average: ").append(calculateClassAverage(students)).append("\n");
        sb.append("Top Performers:\n");
        List<Student> top = getTopPerformers(students, Math.min(3, students.length));
        for(Student s: top) sb.append(s.studentName).append(" (").append(s.gpa).append(")\n");
        return sb.toString();
    }
}
public class GradeManager {
    public static void main(String[] args){
        Subject s1=new Subject("MATH","Mathematics",4,"Mr. Rao");
        Subject s2=new Subject("PHY","Physics",3,"Ms. Anand");
        Subject s3=new Subject("ENG","English",2,"Mrs. Das");
        Map<String,Subject> subjMap = Map.of("MATH",s1,"PHY",s2,"ENG",s3);

        Student st1=new Student("S001","Riya","10A", new String[]{"MATH","PHY","ENG"});
        Student st2=new Student("S002","Vikram","10A", new String[]{"MATH","PHY","ENG"});
        Student st3=new Student("S003","Neha","10A", new String[]{"MATH","PHY","ENG"});
        Student[] students = {st1,st2,st3};

        st1.addMarks("MATH", 95); st1.addMarks("PHY", 88); st1.addMarks("ENG", 76);
        st2.addMarks("MATH", 65); st2.addMarks("PHY", 58); st2.addMarks("ENG", 72);
        st3.addMarks("MATH", 45); st3.addMarks("PHY", 55); st3.addMarks("ENG", 60);

        for(Student s: students) s.calculateGPA(subjMap);

        for(Student s: students) System.out.println(s.generateReportCard(subjMap));

        System.out.println("Class Average: " + SchoolUtils.calculateClassAverage(students));
        System.out.println(SchoolUtils.generateSchoolReport(students));

        List<Student> top = SchoolUtils.getTopPerformers(students,2);
        System.out.println("Top 2:");
        top.forEach(x -> System.out.println(x.studentName + " - GPA: " + x.gpa));
    }
}
