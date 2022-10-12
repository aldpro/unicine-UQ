package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false,length = 200)
    private String urlFoto;

    @ElementCollection
    private List<String> telefonos;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cliente(Integer cedula, String nombre, String correo, String password, Boolean estado, String urlFoto, List<String> telefonos) {
        super(cedula, nombre, correo, password);
        this.estado = false;
        this.urlFoto = urlFoto;
        this.telefonos = telefonos;
    }
}