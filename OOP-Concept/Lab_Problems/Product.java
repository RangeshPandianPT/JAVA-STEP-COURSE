class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String supplierName;
    private String category;

    private static int totalProducts = 0;
    private static double totalInventoryValue = 0;
    private static int lowStockCount = 0;
    private static String[] categories = new String[20];
    private static int categoryCount = 0;

    public Product(String productName, double price, int quantity, String supplierName, String category) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.category = category;
        this.productId = generateProductId();
        totalProducts++;

        if (!isCategoryExists(category)) {
            categories[categoryCount++] = category;
        }
        updateInventoryValue();
    }

    public void addStock(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid stock addition.");
            return;
        }
        this.quantity += quantity;
        updateInventoryValue();
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0 || quantity > this.quantity) {
            System.out.println("Invalid stock reduction.");
            return;
        }
        this.quantity -= quantity;
        updateInventoryValue();
    }

    public boolean isLowStock() {
        return quantity < 10;
    }

    public double calculateProductValue() {
        return price * quantity;
    }

    public void updatePrice(double newPrice) {
        if (newPrice <= 0) {
            System.out.println("Invalid price update.");
            return;
        }
        this.price = newPrice;
        updateInventoryValue();
    }

    public void displayProductInfo() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Name         : " + productName);
        System.out.println("Supplier     : " + supplierName);
        System.out.println("Category     : " + category);
        System.out.println("Price        : " + price);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Value        : " + calculateProductValue());
        System.out.println("Low Stock    : " + (isLowStock() ? "Yes" : "No"));
    }

    private void updateInventoryValue() {
        totalInventoryValue = 0;
        lowStockCount = 0;
    }

    private static boolean isCategoryExists(String category) {
        for (int i = 0; i < categoryCount; i++) {
            if (categories[i].equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    public static double calculateTotalInventoryValue(Product[] products) {
        double total = 0;
        for (Product p : products) {
            if (p != null) {
                total += p.calculateProductValue();
            }
        }
        totalInventoryValue = total;
        return total;
    }

    public static void findLowStockProducts(Product[] products) {
        System.out.println("Low Stock Products:");
        for (Product p : products) {
            if (p != null && p.isLowStock()) {
                p.displayProductInfo();
                lowStockCount++;
            }
        }
        if (lowStockCount == 0) {
            System.out.println("No products are in low stock.");
        }
    }

    public static void generateInventoryReport(Product[] products) {
        System.out.println("Total Products   : " + totalProducts);
        System.out.println("Total Value      : " + calculateTotalInventoryValue(products));
        System.out.println("Low Stock Count  : " + lowStockCount);
        System.out.print("Categories       : ");
        for (int i = 0; i < categoryCount; i++) {
            System.out.print(categories[i] + " ");
        }
    }

    public static Product searchProduct(Product[] products, String name) {
        for (Product p : products) {
            if (p != null && p.productName.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    private static String generateProductId() {
        return String.format("P%03d", totalProducts + 1);
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        Product[] products = new Product[5];
        int count = 0;

        products[count++] = new Product("Laptop", 60000, 15, "Dell Supplier", "Electronics");
        products[count++] = new Product("Smartphone", 30000, 8, "Samsung Supplier", "Electronics");
        products[count++] = new Product("Chair", 2000, 25, "Ikea Supplier", "Furniture");
        products[count++] = new Product("Table", 5000, 5, "Ikea Supplier", "Furniture");

        products[0].addStock(5);
        products[1].reduceStock(3);
        products[2].updatePrice(2200);

        for (int i = 0; i < count; i++) {
            products[i].displayProductInfo();
        }

        Product found = Product.searchProduct(products, "Table");
        if (found != null) {
            System.out.println("Product found:");
            found.displayProductInfo();
        } else {
            System.out.println("Product not found.");
        }

        Product.findLowStockProducts(products);
        Product.generateInventoryReport(products);
    }
}
