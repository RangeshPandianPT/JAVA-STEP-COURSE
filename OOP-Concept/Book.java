import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;

    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int qty) {
        this.quantity += qty;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public void displayBook() {
        System.out.println("Title: " + title + " | Author: " + author + " | ISBN: " + isbn +
                " | Price: " + price + " | Quantity: " + quantity);
    }
}

class Library {
    private String libraryName;
    private Book[] books;
    private int totalBooks;

    public Library(String libraryName, int capacity) {
        this.libraryName = libraryName;
        this.books = new Book[capacity];
        this.totalBooks = 0;
    }

    public void addBook(Book book) {
        if (totalBooks < books.length) {
            books[totalBooks] = book;
            totalBooks++;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (int i = 0; i < totalBooks; i++) {
            if (books[i].getTitle().equalsIgnoreCase(keyword) ||
                books[i].getAuthor().equalsIgnoreCase(keyword)) {
                books[i].displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with keyword: " + keyword);
        }
    }

    public void displayInventory() {
        System.out.println("\n=== Library Inventory (" + libraryName + ") ===");
        if (totalBooks == 0) {
            System.out.println("No books in library.");
            return;
        }
        for (int i = 0; i < totalBooks; i++) {
            books[i].displayBook();
        }
    }

    public double calculateTotalValue() {
        double total = 0;
        for (int i = 0; i < totalBooks; i++) {
            total += books[i].getTotalValue();
        }
        return total;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Library library = new Library("Central Library", 100);

        while (true) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Calculate Total Value");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();

                    Book newBook = new Book(title, author, isbn, price, qty);
                    library.addBook(newBook);
                    break;

                case 2:
                    System.out.print("Enter Title or Author to Search: ");
                    String keyword = scanner.nextLine();
                    library.searchBook(keyword);
                    break;

                case 3:
                    library.displayInventory();
                    break;

                case 4:
                    System.out.println("Total Value of All Books: " + library.calculateTotalValue());
                    break;

                case 5:
                    System.out.println("Exiting Library System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
