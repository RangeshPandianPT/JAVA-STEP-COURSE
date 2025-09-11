package bank;

public class SecureBankAccount {

    // ================== PRIVATE FIELDS ==================
    private String accountNumber;     // Read-only after creation
    private double balance;           // Controlled modification
    private int pin;                  // Write-only
    private boolean isLocked;         // Security state
    private int failedAttempts;       // Security counter

    // ================== CONSTANTS ==================
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // ================== CONSTRUCTOR ==================
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE);
        this.pin = 0; // Default PIN (must be set later)
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // ================== ACCOUNT INFO METHODS ==================
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot view balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // ================== SECURITY METHODS ==================
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
            return true;
        }
        System.out.println("Incorrect old PIN.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked. Access denied.");
            return false;
        }
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            System.out.println("Invalid PIN.");
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked successfully.");
            return true;
        }
        System.out.println("Incorrect PIN. Cannot unlock account.");
        return false;
    }

    // ================== TRANSACTION METHODS ==================
    public boolean deposit(double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
                return true;
            } else {
                System.out.println("Deposit must be positive.");
            }
        }
        return false;
    }

    public boolean withdraw(double amount, int enteredPin) {
        if (validatePin(enteredPin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount);
                return true;
            } else {
                System.out.println("Insufficient funds or invalid amount.");
            }
        }
        return false;
    }

    public boolean transfer(SecureBankAccount target, double amount, int enteredPin) {
        if (this.withdraw(amount, enteredPin)) {
            target.balance += amount; // Direct access allowed internally
            System.out.println("Transferred: " + amount + " to " + target.getAccountNumber());
            return true;
        }
        return false;
    }

    // ================== PRIVATE HELPERS ==================
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to multiple failed attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    // ================== MAIN (DEMO) ==================
    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 1000);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 500);

        // Try accessing private fields (should fail)
        // System.out.println(acc1.balance); // ❌ Compile error

        // Set PINs
        acc1.setPin(0, 1234);
        acc2.setPin(0, 4321);

        // Transactions
        acc1.deposit(200, 1234);
        acc1.withdraw(150, 1234);
        acc1.transfer(acc2, 300, 1234);

        // Security test - Wrong PIN attempts
        acc1.withdraw(100, 1111);
        acc1.withdraw(100, 2222);
        acc1.withdraw(100, 3333); // Should lock account

        // Trying to operate on locked account
        acc1.deposit(100, 1234); // Fail

        // Unlock account
        acc1.unlockAccount(1234);
        acc1.deposit(500, 1234);

        // Over-withdraw attempt
        acc2.withdraw(2000, 4321);
    }
}
