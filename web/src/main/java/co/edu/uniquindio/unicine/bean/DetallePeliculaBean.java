package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
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

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

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

    @Getter @Setter
    private Integer diaSeleccionado;

    @Getter @Setter
    private Integer mesSeleccionado;

    @Getter @Setter
    private Integer anioSeleccionado;

    @PostConstruct
    public void init(){
        try {
            if (peliculaCodigo != null && !peliculaCodigo.isEmpty()){
                pelicula = adminServicio.obtenerPelicula(Integer.parseInt(peliculaCodigo));
            }
            if (ciudadCodigo !=null && !ciudadCodigo.isEmpty()){
                teatros = adminTeatroServicio.listarTeatrosCiudad(Integer.parseInt(ciudadCodigo));
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

    public void obtenerFechaSeleccionada(Integer dia, Integer mes, Integer year) {
        this.diaSeleccionado = dia;
        this.mesSeleccionado = mes;
        this.anioSeleccionado = year;
    }

    public String direccionarCompra(Integer codigoFuncion){
        return "/cliente/proceso_compra.xhtml?faces-redirect=true&amp;funcion_codigo="+codigoFuncion;
    }


}
