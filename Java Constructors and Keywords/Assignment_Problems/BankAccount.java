import java.util.Random;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    BankAccount() {
        this("Unknown", new Random().nextInt(900000) + 100000, 0.0);
    }

    BankAccount(String accountHolder) {
        this(accountHolder, new Random().nextInt(900000) + 100000, 0.0);
    }

    BankAccount(String accountHolder, double initialBalance) {
        this(accountHolder, new Random().nextInt(900000) + 100000, initialBalance);
    }

    BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: Rs." + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: Rs." + amount);
        } else {
            System.out.println("Insufficient balance or invalid withdrawal.");
        }
    }

    void displayAccount() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: Rs." + balance);
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount("Alice");
        BankAccount acc3 = new BankAccount("Bob", 5000.0);

        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();

        acc2.deposit(2000);
        acc2.withdraw(500);
        acc2.displayAccount();

        acc3.withdraw(10000);
        acc3.deposit(3000);
        acc3.displayAccount();
    }
}
