package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;


public interface ClienteServicio {

    Cliente obtenerCliente(Integer cedulaCliente) throws Exception;

    Cliente login(String correo, String password) throws Exception;

    Cliente registrarCliente(Cliente cliente) throws Exception;

    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Integer cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarHistorialCompras(Integer cedulaCliente);

    List<Compra> listarComprasPorCorreo(String correo);

    Compra hacerCompra(List<Entrada> entradas, Cliente cliente, List<CompraConfiteria> confiterias, Funcion funcion, CuponCliente cuponCliente)throws Exception;

    Float redimirCupon(Integer codigoCupon, Float valorInicialCompra) throws Exception;

    List<Pelicula> buscarPeliculas(String nombrePelicula);

    Cliente cambiarPassword(Cliente cliente, String nuevaPassword) throws Exception;

    Compra obtenercompra(Integer codigo)throws Exception;

    Confiteria obtenerConfiteria(Integer codigoConfiteria)throws Exception;

    Funcion obtenerFuncion(Integer codigo)throws Exception;

    Cupon obtenerCupon(Integer codigoCupon)throws Exception;

    Entrada obtenerEntrada(Integer codigoEntrada)throws Exception;

    List<Cupon> listarCuponesCliente(Integer cedula);
}
