package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdminServicio {

    //Administrador Aplicacion

    Administrador iniciarSesion(String correo, String password)throws Exception;

    //Gestion de ciudades

    Ciudad crearCiudad(Ciudad ciudad);

    Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception;

    List<Ciudad> listarCiudades();

    //Gestion de peliculas

    Pelicula crearPelicula(Pelicula pelicula);

    Pelicula actualizarPelicula(Pelicula pelicula) throws Exception;

    void eliminarPelicula(Integer codigoPelicula) throws Exception;

    List<Pelicula> listarPeliculas();

    Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception;

    //Gestion de cupones

    Cupon crearCupon(Cupon cupon) throws Exception;

    Cupon actualizarCupon(Cupon cupon) throws Exception;

    void eliminarCupon(Integer codigoCupon) throws Exception;

    List<Cupon> listaCupones();

    Cupon obtenerCupon(Integer codigoCupon) throws Exception;

    //Gestion de confiteria

    Confiteria crearConfiteria(Confiteria confiteria) throws  Exception;

    Confiteria actualizarConfiteria(Confiteria confiteria) throws  Exception;

    void eliminarConfiteria(Integer codigoConfiteria)throws Exception;

    List<Confiteria> listarConfiteria();

    Confiteria obtenerConfiteria(Integer codigoConfiteria) throws  Exception;

    //Gestion administrador de teatros

    AdministradorTeatro crearAdminTeatro(AdministradorTeatro administradorTeatro) throws  Exception;

    AdministradorTeatro actualizarAdminTeatro(AdministradorTeatro administradorTeatro) throws  Exception;

    void eliminarAdminTeatro(Integer cedulaAdminTeatro) throws  Exception;

    List<AdministradorTeatro> listarAdminsTeatros();

    AdministradorTeatro obtenerAdminTeatro(Integer cedulaAdminTeatro) throws  Exception;
}