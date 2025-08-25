package patterns;

public class Main {
    public static void main(String[] args) {
        // Singleton
        AppConfig config = AppConfig.getInstance();
        System.out.println("App: " + config.getAppName());

        // Strategy
        PaymentContext context = new PaymentContext();
        context.setPaymentStrategy(new PixPayment());
        context.executePayment(100);

        context.setPaymentStrategy(new CreditCardPayment());
        context.executePayment(250);

        // Facade
        PedidoFacade pedido = new PedidoFacade();
        pedido.realizarPedido("Notebook Gamer");
    }
}
