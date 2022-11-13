package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdminTeatroServicio {

    AdministradorTeatro validarLogin(String correo, String password)throws Exception;

    Horario crearHorario(Horario horario);

    List<Horario> listarHorarios();

    Horario obtenerHorario(Integer codigoHorario) throws Exception;

    void eliminarHorario(Integer codigoHorario) throws Exception;

    Funcion crearFuncion(Funcion funcion) throws Exception;

    Funcion actualizarFuncion(Funcion funcion)throws Exception;

    void eliminarFuncion(Integer codigoFuncion) throws Exception;

    List<Funcion> listarFuncion();

    Funcion obtenerFuncion(Integer codigoFuncion)throws Exception;

    Sala crearSala(Sala sala);

    Sala actualizarSala(Sala sala)throws Exception;

    void eliminarSala(Integer codigoSala) throws Exception;

    List<Sala> listarSalas();

    Sala obtenerSala(Integer codigoSala)throws Exception;

    Teatro crearTeatro(Teatro teatro);

    Teatro actualizarTeatro(Teatro teatro)throws Exception;

    void eliminarTeatro(Integer codigoTeatro) throws Exception;

    List<Teatro> listarTeatros();

    Teatro obtenerTeatro(Integer codigoTeatro) throws Exception;

    Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception;

    List<Teatro> listarTeatrosCiudad(Integer codigoCiudad);

    List<DistribucionSilla> listarDistribucionSillas();

    DistribucionSilla obtenerDistribucionSillas(Integer codigoDistribucionSillas) throws Exception;
}
