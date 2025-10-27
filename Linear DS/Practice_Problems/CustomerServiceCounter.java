import java.util.*;

public class CustomerServiceCounter {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Command (ARRIVE <name>/SERVE/STATUS/EXIT): ");
            String cmd = sc.next().toUpperCase();

            switch (cmd) {
                case "ARRIVE":
                    String name = sc.next();
                    queue.add(name); // Add customer to queue
                    System.out.println(name + " has arrived and joined the queue.");
                    break;

                case "SERVE":
                    if (!queue.isEmpty()) {
                        String served = queue.poll(); // Remove from queue
                        System.out.println("Serving " + served);
                    } else {
                        System.out.println("No customers to serve!");
                    }
                    break;

                case "STATUS":
                    if (queue.isEmpty()) {
                        System.out.println("No customers waiting.");
                    } else {
                        System.out.println("Waiting: " + queue);
                    }
                    break;

                case "EXIT":
                    System.out.println("Exiting Customer Service Counter Simulation...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }
}
