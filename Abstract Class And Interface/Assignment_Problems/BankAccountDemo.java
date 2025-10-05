abstract class BankAccount {
    double balance;
    
    BankAccount(double balance) {
        this.balance = balance;
    }
    
    abstract void calculateInterest();
    
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {
    SavingsAccount(double balance) { super(balance); }
    void calculateInterest() {
        double interest = balance * 0.05;
        System.out.println("Savings Account Interest: " + interest);
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(double balance) { super(balance); }
    void calculateInterest() {
        double interest = balance * 0.02;
        System.out.println("Current Account Interest: " + interest);
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount sa = new SavingsAccount(10000);
        sa.deposit(2000);
        sa.calculateInterest();
        
        BankAccount ca = new CurrentAccount(15000);
        ca.deposit(3000);
        ca.calculateInterest();
    }
}
