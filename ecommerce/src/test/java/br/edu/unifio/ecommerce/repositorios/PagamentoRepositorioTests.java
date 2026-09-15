package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PagamentoRepositorioTests {

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Test
    public void deveSalvarPagamentoNovo() {

        Pedido pedido = pedidoRepositorio.findById(100001).orElse(null);

        assertNotNull(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("571.62"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("CARTAO_CREDITO");
        pagamento.setPedido(pedido);

        Pagamento pagamentoSalvo = pagamentoRepositorio.save(pagamento);

        assertNotNull(pagamentoSalvo.getId());

        Pagamento pagamentoEncontrado = pagamentoRepositorio
                .findById(pagamentoSalvo.getId())
                .orElse(null);

        assertNotNull(pagamentoEncontrado);
        assertEquals(new BigDecimal("571.62"), pagamentoEncontrado.getValor());
        assertEquals("APROVADO", pagamentoEncontrado.getStatus());
        assertEquals("CARTAO_CREDITO", pagamentoEncontrado.getTipo());
        assertNotNull(pagamentoEncontrado.getData());
        assertNotNull(pagamentoEncontrado.getPedido());
        assertEquals(100001, pagamentoEncontrado.getPedido().getId());
    }
}