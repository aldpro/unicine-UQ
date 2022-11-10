package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
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

    @ElementCollection
    @Column(nullable = false,length = 200)
    private Map<String, String> imagenes;

    @OneToMany(mappedBy = "confiteria", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CompraConfiteria> compraConfiterias;

    @Builder
    public Confiteria(String nombre, Float precio, String urlImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.compraConfiterias =  new ArrayList<>();
    }

    public String getImagenPrincipal(){
        if (!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";
    }
}