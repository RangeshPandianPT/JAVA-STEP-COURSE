import java.util.*;
class Product {
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    private static int totalProducts = 0;
    private static String[] categories = {"Electronics", "Clothing", "Books", "Groceries"};

    public Product(String productName, double price, String category, int stockQuantity) {
        this.productId = "P" + (1000 + (++totalProducts));
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }
	
    public void reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }

    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }

    public void displayProduct() {
        System.out.printf("%-6s %-20s %-12s %-10.2f %-6d\n",
                productId, productName, category, price, stockQuantity);
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.getProductId().equalsIgnoreCase(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getProductsByCategory(Product[] products, String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p != null && p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }
}
class ShoppingCart {
    private String cartId;
    private String customerName;
    private List<Product> products;
    private List<Integer> quantities;
    private double cartTotal;
	
    public ShoppingCart(String customerName) {
        this.cartId = "C" + new Random().nextInt(1000, 9999);
        this.customerName = customerName;
        this.products = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.cartTotal = 0.0;
    }
    public void addProduct(Product product, int quantity) {
        if (product.getStockQuantity() >= quantity) {
            int index = products.indexOf(product);
            if (index >= 0) {
                quantities.set(index, quantities.get(index) + quantity);
            } else {
                products.add(product);
                quantities.add(quantity);
            }
            product.reduceStock(quantity);
            calculateTotal();
            System.out.println(quantity + " x " + product.getProductName() + " added to cart.");
        } else {
            System.out.println("Not enough stock available!");
        }
    }
    public void removeProduct(String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equalsIgnoreCase(productId)) {
                Product removedProduct = products.get(i);
                int qty = quantities.get(i);
                removedProduct.increaseStock(qty); // return stock
                products.remove(i);
                quantities.remove(i);
                calculateTotal();
                System.out.println("Product " + productId + " removed from cart.");
                return;
            }
        }
        System.out.println("Product not found in cart!");
    }
    public void calculateTotal() {
        cartTotal = 0;
        for (int i = 0; i < products.size(); i++) {
            cartTotal += products.get(i).getPrice() * quantities.get(i);
        }
    }
    public void displayCart() {
        System.out.println("Cart ID: " + cartId + " | Customer: " + customerName);
        if (products.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.printf("%-6s %-20s %-10s %-8s %-10s\n",
                "ID", "Product", "Price", "Qty", "Total");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            int qty = quantities.get(i);
            double total = p.getPrice() * qty;
            System.out.printf("%-6s %-20s %-10.2f %-8d %-10.2f\n",
                    p.getProductId(), p.getProductName(), p.getPrice(), qty, total);
        }
        System.out.println("Cart Total: " + cartTotal + "\n");
    }
    public void checkout() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }
        displayCart();
        System.out.println("Checkout successful! Thank you for shopping, " + customerName + ".");
        products.clear();
        quantities.clear();
        cartTotal = 0;
    }
}
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = {
                new Product("Laptop", 60000, "Electronics", 5),
                new Product("Headphones", 2000, "Electronics", 15),
                new Product("Smartphone", 35000, "Electronics", 10),
                new Product("T-Shirt", 500, "Clothing", 25),
                new Product("Jeans", 1200, "Clothing", 20),
                new Product("Jacket", 2500, "Clothing", 10),
                new Product("Novel - Java Basics", 400, "Books", 30),
                new Product("Cookbook", 300, "Books", 15),
                new Product("Rice (10kg)", 800, "Groceries", 50),
                new Product("Milk (1L)", 50, "Groceries", 100)
        };

        System.out.print("Enter your name: ");
        String customerName = sc.nextLine();
        ShoppingCart cart = new ShoppingCart(customerName);

        int choice;
        do {
            System.out.println("\n==== Online Shopping Menu ====");
            System.out.println("1. Browse All Products");
            System.out.println("2. Search Product by ID");
            System.out.println("3. Browse by Category");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.printf("%-6s %-20s %-12s %-10s %-6s\n", "ID", "Name", "Category", "Price", "Stock");
                    for (Product p : products) {
                        p.displayProduct();
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    Product found = Product.findProductById(products, pid);
                    if (found != null) {
                        found.displayProduct();
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Category (Electronics/Clothing/Books/Groceries): ");
                    String cat = sc.nextLine();
                    List<Product> list = Product.getProductsByCategory(products, cat);
                    if (!list.isEmpty()) {
                        System.out.printf("%-6s %-20s %-12s %-10s %-6s\n", "ID", "Name", "Category", "Price", "Stock");
                        for (Product p : list) {
                            p.displayProduct();
                        }
                    } else {
                        System.out.println("No products found in this category.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    String addId = sc.nextLine();
                    Product addProd = Product.findProductById(products, addId);
                    if (addProd != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(addProd, qty);
                    } else {
                        System.out.println("Invalid Product ID!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Product ID to remove: ");
                    String remId = sc.nextLine();
                    cart.removeProduct(remId);
                    break;

                case 6:
                    cart.displayCart();
                    break;

                case 7:
                    cart.checkout();
                    break;

                case 8:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}
