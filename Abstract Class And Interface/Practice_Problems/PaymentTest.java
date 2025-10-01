interface PaymentGateway {
    void pay(double amount);
    void refund(double amount);
}

class CreditCardPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to Credit Card");
    }
}

class UPIPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to UPI");
    }
}

public class PaymentTest {
    public static void main(String[] args) {
        PaymentGateway payment1 = new CreditCardPayment();
        payment1.pay(5000);
        payment1.refund(1500);

        PaymentGateway payment2 = new UPIPayment();
        payment2.pay(2000);
        payment2.refund(500);
    }
}
