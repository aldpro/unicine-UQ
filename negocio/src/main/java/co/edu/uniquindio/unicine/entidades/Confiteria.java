package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false,length = 200)
    private String urlImagen;

    @ManyToMany(mappedBy = "confiteria")
    private List<Compra> compras;

    @Builder
    public Confiteria(String nombre, Float precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }
}