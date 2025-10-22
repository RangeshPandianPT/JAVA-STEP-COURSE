class Order {
    String state;

    Order() {
        state = "New";
    }

    void processOrder() {
        if (state.equals("New")) {
            state = "Processing";
        }
    }

    void shipOrder() {
        if (state.equals("Processing")) {
            state = "Shipped";
        }
    }

    void deliverOrder() {
        if (state.equals("Shipped")) {
            state = "Delivered";
        }
    }

    void cancelOrder() {
        if (state.equals("New") || state.equals("Processing")) {
            state = "Cancelled";
        }
    }

    void showState() {
        System.out.println("Current Order State: " + state);
    }
}

public class StateDiagramDemo {
    public static void main(String[] args) {
        Order order = new Order();
        order.showState();

        order.processOrder();
        order.showState();

        order.shipOrder();
        order.showState();

        order.deliverOrder();
        order.showState();
    }
}
