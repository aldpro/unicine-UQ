package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {

    @Column(nullable = false)
    private Boolean estado = false;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;

    @ElementCollection
    private List<String> telefonos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CuponCliente> cuponClientes;

    @Builder
    public Cliente(Integer cedula, String nombre, String correo, String password, Boolean estado, List<String> telefonos) {
        super(cedula, nombre, correo, password);
        this.estado = false;
        this.telefonos = telefonos;
        this.compras = new ArrayList<>();
        this.cuponClientes = new ArrayList<>();
    }

    public String getImagenPrincipal(){
        if (!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";
    }
}