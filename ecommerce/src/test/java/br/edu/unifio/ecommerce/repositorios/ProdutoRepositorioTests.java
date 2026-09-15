package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import java.util.List;

import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ProdutoRepositorioTests {
    @Autowired 
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired 
    private ProdutoRepositorio produtoRepositorio;

    @Test  
    public void deveSalvarUmProdutoNovo () {
        var produto = new Produto ();
        produto.setNome("Notebook Lenovo Legion 5i");
        produto.setDescricao("Processador I7, Armazenamento SSD 1TB, Memória 16BG");
        produto.setPreco(new BigDecimal("12570.30"));
        produto.setEstoque(Short.parseShort("10"));
        
        var categoria = categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow();
        produto.setCategoria(categoria);

        System.out.println("ID Antes: " + produto.getId());
        produtoRepositorio.save(produto);
        System.out.println("ID Depois: " + produto.getId());

        assertNotNull(produto.getId());
        assertEquals(6, produto.getId());
    }

    @Test
    public void deveBuscarUmProdutoPorId () {
        Produto produto = produtoRepositorio.findById(Integer.parseInt("3")).orElseThrow();

        assertNotNull(produto);
        assertEquals("JBL Partybox Club 120", produto.getNome());
    }

    @Test
public void deveBuscarTodosOsProdutos () {
	List<Produto> produto = produtoRepositorio.findAll(Sort.by("nome"));

    assertEquals(6, produto.size());
    assertEquals("Código Limpo", produto.get(0).getNome());
    assertEquals("Razer Basilisk V3", produto.get(5).getNome());
    }

    @Test
    public void deveExcluirUmProdutoPorId () {
        var produto = new Produto ();
        produto.setNome("Nome Teste");
        produto.setDescricao("Descrição Teste");
        produto.setPreco(new BigDecimal("67.67"));
        produto.setEstoque(Short.parseShort("67"));
        
        var categoria = categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow();
        produto.setCategoria(categoria);

        produtoRepositorio.save(produto);
        produtoRepositorio.deleteById(produto.getId());
    }
}