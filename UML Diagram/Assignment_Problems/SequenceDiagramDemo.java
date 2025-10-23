class Customer {
    void placeOrder(Cart cart, PaymentService payment, OrderService order) {
        cart.addItem("Laptop");
        cart.addItem("Mouse");
        payment.makePayment(cart.getTotalAmount());
        order.confirmOrder();
    }
}

class Cart {
    double totalAmount = 0;

    void addItem(String item) {
        if (item.equals("Laptop")) totalAmount += 60000;
        if (item.equals("Mouse")) totalAmount += 1000;
    }

    double getTotalAmount() {
        return totalAmount;
    }
}

class PaymentService {
    void makePayment(double amount) {
        System.out.println("Payment of ₹" + amount + " successful.");
    }
}

class OrderService {
    void confirmOrder() {
        System.out.println("Order confirmed and being processed.");
    }
}

public class SequenceDiagramDemo {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Cart cart = new Cart();
        PaymentService payment = new PaymentService();
        OrderService order = new OrderService();

        customer.placeOrder(cart, payment, order);
    }
}
