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
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {

    @OneToMany(mappedBy = "administradorTeatro", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Teatro> teatros;

    @Builder
    public AdministradorTeatro(Integer cedula, String nombre, String correo, String password) {
        super(cedula, nombre, correo, password);
        this.teatros = new ArrayList<>();
    }
}
