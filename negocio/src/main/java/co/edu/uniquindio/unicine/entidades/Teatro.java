package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Teatro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToOne
    private AdministradorTeatro administradorTeatro;

    @OneToMany(mappedBy = "teatro", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Sala> salas;
    
    @Builder
    public Teatro(String direccion, String telefono, Ciudad ciudad, AdministradorTeatro administradorTeatro) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.administradorTeatro = administradorTeatro;
        this.salas = new ArrayList<>();
    }

}