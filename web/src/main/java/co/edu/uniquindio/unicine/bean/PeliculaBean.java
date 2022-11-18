package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.GeneroPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Getter
    @Setter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Pelicula> peliculas;
    @Autowired
    private AdminServicio adminServicio;

    @Setter
    @Getter
    private List<GeneroPelicula> generoPeliculas;

    @Autowired
    CloudinaryServicio cloudinaryServicio;

    private Map<String, String> imagenes;

    private String urlImagenes;

    @Getter @Setter
    private List<EstadoPelicula> estadoPeliculas;

    private boolean editar;

    @Getter @Setter
    private List<Pelicula> peliculasSeleccionadas;

    @PostConstruct
    public void init() {
        pelicula = new Pelicula();
        imagenes = new HashMap<>();
        generoPeliculas = Arrays.asList(GeneroPelicula.values());
        peliculasSeleccionadas = new ArrayList<>();
        peliculas = adminServicio.listarPeliculas();
        estadoPeliculas = Arrays.asList(EstadoPelicula.values());
        /*try{
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile,"Peliculas");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
        }*/
    }

    public void crearPelicula() {

        try {
            if (!imagenes.isEmpty()) {
                pelicula.setImagenes(imagenes);
                pelicula.setEstado(EstadoPelicula.CARTELERA);
                adminServicio.crearPelicula(pelicula);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pelicula creada correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            } else {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario subir una imagen");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public void subirImagen(FileUploadEvent event) {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }

    }

    public File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }

    public void eliminarPelicula(){
        try{
            for (Pelicula p : peliculasSeleccionadas ){
                adminServicio.eliminarPelicula(p.getCodigo());
                peliculas.remove(p);
            }
            peliculasSeleccionadas.clear();
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
        }
    }

    public String getMensajeBorrar(){
        if(peliculasSeleccionadas.isEmpty()){
            return "Borrar";
        }else {
            return peliculasSeleccionadas.size() == 1 ? "Borrar 1 elemento" :
                    "Borrar " + peliculasSeleccionadas.size() + " elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar pelicula" : "Crear pelicula";
    }

    public void crearPeliculaDialogo(){
        this.pelicula = new Pelicula();
        editar = false;
    }

    public void seleccionarPelicula(Pelicula peliculaSeleccionada){
        this.pelicula =  peliculaSeleccionada;
        editar = true;
    }

}
