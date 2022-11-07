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
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    AdminServicio adminServicio;

    @Autowired
    AdminTeatroServicio adminTeatroServicio;

    @Setter @Getter
    private List<Pelicula> peliculasCartelera;

    @Setter @Getter
    private List<Pelicula> peliculasEstrenar;

    @Setter @Getter
    private List<Ciudad> ciudades;

    @Setter @Getter
    private List<Teatro> teatros;

    @Setter @Getter
    private Ciudad ciudad;

    @Setter @Getter
    private Teatro teatro;

    @PostConstruct
    public void inicializar(){

        peliculasCartelera = clienteServicio.listarPeliculasEstado(EstadoPelicula.CARTELERA);
        peliculasEstrenar = clienteServicio.listarPeliculasEstado(EstadoPelicula.PREVENTA);
        ciudades = adminServicio.listarCiudades();
        teatros = adminTeatroServicio.listarTeatros();
    }

    public void elegirCiudad(){
        if (ciudad!= null){
            peliculasCartelera = clienteServicio.listarPeliculasEstadoCiudad(ciudad.getCodigo(),EstadoPelicula.CARTELERA);
            peliculasEstrenar = clienteServicio.listarPeliculasEstadoCiudad(ciudad.getCodigo(),EstadoPelicula.PREVENTA);
        }
    }

    public void elegirTeatro(){

    }
}
