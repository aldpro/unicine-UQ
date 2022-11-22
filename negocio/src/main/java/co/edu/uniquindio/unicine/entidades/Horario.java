package co.edu.uniquindio.unicine.entidades;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private String hora;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Dias> dias;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Funcion> funciones;


    @Builder
    public Horario(LocalDate fechaInicio, LocalDate fechaFin, String hora) {
        this.fechaInicio = fechaInicio;
        this.hora = hora;
        this.fechaFin = fechaFin;
    }

}