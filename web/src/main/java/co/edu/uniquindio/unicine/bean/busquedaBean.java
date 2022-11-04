package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class busquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<PeliculaFuncion> peliculas;

    @Setter @Getter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void init(){
        if (busquedaParam!=null && !busquedaParam.isEmpty()){
            peliculas = clienteServicio.listarFuncionesPelicula(busquedaParam);
        }
        ciudades = adminServicio.listarCiudades();
    }

    public String buscar(){
        if (!busqueda.isEmpty()){
            return "/resultados_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }
}
