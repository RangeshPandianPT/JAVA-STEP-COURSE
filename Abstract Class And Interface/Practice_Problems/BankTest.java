abstract class BankAccount {
    protected double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public abstract void calculateInterest();

    public void displayBalance() {
        System.out.println("Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * 0.04;
        System.out.println("Savings Account Interest: " + interest);
    }
}

class CurrentAccount extends BankAccount {
    public CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * 0.02;
        System.out.println("Current Account Interest: " + interest);
    }
}

public class BankTest {
    public static void main(String[] args) {
        BankAccount acc1 = new SavingsAccount(10000);
        acc1.displayBalance();
        acc1.calculateInterest();

        BankAccount acc2 = new CurrentAccount(15000);
        acc2.displayBalance();
        acc2.calculateInterest();
    }
}
