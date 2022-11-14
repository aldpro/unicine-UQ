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
public class SalaBean implements Serializable {

    @Getter @Setter
    private Sala sala;

    @Setter @Getter
    private List<Sala> salas;

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Teatro> teatros;

    @Setter @Getter
    private DistribucionSilla distribucionSilla;

    @Setter @Getter
    private List<DistribucionSilla> distribucionSillas;

    @Setter @Getter
    private List<Sala> salasSeleccionadas;

    private boolean editar;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @PostConstruct
    public void inicilizar(){
        sala = new Sala();
        teatros = adminTeatroServicio.listarTeatros();
        distribucionSillas = adminTeatroServicio.listarDistribucionSillas();
        salas = adminTeatroServicio.listarSalas();
        salasSeleccionadas = new ArrayList<>();
        editar = false;
    }

    public void crearSala(){

        try {
            if(!editar) {

                Sala registro = adminTeatroServicio.crearSala(sala);
                salas.add(registro);

                sala = new Sala();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Sala creada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else {
                adminTeatroServicio.actualizarSala(sala);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Sala actualizada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);

        }
    }

    public void eliminarSala(){
        try{
            for(Sala s : salasSeleccionadas) {
                adminTeatroServicio.eliminarSala(s.getCodigo());
                salas.remove(s);
            }
            salasSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(salasSeleccionadas.isEmpty()){
            return "Borrar";
        }else{
            return salasSeleccionadas.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " +  salasSeleccionadas.size() +  " elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar sala" : "Crear sala";
    }

    public void seleccionarSala(Sala salaSeleccionada){
        this.sala = salaSeleccionada;
        editar = true;
    }

    public void crearSalaDialogo(){
        this.sala = new Sala();
        editar=false;
    }
}
