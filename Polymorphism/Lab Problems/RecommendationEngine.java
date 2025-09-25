abstract class Product {
    String id;
    String name;
    double price;

    Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    abstract void recommend();

    void updateProduct(double newPrice) {
        this.price = newPrice;
        System.out.println(name + " price updated to ₹" + price);
    }

    void updateProduct(int stock) {
        System.out.println(name + " stock updated to " + stock + " units.");
    }

    void updateProduct(double discountPercent, boolean applyDiscount) {
        if (applyDiscount) {
            price -= (price * discountPercent / 100);
            System.out.println(name + " discounted by " + discountPercent + "% → New Price ₹" + price);
        }
    }
}

class Electronics extends Product {
    int warranty; 
    String specs;

    Electronics(String id, String name, double price, int warranty, String specs) {
        super(id, name, price);
        this.warranty = warranty;
        this.specs = specs;
    }

    @Override
    void recommend() {
        System.out.println("Recommended Electronics: " + name + " | Specs: " + specs + " | Warranty: " + warranty + " months | Price: ₹" + price);
    }

    void showWarranty() {
        System.out.println(name + " has a warranty of " + warranty + " months.");
    }
}

class Clothing extends Product {
    String sizeChart;
    String style;

    Clothing(String id, String name, double price, String sizeChart, String style) {
        super(id, name, price);
        this.sizeChart = sizeChart;
        this.style = style;
    }

    @Override
    void recommend() {
        System.out.println("Recommended Clothing: " + name + " | Style: " + style + " | Sizes: " + sizeChart + " | Price: ₹" + price);
    }

    void matchStyle(String userStyle) {
        System.out.println("Style match for " + name + ": " + (style.equalsIgnoreCase(userStyle) ? "Perfect match!" : "Try a new look!"));
    }
}

class Book extends Product {
    String author;
    String genre;

    Book(String id, String name, double price, String author, String genre) {
        super(id, name, price);
        this.author = author;
        this.genre = genre;
    }

    @Override
    void recommend() {
        System.out.println("Recommended Book: " + name + " by " + author + " | Genre: " + genre + " | Price: ₹" + price);
    }

    void suggestSimilar() {
        System.out.println("Explore more books in " + genre + " genre.");
    }
}

public class RecommendationEngine {
    public static void main(String[] args) {
        Product[] catalog = {
            new Electronics("E101", "Smartphone", 25000, 24, "8GB RAM, 128GB Storage"),
            new Clothing("C202", "T-Shirt", 799, "S, M, L, XL", "Casual"),
            new Book("B303", "Java Programming", 599, "James Gosling", "Education")
        };

        for (Product p : catalog) {
            p.recommend();
        }

        System.out.println();

        catalog[0].updateProduct(23000);       
        catalog[1].updateProduct(50);          
        catalog[2].updateProduct(10, true);    

        System.out.println();
		
        for (Product p : catalog) {
            if (p instanceof Electronics) {
                ((Electronics) p).showWarranty();
            } else if (p instanceof Clothing) {
                ((Clothing) p).matchStyle("Casual");
            } else if (p instanceof Book) {
                ((Book) p).suggestSimilar();
            }
        }
    }
}
