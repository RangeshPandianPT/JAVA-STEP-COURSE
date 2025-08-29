import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Book {
    String bookId;
    String title;
    String author;
    String isbn;
    String category;
    boolean isIssued;
    String issueDate;
    String dueDate;
    int timesIssued;
    Queue<String> reservationQueue = new LinkedList<>();

    Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId; this.title = title; this.author = author; this.isbn = isbn; this.category = category;
        this.isIssued = false; this.issueDate = ""; this.dueDate = ""; this.timesIssued = 0;
    }
}

abstract class Member {
    String memberId;
    String memberName;
    String memberType;
    Book[] booksIssued;
    double totalFines;
    String membershipDate;
    int maxAllowed;

    Member(String memberId, String memberName, String memberType, String membershipDate, int maxAllowed) {
        this.memberId = memberId; this.memberName = memberName; this.memberType = memberType;
        this.membershipDate = membershipDate; this.maxAllowed = maxAllowed;
        this.booksIssued = new Book[maxAllowed]; this.totalFines = 0.0;
    }

    boolean hasSpace() {
        for (Book b : booksIssued) if (b == null) return true;
        return false;
    }

    int countIssued() {
        int c = 0; for (Book b : booksIssued) if (b != null) c++; return c;
    }

    void addIssuedBook(Book b) {
        for (int i = 0; i < booksIssued.length; i++) if (booksIssued[i] == null) { booksIssued[i] = b; return; }
    }

    void removeIssuedBook(String bookId) {
        for (int i = 0; i < booksIssued.length; i++) if (booksIssued[i] != null && booksIssued[i].bookId.equals(bookId)) { booksIssued[i] = null; return; }
    }

    double calculateFine(Book b, LocalDate returnDate, double finePerDay) {
        if (b.dueDate == null || b.dueDate.isEmpty()) return 0;
        LocalDate due = LocalDate.parse(b.dueDate);
        if (!returnDate.isAfter(due)) return 0;
        long days = Duration.between(due.atStartOfDay(), returnDate.atStartOfDay()).toDays();
        double fine = days * finePerDay;
        this.totalFines += fine;
        return fine;
    }
}

class Student extends Member {
    Student(String memberId, String memberName, String membershipDate) { super(memberId, memberName, "Student", membershipDate, Library.maxBooksAllowedForStudent); }
}

class Faculty extends Member {
    Faculty(String memberId, String memberName, String membershipDate) { super(memberId, memberName, "Faculty", membershipDate, Library.maxBooksAllowedForFaculty); }
}

class General extends Member {
    General(String memberId, String memberName, String membershipDate) { super(memberId, memberName, "General", membershipDate, Library.maxBooksAllowedForGeneral); }
}

class Library {
    static int totalBooks = 0;
    static int totalMembers = 0;
    static String libraryName = "City Central Library";
    static double finePerDay = 5.0;
    static int maxBooksAllowed = 3;
    static int maxBooksAllowedForStudent = 5;
    static int maxBooksAllowedForFaculty = 10;
    static int maxBooksAllowedForGeneral = 2;

    DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    List<Book> books = new ArrayList<>();
    Map<String, Member> members = new HashMap<>();
    Map<String, Integer> issueCount = new HashMap<>();

    Library() {}

    Book createBook(String id, String title, String author, String isbn, String category) {
        Book b = new Book(id, title, author, isbn, category);
        books.add(b); totalBooks++; return b;
    }

    Member addMember(String id, String name, String type, String date) {
        Member m;
        if (type.equalsIgnoreCase("Student")) m = new Student(id, name, date);
        else if (type.equalsIgnoreCase("Faculty")) m = new Faculty(id, name, date);
        else m = new General(id, name, date);
        members.put(id, m); totalMembers++; return m;
    }

