package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio{

    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;

    public AdminTeatroServicioImpl(AdministradorTeatroRepo administradorTeatroRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
    }

    @Override
    public Horario crearHorario(Horario horario) throws Exception {
        return null;
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {
        return null;
    }
}
