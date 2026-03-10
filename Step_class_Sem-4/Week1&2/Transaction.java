import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;
    String account;
    String time;

    Transaction(int id, int amount, String merchant, String account, String time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.account = account;
        this.time = time;
    }
}

public class FinancialTwoSum {

    public static List<int[]> findTwoSum(List<Transaction> transactions, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                result.add(new int[]{map.get(complement), t.id});
            }

            map.put(t.amount, t.id);
        }

        return result;
    }

    public static List<String> detectDuplicates(List<Transaction> transactions) {

        Map<String, List<Transaction>> map = new HashMap<>();
        List<String> duplicates = new ArrayList<>();

        for (Transaction t : transactions) {

            String key = t.amount + "-" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());

            for (Transaction existing : map.get(key)) {

                if (!existing.account.equals(t.account)) {
                    duplicates.add("Duplicate amount " + t.amount + " at " + t.merchant +
                                   " accounts: " + existing.account + " & " + t.account);
                }
            }

            map.get(key).add(t);
        }

        return duplicates;
    }

    public static List<List<Integer>> findKSum(int[] nums, int target, int k) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, target, k, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int target, int k, int start,
                                  List<Integer> current, List<List<Integer>> result) {

        if (k == 0 && target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (k == 0 || target < 0) {
            return;
        }

        for (int i = start; i < nums.length; i++) {

            current.add(nums[i]);

            backtrack(nums, target - nums[i], k - 1, i + 1, current, result);

            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1, 500, "StoreA", "acc1", "10:00"));
        transactions.add(new Transaction(2, 300, "StoreB", "acc2", "10:15"));
        transactions.add(new Transaction(3, 200, "StoreC", "acc3", "10:30"));
        transactions.add(new Transaction(4, 500, "StoreA", "acc4", "11:00"));

        List<int[]> pairs = findTwoSum(transactions, 500);

        for (int[] p : pairs) {
            System.out.println("Pair: " + p[0] + " , " + p[1]);
        }

        List<String> duplicates = detectDuplicates(transactions);

        for (String d : duplicates) {
            System.out.println(d);
        }

        int[] nums = {500, 300, 200};

        List<List<Integer>> ksum = findKSum(nums, 1000, 3);

        for (List<Integer> list : ksum) {
            System.out.println(list);
        }
    }
}