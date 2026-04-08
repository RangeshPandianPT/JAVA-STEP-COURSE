import java.util.*;

public class AccountLookup {

    public static int linearFirst(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int linearLast(String[] arr, String target) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int binaryFirst(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                high = mid - 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int binaryLast(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                result = mid;
                low = mid + 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int countOccurrences(String[] arr, String target) {
        int first = binaryFirst(arr, target);
        if (first == -1) return 0;
        int last = binaryLast(arr, target);
        return last - first + 1;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        Arrays.sort(logs);

        String target = "accB";

        int lf = linearFirst(logs, target);
        int ll = linearLast(logs, target);

        int bf = binaryFirst(logs, target);
        int bl = binaryLast(logs, target);
        int count = countOccurrences(logs, target);

        System.out.println(lf);
        System.out.println(ll);
        System.out.println(bf);
        System.out.println(bl);
        System.out.println(count);
    }
}