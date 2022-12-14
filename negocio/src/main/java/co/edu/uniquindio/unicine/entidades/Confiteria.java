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
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false,length = 200)
    private String urlImagen;

    @OneToMany(mappedBy = "confiteria", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CompraConfiteria> compraConfiterias;

    @Builder
    public Confiteria(String nombre, Float precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.compraConfiterias =  new ArrayList<>();
    }
}