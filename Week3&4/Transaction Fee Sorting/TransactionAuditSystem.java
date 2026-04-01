public class TransactionAuditSystem {

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        if (size <= 100) {
            BubbleSortTransactions.sortByFee(transactions);
        } else {
            InsertionSortTransactions.sortByFeeAndTimestamp(transactions);
        }

        OutlierDetector.detectHighFees(transactions);
    }
}