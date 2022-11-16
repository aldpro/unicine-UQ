package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
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
public class adminTeatroBean implements Serializable {

    @Getter @Setter
    private AdministradorTeatro administradorTeatro;

    @Getter @Setter
    private List<AdministradorTeatro> administradorTeatros;

    @Setter @Getter
    private List<Funcion> funciones;

    @Setter @Getter
    private List<Horario> horarios;

    @Setter @Getter
    private List<Sala> salas;

    @Setter @Getter
    private List<Teatro> teatros;

    @Setter @Getter
    private List<AdministradorTeatro> administradorTeatrosSeleccionados;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    private boolean editar;

    @PostConstruct
    public void  init(){
        administradorTeatro = new AdministradorTeatro();
        funciones = adminTeatroServicio.listarFuncion();
        horarios = adminTeatroServicio.listarHorarios();
        salas = adminTeatroServicio.listarSalas();
        teatros = adminTeatroServicio.listarTeatros();
        administradorTeatrosSeleccionados = getAdminTeatros();
        editar = false;
    }

    public void crearAdminTeatro(){
        try {
            if (!editar){

                AdministradorTeatro administradorCreado = adminServicio.crearAdminTeatro(administradorTeatro);
                administradorTeatros.add(administradorCreado);

                administradorTeatro = new AdministradorTeatro();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminServicio.actualizarAdminTeatro(administradorTeatro);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }

        } catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

    public void eliminarAdminTeatro(){
        try{
            for (AdministradorTeatro adminT: administradorTeatrosSeleccionados){
                adminServicio.eliminarAdminTeatro(adminT.getCedula());
                administradorTeatros.remove(adminT);
            }
            administradorTeatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(administradorTeatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return administradorTeatrosSeleccionados.size()==1 ? "Borrar 1 elemento" :
                    "Borrar" + administradorTeatrosSeleccionados.size() + "elementos";
        }
    }

    public String getMensajeCrear(){
        return editar? "Editar Administrador Teatro" : "Crear Administrador Teatro";
    }

    public void crearAdminTeatroDialogo(){
        this.administradorTeatro = new AdministradorTeatro();
        editar = false;
    }

    public void seleccionarAdminTeatro(AdministradorTeatro adminTeatroSeleccionado){
        this.administradorTeatro = adminTeatroSeleccionado;
        editar=true;
    }

    public List<AdministradorTeatro> getAdminTeatros(){
       List<AdministradorTeatro> administradorTeatros;
       administradorTeatros = adminServicio.listarAdminsTeatros();
       return administradorTeatros;
    }

}
