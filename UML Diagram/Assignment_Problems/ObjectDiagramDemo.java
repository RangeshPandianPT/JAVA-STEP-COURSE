class Account {
    String accountNumber;
    double balance;

    Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

class Customer {
    String customerId;
    String name;
    Account account;

    Customer(String customerId, String name, Account account) {
        this.customerId = customerId;
        this.name = name;
        this.account = account;
    }
}

class Bank {
    String bankName;
    Customer[] customers;

    Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new Customer[5];
    }
}

public class ObjectDiagramDemo {
    public static void main(String[] args) {
        Account acc1 = new Account("A101", 5000);
        Account acc2 = new Account("A102", 3000);

        Customer cust1 = new Customer("C01", "Ramesh", acc1);
        Customer cust2 = new Customer("C02", "Priya", acc2);

        Bank bank = new Bank("ABC Bank");
        bank.customers[0] = cust1;
        bank.customers[1] = cust2;
    }
}