    Book searchBooks(String keyword) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(keyword) || b.bookId.equalsIgnoreCase(keyword) || b.isbn.equalsIgnoreCase(keyword)) return b;
        }
        return null;
    }

    List<Book> searchByTitle(String keyword) {
        List<Book> res = new ArrayList<>();
        for (Book b : books) if (b.title.toLowerCase().contains(keyword.toLowerCase())) res.add(b);
        return res;
    }

    boolean issueBook(String memberId, String bookId, String issueDateStr, int loanDays) {
        Member m = members.get(memberId);
        if (m == null) return false;
        Book b = null;
        for (Book x : books) if (x.bookId.equals(bookId)) { b = x; break; }
        if (b == null) return false;
        if (b.isIssued) return false;
        if (!m.hasSpace()) return false;
        LocalDate issueDate = LocalDate.parse(issueDateStr);
        LocalDate due = issueDate.plusDays(Math.max(1, loanDays));
        b.isIssued = true;
        b.issueDate = issueDate.format(F);
        b.dueDate = due.format(F);
        b.timesIssued++;
        m.addIssuedBook(b);
        issueCount.put(b.bookId, issueCount.getOrDefault(b.bookId, 0) + 1);
        return true;
    }

    boolean returnBook(String memberId, String bookId, String returnDateStr) {
        Member m = members.get(memberId);
        if (m == null) return false;
        Book b = null;
        for (Book x : books) if (x.bookId.equals(bookId)) { b = x; break; }
        if (b == null) return false;
        LocalDate ret = LocalDate.parse(returnDateStr);
        double fine = m.calculateFine(b, ret, finePerDay);
        b.isIssued = false;
        b.issueDate = ""; b.dueDate = "";
        m.removeIssuedBook(bookId);
        if (!b.reservationQueue.isEmpty()) {
            String nextMemberId = b.reservationQueue.poll();
            issueBook(nextMemberId, b.bookId, returnDateStr, 14);
        }
        return true;
    }

    boolean renewBook(String memberId, String bookId, int additionalDays) {
        Member m = members.get(memberId);
        if (m == null) return false;
        Book b = null;
        for (Book x : books) if (x.bookId.equals(bookId)) { b = x; break; }
        if (b == null) return false;
        if (!b.isIssued) return false;
        if (!m.membershipDate.equals(m.membershipDate)) {}
        if (!b.reservationQueue.isEmpty()) return false;
        LocalDate newDue = LocalDate.parse(b.dueDate).plusDays(additionalDays);
        b.dueDate = newDue.format(F);
        return true;
    }

    boolean reserveBook(String memberId, String bookId) {
        Member m = members.get(memberId);
        if (m == null) return false;
        Book b = null;
        for (Book x : books) if (x.bookId.equals(bookId)) { b = x; break; }
        if (b == null) return false;
        if (b.reservationQueue.contains(memberId)) return false;
        b.reservationQueue.add(memberId);
        return true;
    }

    List<Book> getOverdueBooks(String onDateStr) {
        LocalDate on = LocalDate.parse(onDateStr);
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            if (b.isIssued && b.dueDate != null && !b.dueDate.isEmpty()) {
                LocalDate due = LocalDate.parse(b.dueDate);
                if (on.isAfter(due)) res.add(b);
            }
        }
        return res;
    }

    List<Book> getMostPopularBooks(int topN) {
        List<Book> copy = new ArrayList<>(books);
        copy.sort((a, c) -> Integer.compare(issueCount.getOrDefault(c.bookId, 0), issueCount.getOrDefault(a.bookId, 0)));
        return copy.subList(0, Math.min(topN, copy.size()));
    }

    String generateLibraryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Library: ").append(libraryName).append("\n");
        sb.append("Total Books: ").append(totalBooks).append("\n");
        sb.append("Total Members: ").append(totalMembers).append("\n");
        sb.append("Books Issued Now: ").append(currentlyIssuedCount()).append("\n");
        sb.append("Total Fines Collected (approx): ").append(totalFinesCollected()).append("\n");
        sb.append("Most Popular Books:\n");
        for (Book b : getMostPopularBooks(5)) sb.append(b.bookId).append(" - ").append(b.title).append(" (").append(issueCount.getOrDefault(b.bookId, 0)).append(")\n");
        return sb.toString();
    }

    int currentlyIssuedCount() {
        int c = 0; for (Book b : books) if (b.isIssued) c++; return c;
    }

    double totalFinesCollected() {
        double sum = 0; for (Member m : members.values()) sum += m.totalFines; return sum;
    }

    void seedDemoData() {
        createBook("B001","Data Structures","S. Lipschutz","978-1","CS");
        createBook("B002","Algorithms","Cormen","978-2","CS");
        createBook("B003","Operating Systems","Tanenbaum","978-3","CS");
        createBook("B004","Database Systems","Elmasri","978-4","CS");
        createBook("B005","Discrete Math","Rosen","978-5","Math");
        addMember("M001","Aisha","Student","2024-06-01");
        addMember("M002","Rahul","Faculty","2023-01-15");
        addMember("M003","Nina","General","2024-02-20");
    }

    public static void main(String[] args) {
        Library lib = new Library();
        seedDemoOperations(lib);
    }

    static void seedDemoOperations(Library lib) {
        lib.seedDemoData();
        lib.issueBook("M001","B001","2025-08-01",14);
        lib.issueBook("M002","B002","2025-08-03",30);
        lib.reserveBook("M003","B001");
        lib.returnBook("M001","B001","2025-08-20");
        lib.renewBook("M002","B002",7);
        lib.issueBook("M003","B003","2025-08-10",7);
        System.out.println(lib.generateLibraryReport());
        System.out.println("Overdue on 2025-08-25:");
        for (Book b : lib.getOverdueBooks("2025-08-25")) System.out.println(b.bookId + " " + b.title + " due " + b.dueDate);
    }
}
