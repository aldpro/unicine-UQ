package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false, length = 10)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Positive
    @Column(nullable = false)
    private Float valorTotal;

    @ManyToMany
    private List<Confiteria> confiteria;

    @OneToOne
    private Cupon cupon;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Funcion funcion;

    @OneToMany(mappedBy = "compra")
    private List<Entrada> entradas;

    @Builder
    public Compra(MedioPago medioPago, List<Confiteria> confiteria, Cupon cupon, Cliente cliente, Funcion funcion) {
        this.medioPago = medioPago;
        this.fechaCompra = LocalDateTime.now();
        this.confiteria = confiteria;
        this.cupon = cupon;
        this.cliente = cliente;
        this.funcion = funcion;
    }
}