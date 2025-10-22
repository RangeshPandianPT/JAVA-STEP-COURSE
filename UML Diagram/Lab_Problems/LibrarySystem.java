class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }
}

class Member {
    private String memberId;
    private String name;
    private Book[] borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new Book[5];
    }

    public void borrowBook(Book book) {
        book.issueBook();
    }

    public void returnBook(Book book) {
        book.returnBook();
    }
}

class Librarian {
    private String librarianId;
    private String name;

    public Librarian(String librarianId, String name) {
        this.librarianId = librarianId;
        this.name = name;
    }

    public void addBook(Book book) { }

    public void removeBook(Book book) { }

    public void issueBook(Member member, Book book) {
        member.borrowBook(book);
    }
}

class Library {
    private Book[] books;
    private Member[] members;
    private Librarian librarian;

    public Library(Librarian librarian) {
        this.librarian = librarian;
        this.books = new Book[100];
        this.members = new Member[50];
    }

    public void addMember(Member member) { }

    public void showAvailableBooks() { }
}
