package model;
import java.math.BigDecimal;

public class Pedido {
    private int id;
    private String cliente;
    private String produto;
    private int quantidade;
    private BigDecimal valor;

    // Construtor para novos pedidos (sem ID)
    public Pedido(String cliente, String produto, int quantidade, BigDecimal valor) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCliente() { return cliente; }
    public String getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getValor() { return valor; }
    
    // Adicione isso ao final da sua classe Pedido.java, antes do último }
public BigDecimal getValorTotal() {
    if (this.valor == null) return BigDecimal.ZERO;
    return this.valor.multiply(new BigDecimal(this.quantidade));
}
}