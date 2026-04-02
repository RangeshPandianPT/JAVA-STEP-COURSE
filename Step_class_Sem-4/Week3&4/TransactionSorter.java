import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // format HH:MM

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionSorter {

    // ------------------- Bubble Sort (Stable) -------------------
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("Bubble Sort Result: " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // ------------------- Insertion Sort (Stable) -------------------
    public static void insertionSort(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Sort by fee first, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort Result: " + list);
    }

    // Comparator: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    public static void detectOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }

        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.println("High-fee outliers: " + outliers);
        }
    }

    public static void processTransactions(List<Transaction> transactions) {
        int size = transactions.size();

        if (size <= 100) {
            bubbleSort(transactions);
        } else if (size <= 1000) {
            insertionSort(transactions);
        } else {
            System.out.println("Dataset too large, consider advanced sorting (e.g., Merge Sort)");
        }

        detectOutliers(transactions);
    }

    // ------------------- Main Method -------------------
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        processTransactions(transactions);
    }
}