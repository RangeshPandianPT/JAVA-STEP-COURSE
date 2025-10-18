class BankAccount {
    private String accountNumber;
    private double balance;
    private int pin;

    public BankAccount(String accountNumber, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public boolean validatePin(int enteredPin) {
        return enteredPin == pin;
    }

    public void debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }
}

class ATM {
    private BankAccount linkedAccount;

    public ATM(BankAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public void withdraw(int enteredPin, double amount) {
        System.out.println("ATM: Validating PIN...");
        if (linkedAccount.validatePin(enteredPin)) {
            linkedAccount.debit(amount);
            System.out.println("ATM: Cash dispensed successfully.");
        } else {
            System.out.println("Invalid PIN. Transaction failed.");
        }
    }
}

class Customer {
    private String name;
    private ATM atm;

    public Customer(String name, ATM atm) {
        this.name = name;
        this.atm = atm;
    }

    public void performWithdrawal(int pin, double amount) {
        System.out.println(name + " is requesting withdrawal of ₹" + amount + "...");
        atm.withdraw(pin, amount);
        System.out.println(name + " received confirmation.\n");
    }
}

public class ATMDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("ACC12345", 20000, 1234);
        ATM atm = new ATM(account);
        Customer customer = new Customer("Rahul", atm);

        customer.performWithdrawal(1234, 5000);   
        customer.performWithdrawal(9999, 2000);   
    }
}
