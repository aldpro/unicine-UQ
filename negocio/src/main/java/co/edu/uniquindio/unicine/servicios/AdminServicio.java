package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdminServicio {

    Ciudad crearCiudad(Ciudad ciudad);

    Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception;

    Pelicula crearPelicula(Pelicula pelicula);

    Pelicula actualizarPelicula(Pelicula pelicula) throws Exception;

    void eliminarPelicula(Integer codigoPelicula) throws Exception;

    List<Pelicula> listarPeliculas();

    Pelicula obtenerPelicula(Pelicula pelicula) throws Exception;

    Cupon crearCupon(Cupon cupon) throws Exception;

    Cupon actualizarCupon(Cupon cupon) throws Exception;

    void eliminarCupon(Integer codigoCupon) throws Exception;

    List<Cupon> listaCupones();

    Cupon obtenerCupon(Integer codigoCupon) throws Exception;

    Confiteria crearConfiteria(Confiteria confiteria) throws  Exception;

    Confiteria actualizarConfiteria(Confiteria confiteria) throws  Exception;

    void eliminarConfiteria(Integer codigoConfiteria)throws Exception;

    List<Confiteria> listarConfiteria();

    Confiteria obtenerConfiteria(Integer codigoConfiteria) throws  Exception;

    AdministradorTeatro crearAdminTeatro(AdministradorTeatro administradorTeatro) throws  Exception;

    AdministradorTeatro actualizarAdminTeatro(AdministradorTeatro administradorTeatro) throws  Exception;

    void eliminarAdminTeatro(Integer cedulaAdminTeatro) throws  Exception;

    List<AdministradorTeatro> listarAdminsTeatros();

    AdministradorTeatro obtenerAdminTeatro(Integer cedulaAdminTeatro) throws  Exception;
}