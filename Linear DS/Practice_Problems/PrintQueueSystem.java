import java.util.*;

public class PrintQueueSystem {
    public static void main(String[] args) {
        Queue<String> printQueue = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Command (ADD <doc>/PRINT/EXIT): ");
            String cmd = sc.next().toUpperCase();

            switch (cmd) {
                case "ADD":
                    String document = sc.next(); // Read document name
                    printQueue.add(document);    // Enqueue document
                    System.out.println("Added to queue: " + document);
                    break;

                case "PRINT":
                    if (!printQueue.isEmpty()) {
                        String nextDoc = printQueue.poll(); // Dequeue document
                        System.out.println("Printing " + nextDoc);
                    } else {
                        System.out.println("No jobs left!");
                    }
                    break;

                case "EXIT":
                    System.out.println("Exiting Print Queue System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }
}
