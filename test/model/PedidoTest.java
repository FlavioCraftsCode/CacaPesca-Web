package model;

import java.math.BigDecimal;

public class PedidoTest {
    public static void main(String[] args) {
        System.out.println("----- INICIANDO TESTE DE UNIDADE: PEDIDO -----");

        // 1. Cenário de Teste: Cliente compra 3 itens de R$ 50.00
        BigDecimal valorUnitario = new BigDecimal("50.00");
        Pedido pedido = new Pedido("Flavio", "Vara de Pesca", 3, valorUnitario);

        // 2. Execução
        BigDecimal totalEsperado = new BigDecimal("150.00");
        BigDecimal totalObtido = pedido.getValorTotal();

        // 3. Verificação (O que o JUnit faria internamente)
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Produto: " + pedido.getProduto());
        System.out.println("Valor Esperado: R$ " + totalEsperado);
        System.out.println("Valor Obtido:   R$ " + totalObtido);

        if (totalEsperado.compareTo(totalObtido) == 0) {
            System.out.println("\n✅ TESTE PASSOU: O cálculo do valor total está correto.");
        } else {
            System.out.println("\n❌ TESTE FALHOU: O cálculo divergiu do esperado.");
        }
        
        System.out.println("----------------------------------------------");
    }
}