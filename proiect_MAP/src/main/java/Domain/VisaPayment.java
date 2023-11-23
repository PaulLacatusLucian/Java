package Domain;

public class VisaPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid " + amount + " RON using Visa.");
    }
}
