package co.edu.uniquindio.unicine.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicio {

    private Cloudinary cloudinary;
    private Map<String,String> config;

    public CloudinaryServicio(){

        config =new HashMap<>();
        config.put("cloud_name", "dwu4xtiun");
        config.put("api_key", "926435952639986");
        config.put("api_secret", "Aix9StkZ6hUYq4uJtjyd-wXIz5s");

        cloudinary = new Cloudinary(config);
    }

    public Map subirImagen(File file, String carpeta)throws Exception{
        Map respuesta = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", carpeta));
        return respuesta;
    }

    public Map eliminarImagen(String idImagen)throws Exception{
        Map respuesta = cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
        return respuesta;
    }
}
