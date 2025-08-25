package patterns;

public class PixPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Pagando R$" + amount + " via PIX");
    }
}
