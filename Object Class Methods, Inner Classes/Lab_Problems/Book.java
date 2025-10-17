public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Book other = (Book) obj;
        return title.equals(other.title) && author.equals(other.author);
    }

    public static void main(String[] args) {
        Book b1 = new Book("The Alchemist", "Paulo Coelho");
        Book b2 = new Book("The Alchemist", "Paulo Coelho");
        Book b3 = b1;

        System.out.println("Comparing b1 and b2 using == : " + (b1 == b2));
        System.out.println("Comparing b1 and b2 using equals() : " + b1.equals(b2));

        System.out.println("Comparing b1 and b3 using == : " + (b1 == b3));
        System.out.println("Comparing b1 and b3 using equals() : " + b1.equals(b3));
    }
}
