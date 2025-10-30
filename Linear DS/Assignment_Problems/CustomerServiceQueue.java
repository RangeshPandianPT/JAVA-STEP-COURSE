import java.util.*;

public class CustomerServiceQueue {
    Queue<String> queue = new LinkedList<>();

    public void enqueue(String customer) {
        queue.add(customer);
        System.out.println(customer + " has arrived and joined the queue.");
        displayQueue();
    }

    public void dequeue() {
        if (queue.isEmpty()) {
            System.out.println("No customers to serve.");
        } else {
            String served = queue.remove();
            System.out.println("Serving customer: " + served);
            displayQueue();
        }
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.\n");
        } else {
            System.out.println("Current Queue: " + queue + "\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerServiceQueue service = new CustomerServiceQueue();

        service.enqueue("Alice");
        service.enqueue("Bob");
        service.enqueue("Charlie");

        service.dequeue();
        service.dequeue();

        service.enqueue("David");
        service.displayQueue();
    }
}
