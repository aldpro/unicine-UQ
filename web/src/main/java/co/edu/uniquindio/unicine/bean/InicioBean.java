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
    private Ciudad ciudad;

    @Setter @Getter
    private ArrayList<String> imagenesCarousel;

    @PostConstruct
    public void inicializar(){

        peliculasCartelera = clienteServicio.listarPeliculasEstado(EstadoPelicula.CARTELERA);
        peliculasEstrenar = clienteServicio.listarPeliculasEstado(EstadoPelicula.PREVENTA);
        ciudades = adminServicio.listarCiudades();
        imagenesCarousel = new ArrayList<>();
        imagenesCarousel.add("https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/6FB49579CE66FD924D7C91DFF86F354DFE10AD56446002E98C1E40B3FD2C7D3F/scale?width=1200&aspectRatio=1.78&format=jpeg");
        imagenesCarousel.add("https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/F6570A8A7FCC8633100E0E0E6B87E081BB433F4658F15D4C8F04D31E6353ECB5/scale?width=1200&aspectRatio=1.78&format=jpeg");
        imagenesCarousel.add("https://imagenes.20minutos.es/files/og_thumbnail/uploads/imagenes/2022/09/29/smile.jpeg");
    }

    public void elegirCiudad(){
        System.out.println(ciudad);
        if (ciudad!= null){
            System.out.println(ciudad.getCodigo());
            peliculasCartelera = clienteServicio.listarPeliculasEstadoCiudad(ciudad.getCodigo(),EstadoPelicula.CARTELERA);
            peliculasEstrenar = clienteServicio.listarPeliculasEstadoCiudad(ciudad.getCodigo(),EstadoPelicula.PREVENTA);
        }
    }
}
