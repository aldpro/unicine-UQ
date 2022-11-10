package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
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
public class DetallePeliculaBean  implements Serializable {

    @Autowired
    private AdminServicio adminServicio;
    @Value("#{param['pelicula_id']}")
    private String peliculaCodigo;

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private List<Funcion> funciones;

    @PostConstruct
    public void init(){
        try {
            if (peliculaCodigo != null && !peliculaCodigo.isEmpty()){
                pelicula = adminServicio.obtenerPelicula(Integer.parseInt(peliculaCodigo));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
