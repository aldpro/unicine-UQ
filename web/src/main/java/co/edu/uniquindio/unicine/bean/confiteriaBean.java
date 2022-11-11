package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Confiteria;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class confiteriaBean implements Serializable {

    @Getter @Setter
    private Confiteria confiteria;

    @Getter @Setter
    private List<Confiteria> confiterias;

    private Map<String, String> imagenesConfiteria;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    CloudinaryServicio cloudinaryServicio;

    @Setter @Getter
    private ArrayList<String> imagenesCarousel;

    @PostConstruct
    public void init(){
        confiterias = adminServicio.listarConfiteria();
        imagenesConfiteria = new HashMap<>();

        imagenesCarousel = new ArrayList<>();
        imagenesCarousel.add("https://cloudfront-us-east-1.images.arcpublishing.com/semana/ISWSR7L7T5BM7MKFANV6ESYAU4.jpg");
        imagenesCarousel.add("https://www.procinal.com/uploads/HOME/Noticias_Destacados/PROMOCIONES/Promo-CumpleCF.png");
        imagenesCarousel.add("https://www.ccviva.com/sites/default/files/PROCINAL_BANNER_INT2_1.png");
    }

    public void subirImagen(FileUploadEvent event){
        try{
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile,"confiteria");
            imagenesConfiteria.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
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
}
