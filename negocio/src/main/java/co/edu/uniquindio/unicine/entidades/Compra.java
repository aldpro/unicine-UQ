package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column (nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Column(nullable = false)
    @Positive
    private Float valorTotal;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<CompraConfiteria> compraConfiterias;

    @OneToOne
    private CuponCliente cuponCliente;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Funcion funcion;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Entrada> entradas;

    @Column(nullable = false)
    private LocalDateTime fechaPelicula;

    @Builder
    public Compra(MedioPago medioPago, CuponCliente cuponCliente, Cliente cliente, Funcion funcion) {
        this.medioPago = medioPago;
        this.fechaCompra = LocalDateTime.now();
        this.cuponCliente = cuponCliente;
        this.cliente = cliente;
        this.funcion = funcion;
        this.entradas = new ArrayList<>();
    }
}