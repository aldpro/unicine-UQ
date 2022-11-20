package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private String hora;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private List<Dias> dias;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Funcion> funciones;

    @Builder
    public Horario(LocalDate fechaInicio, LocalDate fechaFin, String hora, List<Dias> dias) {
        this.fechaInicio = fechaInicio;
        this.hora = hora;
        this.fechaFin = fechaFin;
        this.funciones =  new ArrayList<>();
        this.dias = dias;
    }

}