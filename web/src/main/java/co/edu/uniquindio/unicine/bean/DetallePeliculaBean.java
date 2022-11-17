package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Dias;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class DetallePeliculaBean  implements Serializable {

    @Autowired
    private AdminServicio adminServicio;
    @Value("#{param['pelicula_id']}")
    private String peliculaCodigo;

    @Value("#{param['ciudad_id']}")
    private String ciudadCodigo;

    @Getter @Setter
    private Pelicula pelicula;

    @Getter @Setter
    private List<Funcion> funciones;

    @Getter @Setter
    private List<LocalDate> fechas;

    @Getter @Setter
    private List<Dias> diaSemanas;

    @Getter @Setter
    private List<Teatro> teatros;

    @PostConstruct
    public void init(){
        try {
            if (peliculaCodigo != null && !peliculaCodigo.isEmpty()){
                pelicula = adminServicio.obtenerPelicula(Integer.parseInt(peliculaCodigo));
            }

            fechas = new ArrayList<>();

            for(int i=0; i<7; i++){
                fechas.add( LocalDate.now().plusDays(i) );
            }

            diaSemanas = Arrays.asList(Dias.values());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
