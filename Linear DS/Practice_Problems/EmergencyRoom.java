import java.util.*;

class Patient {
    String name;
    int priority; // 1 = low, 3 = high

    Patient(String n, int p) {
        name = n;
        priority = p;
    }

    @Override
    public String toString() {
        return name + " (Priority " + priority + ")";
    }
}

public class EmergencyRoom {
    public static void main(String[] args) {
        // Higher priority patients (larger number) treated first
        PriorityQueue<Patient> pq = new PriorityQueue<>(
                Comparator.comparingInt((Patient p) -> -p.priority)
        );

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Command (ARRIVE <name> <priority>/TREAT/STATUS/EXIT): ");
            String cmd = sc.next().toUpperCase();

            switch (cmd) {
                case "ARRIVE":
                    String name = sc.next();
                    int priority = sc.nextInt();
                    pq.add(new Patient(name, priority));
                    System.out.println(name + " arrived with priority " + priority);
                    break;

                case "TREAT":
                    if (!pq.isEmpty()) {
                        Patient next = pq.poll();
                        System.out.println("Treating " + next.name + " (Priority " + next.priority + ")");
                    } else {
                        System.out.println("No patients to treat!");
                    }
                    break;

                case "STATUS":
                    if (pq.isEmpty()) {
                        System.out.println("No patients waiting.");
                    } else {
                        // Convert queue to list for display (without modifying order)
                        List<Patient> waiting = new ArrayList<>(pq);
                        waiting.sort((a, b) -> Integer.compare(b.priority, a.priority));
                        System.out.println("Waiting: " + waiting);
                    }
                    break;

                case "EXIT":
                    System.out.println("Exiting Emergency Room Simulation...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Command!");
                    break;
            }
        }
    }
}
