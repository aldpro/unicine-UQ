package co.edu.uniquindio.unicine.entidades;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {
    @Builder
    public Administrador(String nombre, String correo, String password) {
        super(nombre, correo, password);
    }

}
