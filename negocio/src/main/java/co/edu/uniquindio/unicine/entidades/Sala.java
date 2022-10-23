package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;

    @ManyToOne
    private Teatro teatro;

    @ManyToOne
    private DistribucionSilla distribucionSilla;

    @OneToMany(mappedBy = "sala",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Funcion> funciones;

    @Builder
    public Sala(String nombre, TipoSala tipoSala, Teatro teatro, DistribucionSilla distribucionSilla) {
        this.nombre = nombre;
        this.tipoSala = tipoSala;
        this.teatro = teatro;
        this.distribucionSilla = distribucionSilla;
        this.funciones = new ArrayList<>();
    }

}