package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Funcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false)
    @Positive
    private Float precio;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Horario horario;

    @ManyToOne
    private Pelicula pelicula;

    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Compra> compras;

    @Builder
    public Funcion(Float precio, Sala sala, Horario horario, Pelicula pelicula) {
        this.precio = precio;
        this.sala = sala;
        this.horario = horario;
        this.pelicula = pelicula;
        this.compras = new ArrayList<>();
    }
}