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
@ToString
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Double descuento;

    @Column(nullable = false, length = 100)
    private String criterio;

    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    @Column(nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "cupon")
    @ToString.Exclude
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cupon(String descripcion, Double descuento, String criterio, LocalDateTime fechaVencimiento, Boolean estado) {
        this.descripcion = descripcion;
        this.descuento = descuento/100;
        this.criterio = criterio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }
}