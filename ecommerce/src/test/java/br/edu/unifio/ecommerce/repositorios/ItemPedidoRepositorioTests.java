package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.ItemPedido;

@SpringBootTest
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    public void deveSalvarUmItemPedidoNovo() {
        var pedido = pedidoRepositorio.findById(100001).orElseThrow();
        var produto = produtoRepositorio.findById(1).orElseThrow();

        var itemPedido = new ItemPedido();
        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new java.math.BigDecimal("50.00"));
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        itemPedidoRepositorio.save(itemPedido);

        assertNotNull(itemPedido.getId());
    }
}