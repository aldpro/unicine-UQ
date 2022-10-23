package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer cedula;

    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull
    @Email
    @Column(nullable = false, unique = true, length = 200)
    private String correo;

    @Column(nullable = false,length = 100)
    private String password;

    public Persona(Integer cedula, String nombre, String correo, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }
}
