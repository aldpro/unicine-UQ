package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Persona;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private Boolean autenticado;

    @Getter @Setter
    private String correo, password;

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private String tipoSesion;

    @Getter @Setter
    private Ciudad ciudadSeleccionada;


    @PostConstruct
    public void init(){
        autenticado = false;

    }

    public String iniciarSesionCliente(){

        if (!correo.isEmpty() && !password.isEmpty()){
            try {
                persona = clienteServicio.login(correo,password);
                tipoSesion = "cliente";
                autenticado = true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("Login-bean", fm);
            }
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","El correo y la contraseña son necesarios");
            FacesContext.getCurrentInstance().addMessage("Login-bean", fm);
        }
        return null;
    }
    public String iniciarSesionAdmins(){

        if (!correo.isEmpty() && !password.isEmpty()){
            try {
                System.out.println("HOLA");
                persona = adminServicio.iniciarSesion(correo,password);
                if (persona == null){
                    persona = adminTeatroServicio.validarLogin(correo,password);
                    tipoSesion = "admin_teatro";
                }else {
                    tipoSesion="admin";
                }
                autenticado =true;
                return "/index_admin?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("Login-bean", fm);
            }
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","El correo y la contraseña son necesarios");
            FacesContext.getCurrentInstance().addMessage("Login-bean", fm);
        }
        return null;
    }

    public void seleccionarCiudad(Ciudad ciudad){
        this.ciudadSeleccionada = ciudad;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public boolean isAutenticado() {
        return autenticado=true;
    }
}
