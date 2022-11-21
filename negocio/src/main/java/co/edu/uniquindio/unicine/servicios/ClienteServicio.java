package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface ClienteServicio {

    Cliente obtenerCliente(Integer cedulaCliente) throws Exception;

    Cliente login(String correo, String password) throws Exception;

    CuponCliente crearCuponCliente(Integer codigoCupon, Cliente cliente) throws Exception;

    Cliente registrarCliente(Cliente cliente) throws Exception;

    List<Funcion> listarFuncionesCiudad(Integer codigoCiudad);
    List<Funcion> listarFuncionesTeatro(Integer codigoTeatro);

    void activarCliente(String correo, String fecha) throws Exception;

    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Integer cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarHistorialCompras(Integer cedulaCliente);

    List<Compra> listarComprasPorCorreo(String correo);

    List<PeliculaFuncion> listarFuncionesPelicula(String nombre);

    Compra hacerCompra(Cliente cliente, Funcion funcion, MedioPago medioPago, List<CompraConfiteria> confiterias, Integer codigoCupon, List<Entrada> entradas, LocalDateTime fechaCompra)throws Exception;

    CuponCliente validarCupon(Integer codigoCupon);

    Float redimirCupon(Integer codigoCupon, Float valorInicialCompra) throws Exception;

    List<Pelicula> buscarPeliculas(String nombrePelicula);

    Cliente cambiarPassword(Cliente cliente, String nuevaPassword) throws Exception;

    Compra obtenercompra(Integer codigo)throws Exception;

    Confiteria obtenerConfiteria(Integer codigoConfiteria)throws Exception;

    Funcion obtenerFuncion(Integer codigo)throws Exception;

    Cupon obtenerCupon(Integer codigoCupon)throws Exception;

    Entrada obtenerEntrada(Integer codigoEntrada)throws Exception;

    List<Cupon> listarCuponesCliente(Integer cedula);

    List<Pelicula> listarPeliculasEstadoCiudad(Integer codigoCiudad, EstadoPelicula estadoPelicula);

    List<Pelicula> listarPeliculasEstado(EstadoPelicula estadoPelicula);
}
