package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicioImpl implements AdminServicio{

    @Autowired
    private AdministradorRepo administradorRepo;

    public AdminServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception {
        return null;
    }

    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {
        return null;
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
        return null;
    }
}
