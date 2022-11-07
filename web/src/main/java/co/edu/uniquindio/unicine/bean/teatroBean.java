package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class teatroBean  implements Serializable {

    @Getter @Setter
    private Teatro teatro;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    public void inicializarTeatro(){
        teatro= new Teatro();
        ciudades = adminServicio.listarCiudades();
    }

    @Getter @Setter
    private List<Ciudad> ciudades;

    public void crearTeatro(){
        try {

            //Esto se borra cuando se implemente la sesion
            AdministradorTeatro administradorTeatro = adminServicio.obtenerAdminTeatro(1228000000);
            teatro.setAdministradorTeatro(administradorTeatro);

            adminTeatroServicio.crearTeatro(teatro);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Teatro creado correctamente");
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);

        }
    }
}
