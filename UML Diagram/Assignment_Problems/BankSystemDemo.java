class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

class Customer {
    private String customerId;
    private String name;
    private Account account;

    public Customer(String customerId, String name, Account account) {
        this.customerId = customerId;
        this.name = name;
        this.account = account;
    }

    public void openAccount(Account account) {
        this.account = account;
    }

    public void viewBalance() {
        System.out.println("Balance: " + account.getBalance());
    }
}

class Bank {
    private String bankName;
    private Customer[] customers;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new Customer[50];
    }

    public void addCustomer(Customer customer) { }

    public void displayCustomers() { }
}

public class BankSystemDemo {
    public static void main(String[] args) {
        Account acc1 = new Account("A101", 5000);
        Customer cust1 = new Customer("C01", "Ramesh", acc1);
        Bank bank = new Bank("ABC Bank");
        bank.addCustomer(cust1);
        cust1.viewBalance();
    }
}
