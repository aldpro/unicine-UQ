package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 10)
    private String estado;

    @ManyToMany
    private List<Genero> generos;

    @Column(nullable = false, length = 80)
    private String nombre;

    @ElementCollection
    private List<String> repartos;

    @Column(nullable = false)
    @Lob
    private String sinopsis;

    @Column(nullable = false, length = 200)
    private String urlImagen;

    @Column(nullable = false, length = 200)
    private String urlTrailer;

    @Column(nullable = false, precision = 1, scale = 2)
    @Max(5)
    @Positive
    private float puntuacion;

    @OneToMany(mappedBy = "pelicula")
    @ToString.Exclude
    private List<Funcion> funciones;

    @Builder
    public Pelicula(String estado, List<Genero> genero, String nombre, List<String> reparto, String sinopsis, String urlImagen, String urlTrailer) {
        this.estado = estado;
        this.generos = genero;
        this.nombre = nombre;
        this.repartos = reparto;
        this.sinopsis = sinopsis;
        this.urlImagen = urlImagen;
        this.urlTrailer = urlTrailer;
    }
}