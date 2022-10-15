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
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column (nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Teatro teatro;

    @ManyToOne
    @JoinColumn(nullable = false)
    private DistribucionSilla distribucionSilla;

    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    private List<Funcion> funciones;

    @Builder
    public Sala(String nombre, TipoSala tipoSala, Teatro teatro, DistribucionSilla distribucionSilla) {
        this.nombre = nombre;
        this.tipoSala = tipoSala;
        this.teatro = teatro;
        this.distribucionSilla = distribucionSilla;
    }
}