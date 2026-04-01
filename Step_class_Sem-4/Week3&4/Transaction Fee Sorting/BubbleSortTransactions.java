import java.util.Collections;
import java.util.List;

class BubbleSortTransactions {

    public static void sortByFee(List<Transaction> list) {
        int n = list.size();
        boolean swapped;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // Early termination
        }

        System.out.println("BubbleSort Result: " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }
}