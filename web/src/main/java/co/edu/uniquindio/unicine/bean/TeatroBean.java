package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
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
import java.util.List;

@Component
@ViewScoped
public class TeatroBean implements Serializable {

    @Getter @Setter
    private Teatro teatro;

    @Setter @Getter
    private List<Teatro> teatros;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Setter @Getter
    private List<Teatro> teatrosSeleccionados;

    private boolean editar;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;


    @PostConstruct
    public void inicilizar(){
        teatro = new Teatro();
        ciudades = adminServicio.listarCiudades();
        teatros = adminTeatroServicio.listarTeatros(); //DEBE SER DADO EL CODIGO DE LA CIUDAD
        teatrosSeleccionados = new ArrayList<>();
        editar = false;
    }

    public void crearTeatro(){
        try {
            if(!editar) {

                //Esto se borra cuando se implemente la sesion
                AdministradorTeatro administradorTeatro = adminServicio.obtenerAdminTeatro(1119000000);
                teatro.setAdministradorTeatro(administradorTeatro);

                Teatro registro = adminTeatroServicio.crearTeatro(teatro);
                teatros.add(registro);

                teatro = new Teatro();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminTeatroServicio.actualizarTeatro(teatro);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);

        }
    }

    public void eliminarTeatro(){
        try{
            for(Teatro t : teatrosSeleccionados) {
                adminTeatroServicio.eliminarTeatro(t.getCodigo());
                teatros.remove(t);
            }
            teatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(teatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return teatrosSeleccionados.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " +  teatrosSeleccionados.size() +  " elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar teatro" : "Crear teatro";
    }

    public void seleccionarTeatro(Teatro teatroSeleccionado){
        this.teatro = teatroSeleccionado;
        editar = true;
    }

    public void crearTeatroDialogo(){
        this.teatro = new Teatro();
        editar=false;
    }

}
