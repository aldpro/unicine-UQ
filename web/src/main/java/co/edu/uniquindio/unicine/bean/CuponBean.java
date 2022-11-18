package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class CuponBean implements Serializable {

    @Getter
    @Setter
    private Cupon cupon;

    @Setter
    @Getter
    private List<Cupon> cupones;

    @Getter @Setter
    private List<Cupon> cuponesSeleccionados;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    private boolean editar;

    @PostConstruct
    public void inicializar() {
        cupon = new Cupon();
        cuponesSeleccionados = new ArrayList<>();
        cupones = new ArrayList<>();
        editar = false;
    }

    public void crearCupon() {
        try {
            System.out.println("try");
            if (!editar) {

                System.out.println("1");
                Cupon creado = adminServicio.crearCupon(cupon);
                cupones.add(creado);

                cupon = new Cupon();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon creada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

            } else {
                System.out.println("2");
                adminServicio.actualizarCupon(cupon);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Cupon actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        } catch (Exception e) {
            System.out.println("3");
           FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);

        }

    }

    public void eliminarCupon() {
        try {
            for (Cupon c : cuponesSeleccionados) {
                adminServicio.eliminarCupon(c.getCodigo());
                cupones.remove(c);
            }
            cuponesSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar() {
        if (cuponesSeleccionados.isEmpty()) {
            return "Borrar";
        } else {
            return cuponesSeleccionados.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " + cuponesSeleccionados.size() + " elementos";
        }
    }

    public String getMensajeCrear() {
        return editar ? "Editar Cupon" : "Crear cupon";
    }

    public void seleccionarCupon(Cupon cuponSeleccionado) {
        this.cupon = cuponSeleccionado;
        editar = true;
    }

    public void crearCuponDialogo() {
        this.cupon = new Cupon();
        editar = false;
    }

    public List<Cupon> getCupones(){
        List<Cupon> cupones;
        cupones = adminServicio.listaCupones();
        return  cupones;
    }

}
