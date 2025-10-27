import java.util.*;

public class BrowserNavigation {
    public static void main(String[] args) {
        Stack<String> backStack = new Stack<>();
        Stack<String> forwardStack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String current = "Home";

        while (true) {
            System.out.print("Command (VISIT/BACK/FORWARD/PRINT/EXIT): ");
            String cmd = sc.next().toUpperCase();

            switch (cmd) {
                case "VISIT":
                    System.out.print("Enter URL: ");
                    String url = sc.next();
                    backStack.push(current); // store current page in back stack
                    current = url;           // change to new page
                    forwardStack.clear();    // clear forward history
                    System.out.println("Visited: " + current);
                    break;

                case "BACK":
                    if (!backStack.isEmpty()) {
                        forwardStack.push(current);
                        current = backStack.pop();
                        System.out.println("Moved Back to: " + current);
                    } else {
                        System.out.println("No previous pages!");
                    }
                    break;

                case "FORWARD":
                    if (!forwardStack.isEmpty()) {
                        backStack.push(current);
                        current = forwardStack.pop();
                        System.out.println("Moved Forward to: " + current);
                    } else {
                        System.out.println("No forward pages!");
                    }
                    break;

                case "PRINT":
                    System.out.println("Current Page: " + current);
                    break;

                case "EXIT":
                    System.out.println("Exiting Browser Simulation...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }
}
