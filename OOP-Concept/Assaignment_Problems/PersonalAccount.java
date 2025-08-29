import java.util.*;

class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";

    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + description + " | +" + amount);
        } else {
            System.out.println("Invalid income amount!");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent: " + description + " | -" + amount);
        } else {
            System.out.println("Invalid expense or insufficient balance!");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("Bank Name       : " + bankName);
        System.out.println("Account Holder  : " + accountHolderName);
        System.out.println("Account Number  : " + accountNumber);
        System.out.println("Total Income    : " + totalIncome);
        System.out.println("Total Expenses  : " + totalExpenses);
        System.out.println("Current Balance : " + currentBalance);
        System.out.println("Total Savings   : " + calculateSavings());
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "ACC" + (1000 + totalAccounts + 1);
    }
}

public class PersonalFinanceManager {
    public static void main(String[] args) {
        PersonalAccount.setBankName("Future Bank of India");

        PersonalAccount acc1 = new PersonalAccount("Alice");
        PersonalAccount acc2 = new PersonalAccount("Bob");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1500, "Groceries");
        acc1.addExpense(1000, "Electricity Bill");

        acc2.addIncome(10000, "Freelance Project");
        acc2.addExpense(2000, "Rent");

        acc3.addIncome(7000, "Part-time Job");
        acc3.addExpense(2500, "Shopping");
        acc3.addIncome(3000, "Bonus");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("Bank Name (shared by all accounts): " + "Future Bank of India");
        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());
    }
}
