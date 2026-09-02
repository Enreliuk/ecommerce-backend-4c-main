package br.edu.unifio.ecommerce.entidades;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Pagamento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valor;

    private LocalDateTime data;

    private String status;

    private String tipo;

    @ManyToOne
    private Pedido pedido;
}