package patterns;

public class PedidoFacade {
    private EstoqueService estoque = new EstoqueService();
    private PagamentoService pagamento = new PagamentoService();
    private EnvioService envio = new EnvioService();

    public void realizarPedido(String produto) {
        estoque.verificarEstoque(produto);
        pagamento.processarPagamento();
        envio.enviarProduto(produto);
    }
}
