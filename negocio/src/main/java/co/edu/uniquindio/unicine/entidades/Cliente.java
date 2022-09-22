package co.edu.uniquindio.unicine.entidades;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false,length = 200)
    private String urlFoto;

    @ElementCollection
    private List<String> telefonos;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Cupon> cupones;

    public Cliente(String nombre, String correo, String password, String urlFoto, List<String> telefonos) {
        super(nombre, correo, password);
        this.urlFoto = urlFoto;
        this.telefonos = telefonos;
        this.estado = false;
    }
}
