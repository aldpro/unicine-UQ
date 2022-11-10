package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EstadoPelicula estado;


    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private GeneroPelicula generoPelicula;

    @Column(nullable = false, length = 80)
    private String nombre;

    //@Column(nullable = false)
    @Lob
    private String reparto;

    @Column(nullable = false)
    @Lob
    private String sinopsis;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @Column(nullable = false, length = 200)
    private String urlTrailer;

    @Column(nullable = false, precision = 1, scale = 2)
    @Max(5)
    @Positive
    private Float puntuacion;

    @OneToMany(mappedBy = "pelicula", cascade =  CascadeType.ALL)
    @ToString.Exclude
    private List<Funcion> funciones;

    public Pelicula(EstadoPelicula estado, GeneroPelicula generoPelicula, String nombre, String reparto, String sinopsis, String urlTrailer, Float puntuacion) {
        this.estado = estado;
        this.generoPelicula = generoPelicula;
        this.nombre = nombre;
        this.reparto = reparto;
        this.sinopsis = sinopsis;
        this.urlTrailer = urlTrailer;
        this.puntuacion = puntuacion;
        this.funciones =  new ArrayList<>();
    }
    public String obtenerImagenPrincipal(){
        if (!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";
    }
}