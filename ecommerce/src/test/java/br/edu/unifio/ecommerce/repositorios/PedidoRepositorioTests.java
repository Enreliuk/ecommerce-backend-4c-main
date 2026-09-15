package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PedidoRepositorioTests {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    void deveSalvarPedidoNovo() {

        Cliente cliente = clienteRepositorio.findById(234532)
                .orElseThrow();

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);

        Pedido resultado = pedidoRepositorio.save(pedido);

        assertNotNull(resultado.getId());
    }
}