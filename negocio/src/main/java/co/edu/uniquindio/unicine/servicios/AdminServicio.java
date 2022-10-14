package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Teatro;

public interface AdminServicio {

    Ciudad crearCiudad(Ciudad ciudad) throws Exception;

    Teatro crearTeatro(Teatro teatro) throws  Exception;

    Cupon crearCupon(Cupon cupon) throws Exception;

}
