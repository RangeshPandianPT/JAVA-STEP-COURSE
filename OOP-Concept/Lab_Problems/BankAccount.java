class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;

    public BankAccount(String accountHolderName, double initialDeposit) {
        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative. Setting to 0.");
            initialDeposit = 0;
        }
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println("Deposited " + amount + " into " + accountNumber);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds in " + accountNumber);
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn " + amount + " from " + accountNumber);
    }

    public double checkBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return String.format("ACC%03d", totalAccounts + 1);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[5];
        int count = 0;

        accounts[count++] = new BankAccount("Alice", 1000);
        accounts[count++] = new BankAccount("Bob", 500);
        accounts[count++] = new BankAccount("Charlie", 2000);

        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].withdraw(2500);

        for (int i = 0; i < count; i++) {
            accounts[i].displayAccountInfo();
        }

        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
    }
}
