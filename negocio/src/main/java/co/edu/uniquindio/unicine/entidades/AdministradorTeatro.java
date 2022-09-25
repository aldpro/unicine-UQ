package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {

    @OneToMany
    private List<Teatro> teatros;

    @Builder
    public AdministradorTeatro(String nombre, String correo, String password, List<Teatro> teatros) {
        super(nombre, correo, password);
        this.teatros = teatros;
    }

}
