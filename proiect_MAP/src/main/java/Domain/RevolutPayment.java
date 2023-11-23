package Domain;

public class RevolutPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " RON using Revolut.");
    }
}
