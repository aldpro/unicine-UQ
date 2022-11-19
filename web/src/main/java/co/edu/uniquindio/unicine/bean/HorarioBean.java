package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
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
public class HorarioBean implements Serializable {

    @Getter @Setter
    private Horario horario;

    @Setter @Getter
    private List<Horario> horarios;

    @Setter @Getter
    private List<Dias> dias;

    @Setter @Getter
    private List<Horario> horariosSeleccionados;

    private boolean editar;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @PostConstruct
    public void inicilizar(){
        horario = new Horario();
        dias = Arrays.asList(Dias.values());
        horarios = adminTeatroServicio.listarHorarios();
        horariosSeleccionados = new ArrayList<>();
        editar = false;
    }

    public void crearHorario(){

        try {
            if(!editar) {

                Horario registro = adminTeatroServicio.crearHorario(horario);
                horarios.add(registro);

                horario = new Horario();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Horario creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminTeatroServicio.actualizarHorario(horario);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Horario actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);

        }
    }

    public void eliminarHorario(){
        try{
            for(Horario h : horariosSeleccionados) {
                adminTeatroServicio.eliminarHorario(h.getCodigo());
                horarios.remove(h);
            }
            horariosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(horariosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return horariosSeleccionados.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " +  horariosSeleccionados.size() +  " elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar horario" : "Crear horario";
    }

    public void seleccionarHorario(Horario horarioSeleccionado){
        this.horario = horarioSeleccionado;
        editar = true;
    }

    public void crearHorarioDialogo(){
        this.horario = new Horario();
        editar=false;
    }
}
