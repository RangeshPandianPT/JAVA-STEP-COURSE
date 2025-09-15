package ecommerce;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    private ECommerceSystem() {}

    public static boolean processOrder(Object order, Object customer) {
        if (!(order instanceof Order)) return false;
        if (!(customer instanceof Customer)) return false;
        return true;
    }

    public static void addProduct(String id, Object product) {
        productCatalog.put(id, product);
    }

    public static Object getProduct(String id) {
        return productCatalog.get(id);
    }

    public static final class Product implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String productId;
        private final String name;
        private final String category;
        private final String manufacturer;
        private final double basePrice;
        private final double weight;
        private final String[] features;
        private final Map<String, String> specifications;

        private Product(String productId, String name, String category, String manufacturer, double basePrice, double weight, String[] features, Map<String, String> specifications) {
            Objects.requireNonNull(productId);
            Objects.requireNonNull(name);
            Objects.requireNonNull(category);
            Objects.requireNonNull(manufacturer);
            Objects.requireNonNull(features);
            Objects.requireNonNull(specifications);
            this.productId = productId;
            this.name = name;
            this.category = category;
            this.manufacturer = manufacturer;
            this.basePrice = basePrice;
            this.weight = weight;
            this.features = Arrays.copyOf(features, features.length);
            this.specifications = Collections.unmodifiableMap(new HashMap<>(specifications));
        }

        public static Product createElectronics(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
            return new Product(id, name, "Electronics", manufacturer, price, weight, features, specs);
        }

        public static Product createClothing(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
            return new Product(id, name, "Clothing", manufacturer, price, weight, features, specs);
        }

        public static Product createBooks(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
            return new Product(id, name, "Books", manufacturer, price, weight, features, specs);
        }

        public String getProductId() {
            return productId;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public double getBasePrice() {
            return basePrice;
        }

        public double getWeight() {
            return weight;
        }

        public String[] getFeatures() {
            return Arrays.copyOf(features, features.length);
        }

        public Map<String, String> getSpecifications() {
            return new HashMap<>(specifications);
        }

        public final double calculateTax(String region) {
            double rate = 0.1;
            if ("EU".equalsIgnoreCase(region)) rate = 0.2;
            if ("US".equalsIgnoreCase(region)) rate = 0.08;
            return basePrice * rate;
        }

        @Override
        public String toString() {
            return "Product{" + "id='" + productId + '\'' + ", name='" + name + '\'' + ", category='" + category + '\'' + ", price=" + basePrice + '}';
        }
    }

    public static class Customer implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String customerId;
        private final String email;
        private String name;
        private String phoneNumber;
        private String preferredLanguage;
        private final String accountCreationDate;

        public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage, String accountCreationDate) {
            Objects.requireNonNull(customerId);
            Objects.requireNonNull(email);
            Objects.requireNonNull(accountCreationDate);
            this.customerId = customerId;
            this.email = email;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.preferredLanguage = preferredLanguage;
            this.accountCreationDate = accountCreationDate;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPreferredLanguage() {
            return preferredLanguage;
        }

        public void setPreferredLanguage(String preferredLanguage) {
            this.preferredLanguage = preferredLanguage;
        }

        public String getAccountCreationDate() {
            return accountCreationDate;
        }

        String getCreditRating() {
            return "GOOD";
        }

        public String getPublicProfile() {
            return "Customer{name='" + name + '\'' + ", preferredLanguage='" + preferredLanguage + '\'' + '}';
        }
    }

    public static class ShoppingCart implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String cartId;
        private final String customerId;
        private final List<Object> items;
        private double totalAmount;
        private int itemCount;

        public ShoppingCart(String cartId, String customerId) {
            this.cartId = cartId;
            this.customerId = customerId;
            this.items = new ArrayList<>();
            this.totalAmount = 0.0;
            this.itemCount = 0;
        }

        public boolean addItem(Object product, int quantity) {
            if (!(product instanceof Product)) return false;
            Product p = (Product) product;
            for (int i = 0; i < quantity; i++) items.add(p);
            itemCount += quantity;
            totalAmount += p.getBasePrice() * quantity - calculateDiscount();
            return true;
        }

        private double calculateDiscount() {
            if (itemCount > 10) return totalAmount * 0.1;
            return 0.0;
        }

        String getCartSummary() {
            return "Cart{" + "id='" + cartId + '\'' + ", items=" + itemCount + ", total=" + totalAmount + '}';
        }
    }

    public static class Order implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String orderId;
        private final LocalDateTime orderTime;

        public Order(String orderId) {
            this.orderId = orderId;
            this.orderTime = LocalDateTime.now();
        }

        public String getOrderId() {
            return orderId;
        }

        public LocalDateTime getOrderTime() {
            return orderTime;
        }
    }

    public static class PaymentProcessor implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String processorId;
        private final String securityKey;

        public PaymentProcessor(String processorId, String securityKey) {
            this.processorId = processorId;
            this.securityKey = securityKey;
        }

        public boolean processPayment(double amount) {
            return amount > 0;
        }
    }

    public static class ShippingCalculator implements Serializable {
        private static final long serialVersionUID = 1L;

        private final Map<String, Double> shippingRates;

        public ShippingCalculator(Map<String, Double> rates) {
            this.shippingRates = new HashMap<>(rates);
        }

        public double calculateShipping(String region, double weight) {
            double rate = shippingRates.getOrDefault(region, 10.0);
            return weight * rate;
        }
    }
}