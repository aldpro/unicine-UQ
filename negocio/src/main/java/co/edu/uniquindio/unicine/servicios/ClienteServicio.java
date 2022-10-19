package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Pelicula;

import java.util.List;


public interface ClienteServicio {

    Cliente obtenerCliente(Integer cedulaCliente) throws Exception;

    Cliente login(String correo, String password) throws Exception;

    Cliente registrarCliente(Cliente cliente) throws Exception;

    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Integer cedulaCliente) throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarHistorialCompras(Integer cedulaCliente)throws Exception;

    List<Compra> listarComprasPorCorreo(String correo) throws Exception;

    Compra hacerCompra(Compra compra)throws Exception;

    Float redimirCupon(Integer codigoCupon, Float valorInicialCompra) throws Exception;

    List<Pelicula> buscarPeliculas(String nombrePelicula) throws Exception;

    boolean cambiarPassword (Integer codigo) throws Exception;
}
