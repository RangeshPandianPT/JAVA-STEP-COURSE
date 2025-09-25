class LibraryUser {
    String name;
    String id;

    LibraryUser(String name, String id) {
        this.name = name;
        this.id = id;
    }

    void logEntry() {
        System.out.println(name + " (ID: " + id + ") has entered the library.");
    }

    void displayInfo() {
        System.out.println("User: " + name + " | ID: " + id);
    }
}

class Student extends LibraryUser {
    Student(String name, String id) {
        super(name, id);
    }

    void borrowBook(String book) {
        System.out.println(name + " borrowed the book: " + book);
    }

    void accessComputer() {
        System.out.println(name + " is using a library computer.");
    }
}

class Faculty extends LibraryUser {
    Faculty(String name, String id) {
        super(name, id);
    }

    void reserveBook(String book) {
        System.out.println(name + " reserved the book: " + book);
    }

    void accessResearchDatabase() {
        System.out.println(name + " is accessing the research database.");
    }
}

class Guest extends LibraryUser {
    Guest(String name, String id) {
        super(name, id);
    }

    void browseBooks() {
        System.out.println(name + " is browsing books in the library.");
    }
}

public class UniversityLibrary {
    public static void main(String[] args) {
        LibraryUser[] users = {
            new Student("Alice", "S101"),
            new Faculty("Dr. Smith", "F202"),
            new Guest("John", "G303")
        };

        for (LibraryUser user : users) {
            user.logEntry();
            user.displayInfo();
            System.out.println();
        }
        Student s = (Student) users[0];
        s.borrowBook("Data Structures");
        s.accessComputer();

        Faculty f = (Faculty) users[1];
        f.reserveBook("Artificial Intelligence");
        f.accessResearchDatabase();

        Guest g = (Guest) users[2];
        g.browseBooks();
    }
}
