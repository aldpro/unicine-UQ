package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CuponCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cupon cupon;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @OneToOne(mappedBy = "cuponCliente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Compra compra;

    @Builder
    public CuponCliente(Boolean estado, Cupon cupon, Cliente cliente) {
        this.estado = estado;
        this.cupon = cupon;
        this.cliente = cliente;
    }
}
