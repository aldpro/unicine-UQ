package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    @Max(100)
    private Float descuento;

    @Column(nullable = false, length = 100)
    private String criterio;

    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    @OneToMany(mappedBy = "cupon", cascade =  CascadeType.ALL)
    @ToString.Exclude
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cupon(String descripcion, Float descuento, String criterio, LocalDateTime fechaVencimiento) {
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.criterio = criterio;
        this.fechaVencimiento = fechaVencimiento;
        this.cuponClientes =  new ArrayList<>();
    }
}