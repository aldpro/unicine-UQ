package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    private Float precio;

    @Column(nullable = false)
    private Integer fila;

    @Column(nullable = false)
    private Integer columna;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @Builder
    public Entrada(Float precio, Integer fila, Integer columna, Compra compra) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
        this.compra = compra;
    }
}