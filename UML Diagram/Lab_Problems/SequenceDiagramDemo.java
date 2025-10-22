class Customer {
    void placeOrder(OrderService orderService) {
        orderService.createOrder(this);
    }
}

class OrderService {
    void createOrder(Customer customer) {
        PaymentGateway paymentGateway = new PaymentGateway();
        InventoryService inventoryService = new InventoryService();

        if (inventoryService.checkStock()) {
            paymentGateway.processPayment();
            confirmOrder(customer);
        }
    }

    void confirmOrder(Customer customer) { }
}

class PaymentGateway {
    void processPayment() { }
}

class InventoryService {
    boolean checkStock() {
        return true;
    }
}

public class SequenceDiagramDemo {
    public static void main(String[] args) {
        Customer customer = new Customer();
        OrderService orderService = new OrderService();

        customer.placeOrder(orderService);
    }
}
