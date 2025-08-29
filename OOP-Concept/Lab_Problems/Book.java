class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    private static int totalBooks = 0;
    private static int availableBooks = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            return true;
        }
        return false;
    }

    public void displayBookInfo() {
        System.out.println("Book ID     : " + bookId);
        System.out.println("Title       : " + title);
        System.out.println("Author      : " + author);
        System.out.println("Available   : " + isAvailable);
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }

    private static String generateBookId() {
        return String.format("B%03d", totalBooks + 1);
    }
}


class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;

    private static int totalMembers = 0;

    public Member(String memberName, int maxBooks) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[maxBooks];
        this.bookCount = 0;
        totalMembers++;
    }

    public void borrowBook(Book book) {
        if (bookCount == booksIssued.length) {
            System.out.println(memberName + " has reached the maximum book limit.");
            return;
        }
        if (book.issueBook()) {
            booksIssued[bookCount++] = book.getBookId();
            System.out.println(memberName + " borrowed " + book.getBookId());
        } else {
            System.out.println("Book " + book.getBookId() + " is not available.");
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (Book b : books) {
                    if (b != null && b.getBookId().equals(bookId)) {
                        if (b.returnBook()) {
                            System.out.println(memberName + " returned " + bookId);
                            booksIssued[i] = booksIssued[bookCount - 1];
                            booksIssued[bookCount - 1] = null;
                            bookCount--;
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(memberName + " does not have book " + bookId);
    }

    public void displayMemberInfo() {
        System.out.println("Member ID   : " + memberId);
        System.out.println("Name        : " + memberName);
        System.out.print("Books Issued: ");
        if (bookCount == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.print(booksIssued[i] + " ");
            }
            System.out.println();
        }
    }

    public static int getTotalMembers() {
        return totalMembers;
    }

    private static String generateMemberId() {
        return String.format("M%03d", totalMembers + 1);
    }
}


public class LibrarySystem {
    public static void main(String[] args) {
        Book[] books = new Book[5];
        int bookCount = 0;

        books[bookCount++] = new Book("Java Programming", "James Gosling");
        books[bookCount++] = new Book("Python Basics", "Guido van Rossum");
        books[bookCount++] = new Book("C++ Primer", "Bjarne Stroustrup");

        Member[] members = new Member[3];
        int memberCount = 0;

        members[memberCount++] = new Member("Alice", 2);
        members[memberCount++] = new Member("Bob", 3);

        members[0].borrowBook(books[0]);
        members[0].borrowBook(books[1]);
        members[1].borrowBook(books[1]);
        members[0].returnBook("B001", books);
        members[1].borrowBook(books[0]);

        for (int i = 0; i < bookCount; i++) {
            books[i].displayBookInfo();
        }

        for (int i = 0; i < memberCount; i++) {
            members[i].displayMemberInfo();
        }

        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
        System.out.println("Total Members: " + Member.getTotalMembers());
    }
}
