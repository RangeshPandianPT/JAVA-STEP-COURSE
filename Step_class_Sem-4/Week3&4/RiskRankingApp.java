class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + "(" + riskScore + ", ₹" + accountBalance + ")";
    }
}

public class RiskRankingApp {

    // 🔹 Bubble Sort (Ascending)
    public static int bubbleSortAscending(Client[] clients) {
        int n = clients.length;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;

                    swapCount++;
                    swapped = true;
                }
            }

            if (!swapped) break; // Adaptive optimization
        }

        return swapCount;
    }

    // 🔹 Insertion Sort (Descending + Account Balance)
    public static void insertionSortDescending(Client[] clients) {
        int n = clients.length;

        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 &&
                  (clients[j].riskScore < key.riskScore ||
                  (clients[j].riskScore == key.riskScore &&
                   clients[j].accountBalance < key.accountBalance))) {

                clients[j + 1] = clients[j];
                j--;
            }

            clients[j + 1] = key;
        }
    }

    // 🔹 Print Array
    public static void printArray(Client[] clients) {
        for (Client c : clients) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    // 🔹 Top N Clients
    public static void printTopN(Client[] clients, int topN) {
        System.out.println("\nTop " + topN + " High Risk Clients:");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.println(clients[i]);
        }
    }

    // 🔹 Main Method
    public static void main(String[] args) {

        Client[] clients = {
            new Client("clientC", 80, 50000),
            new Client("clientA", 20, 30000),
            new Client("clientB", 50, 70000)
        };

        // 🔸 Bubble Sort (Ascending)
        int swaps = bubbleSortAscending(clients);
        System.out.println("Bubble Sort (Ascending):");
        printArray(clients);
        System.out.println("Total Swaps: " + swaps);

        // 🔸 Insertion Sort (Descending)
        insertionSortDescending(clients);
        System.out.println("\nInsertion Sort (Descending):");
        printArray(clients);

        // 🔸 Top 3 (change to 10 for real case)
        printTopN(clients, 3);
    }
}

