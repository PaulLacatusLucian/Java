package Domain;

public class MasterCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " RON using MasterCard.");
    }
}
