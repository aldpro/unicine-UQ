package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DistribucionSilla implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 200)
    private String urlEsquema;

    @Positive
    @Column(nullable = false)
    private Integer total_sillas;

    @Positive
    @Column(nullable = false)
    private Integer filas;

    @Positive
    @Column(nullable = false)
    private Integer columnas;

    @OneToMany(mappedBy = "distribucionSilla")
    private List<Sala> salas;

    @Builder
    public DistribucionSilla(String urlEsquema, Integer total_sillas, Integer filas, Integer columnas) {
        this.urlEsquema = urlEsquema;
        this.total_sillas = total_sillas;
        this.filas = filas;
        this.columnas = columnas;
    }
}
