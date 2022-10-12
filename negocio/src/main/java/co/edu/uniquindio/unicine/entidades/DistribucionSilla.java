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
@ToString
public class DistribucionSilla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 200)
    private String urlEsquema;

    @Column(nullable = false)
    @Positive
    private Integer total_sillas;

    @Column(nullable = false)
    @Positive
    private Integer filas;

    @Column(nullable = false)
    @Positive
    private Integer columnas;

    @OneToMany(mappedBy = "distribucionSilla")
    @ToString.Exclude
    private List<Sala> salas;

    @Builder
    public DistribucionSilla(String urlEsquema, Integer total_sillas, Integer filas, Integer columnas) {
        this.urlEsquema = urlEsquema;
        this.total_sillas = total_sillas;
        this.filas = filas;
        this.columnas = columnas;
    }
}
