class ATMSystem {
    void withdrawMoney(User user, double amount) {
        user.performWithdrawal(amount);
    }

    void checkBalance(User user) {
        user.viewBalance();
    }

    void depositMoney(User user, double amount) {
        user.performDeposit(amount);
    }
}

class User {
    String cardNumber;
    double balance;

    User(String cardNumber, double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    void performWithdrawal(double amount) {
        if (balance >= amount) balance -= amount;
    }

    void performDeposit(double amount) {
        balance += amount;
    }

    void viewBalance() { }
}

public class UseCaseDiagramDemo {
    public static void main(String[] args) {
        User user = new User("1234-5678-9012", 5000);
        ATMSystem atm = new ATMSystem();

        atm.withdrawMoney(user, 1000);
        atm.checkBalance(user);
        atm.depositMoney(user, 2000);
    }
}
