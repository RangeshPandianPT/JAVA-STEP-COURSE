import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public void showDetails() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn);
    }

    public String getTitle() {
        return title;
    }
}

class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book '" + book.getTitle() + "' to " + name + " Library");
    }

    public void showBooks() {
        System.out.println("Books in " + name + " Library:");
        for (Book book : books) {
            book.showDetails();
        }
    }
}

class Member {
    private String name;
    private List<Book> borrowedBooks;

    public Member(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        System.out.println(name + " borrowed book: " + book.getTitle());
    }

    public void showBorrowedBooks() {
        System.out.println("Books borrowed by " + name + ":");
        for (Book book : borrowedBooks) {
            book.showDetails();
        }
    }
}

public class LibraryDemo {
    public static void main(String[] args) {
        Library lib = new Library("Central City");

        Book b1 = new Book("Java Programming", "James Gosling", "ISBN001");
        Book b2 = new Book("Data Structures", "Robert Lafore", "ISBN002");
        Book b3 = new Book("Operating Systems", "Silberschatz", "ISBN003");

        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        lib.showBooks();

        Member m1 = new Member("Ravi");
        m1.borrowBook(b1);
        m1.borrowBook(b3);

        m1.showBorrowedBooks();
    }
}
