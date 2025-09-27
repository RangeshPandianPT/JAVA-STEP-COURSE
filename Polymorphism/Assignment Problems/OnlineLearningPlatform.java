import java.time.LocalDate;

class Course {
    String title;
    String instructor;
    LocalDate enrollmentDate;

    Course(String title, String instructor, LocalDate enrollmentDate) {
        this.title = title;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }

    public void showProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled On: " + enrollmentDate);
        System.out.println("Progress details unavailable");
    }
}

class VideoCourse extends Course {
    int completionPercentage;
    int watchTimeMinutes;

    VideoCourse(String title, String instructor, LocalDate enrollmentDate, int completionPercentage, int watchTimeMinutes) {
        super(title, instructor, enrollmentDate);
        this.completionPercentage = completionPercentage;
        this.watchTimeMinutes = watchTimeMinutes;
    }

    @Override
    public void showProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled On: " + enrollmentDate);
        System.out.println("Completion: " + completionPercentage + "%");
        System.out.println("Watch Time: " + watchTimeMinutes + " minutes");
    }
}

class InteractiveCourse extends Course {
    int quizScore;
    int projectsCompleted;

    InteractiveCourse(String title, String instructor, LocalDate enrollmentDate, int quizScore, int projectsCompleted) {
        super(title, instructor, enrollmentDate);
        this.quizScore = quizScore;
        this.projectsCompleted = projectsCompleted;
    }

    @Override
    public void showProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled On: " + enrollmentDate);
        System.out.println("Quiz Score: " + quizScore + "%");
        System.out.println("Projects Completed: " + projectsCompleted);
    }
}

class ReadingCourse extends Course {
    int pagesRead;
    int notesTaken;

    ReadingCourse(String title, String instructor, LocalDate enrollmentDate, int pagesRead, int notesTaken) {
        super(title, instructor, enrollmentDate);
        this.pagesRead = pagesRead;
        this.notesTaken = notesTaken;
    }

    @Override
    public void showProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled On: " + enrollmentDate);
        System.out.println("Pages Read: " + pagesRead);
        System.out.println("Notes Taken: " + notesTaken);
    }
}

class CertificationCourse extends Course {
    int examAttempts;
    boolean certified;

    CertificationCourse(String title, String instructor, LocalDate enrollmentDate, int examAttempts, boolean certified) {
        super(title, instructor, enrollmentDate);
        this.examAttempts = examAttempts;
        this.certified = certified;
    }

    @Override
    public void showProgress() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled On: " + enrollmentDate);
        System.out.println("Exam Attempts: " + examAttempts);
        System.out.println("Certification Status: " + (certified ? "Achieved" : "Not Yet Achieved"));
    }
}

public class OnlineLearningPlatform {
    public static void main(String[] args) {
        Course c1 = new VideoCourse("Java Basics", "Dr. Smith", LocalDate.of(2025, 1, 10), 70, 350);
        Course c2 = new InteractiveCourse("Data Science Lab", "Prof. Allen", LocalDate.of(2025, 2, 5), 85, 4);
        Course c3 = new ReadingCourse("History 101", "Dr. Green", LocalDate.of(2025, 3, 15), 120, 25);
        Course c4 = new CertificationCourse("Cloud Certification", "Mr. Brown", LocalDate.of(2025, 4, 1), 2, false);

        c1.showProgress();
        c2.showProgress();
        c3.showProgress();
        c4.showProgress();
    }
}
