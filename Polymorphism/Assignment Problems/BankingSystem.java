abstract class BankAccount {
    String accountNumber;
    String holderName;
    double balance;

    BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println(holderName + " deposited ₹" + amount + " | New Balance: ₹" + balance);
    }

    abstract void processTransaction(double amount);

    double calculateFee(double amount) {
        return amount * 0.01; 
    }

    double calculateFee(double amount, boolean premiumCustomer) {
        return premiumCustomer ? amount * 0.005 : amount * 0.02;
    }

    double calculateFee(double amount, String transactionType) {
        switch (transactionType.toLowerCase()) {
            case "international": return amount * 0.03;
            case "domestic": return amount * 0.01;
            default: return amount * 0.015;
        }
    }
}

class SavingsAccount extends BankAccount {
    double interestRate;
    double withdrawalLimit;

    SavingsAccount(String accountNumber, String holderName, double balance, double interestRate, double withdrawalLimit) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
        this.withdrawalLimit = withdrawalLimit;
    }

    @Override
    void processTransaction(double amount) {
        if (amount > withdrawalLimit) {
            System.out.println("Transaction denied. Withdrawal exceeds limit for " + holderName);
        } else if (amount <= balance) {
            balance -= amount;
            System.out.println(holderName + " withdrew ₹" + amount + " | Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds in Savings Account for " + holderName);
        }
    }

    void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest applied: ₹" + interest + " | New Balance: ₹" + balance);
    }
}

class CheckingAccount extends BankAccount {
    double overdraftLimit;
    double fee;

    CheckingAccount(String accountNumber, String holderName, double balance, double overdraftLimit, double fee) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
        this.fee = fee;
    }

    @Override
    void processTransaction(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= (amount + fee);
            System.out.println(holderName + " paid ₹" + amount + " + Fee ₹" + fee + " | Balance: ₹" + balance);
        } else {
            System.out.println("Overdraft limit exceeded for " + holderName);
        }
    }
}

class InvestmentAccount extends BankAccount {
    String portfolioType;
    String riskLevel;

    InvestmentAccount(String accountNumber, String holderName, double balance, String portfolioType, String riskLevel) {
        super(accountNumber, holderName, balance);
        this.portfolioType = portfolioType;
        this.riskLevel = riskLevel;
    }

    @Override
    void processTransaction(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(holderName + " invested ₹" + amount + " in " + portfolioType + " | Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds for investment by " + holderName);
        }
    }

    void assessRisk() {
        System.out.println("Portfolio Type: " + portfolioType + " | Risk Level: " + riskLevel);
    }
}

class BusinessAccount extends BankAccount {
    double transactionFeeRate;

    BusinessAccount(String accountNumber, String holderName, double balance, double transactionFeeRate) {
        super(accountNumber, holderName, balance);
        this.transactionFeeRate = transactionFeeRate;
    }

    @Override
    void processTransaction(double amount) {
        double fee = amount * transactionFeeRate;
        if (balance >= amount + fee) {
            balance -= (amount + fee);
            System.out.println(holderName + " processed business transaction ₹" + amount + " + Fee ₹" + fee + " | Balance: ₹" + balance);
        } else {
            System.out.println("Insufficient funds in Business Account for " + holderName);
        }
    }

    void bulkTransaction(double[] amounts) {
        double total = 0;
        for (double amt : amounts) total += amt;
        System.out.println(holderName + " requested bulk transaction of ₹" + total);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        BankAccount[] accounts = {
            new SavingsAccount("S1001", "Ravi", 50000, 0.05, 20000),
            new CheckingAccount("C2001", "Priya", 30000, 10000, 200),
            new InvestmentAccount("I3001", "Arjun", 100000, "Stocks", "High"),
            new BusinessAccount("B4001", "Meena Traders", 200000, 0.02)
        };

        for (BankAccount acc : accounts) {
            acc.deposit(5000);
            acc.processTransaction(15000);

            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).applyInterest();
            } else if (acc instanceof InvestmentAccount) {
                ((InvestmentAccount) acc).assessRisk();
            } else if (acc instanceof BusinessAccount) {
                ((BusinessAccount) acc).bulkTransaction(new double[]{5000, 7000, 3000});
            }

            System.out.println("Fee Example (Domestic): ₹" + acc.calculateFee(10000, "domestic"));
        }
    }
}
