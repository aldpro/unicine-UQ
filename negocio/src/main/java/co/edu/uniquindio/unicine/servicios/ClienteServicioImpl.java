package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private final ClienteRepo clienteRepo;
    private final EmailServicio emailServicio;

    private final CuponRepo cuponRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio,
                               CuponRepo cuponRepo) {
        this.emailServicio = emailServicio;
        this.clienteRepo = clienteRepo;
        this.cuponRepo = cuponRepo;
    }

    @Override
    public Cliente obtenerCliente(Integer cedulaCliente) throws Exception {
        Optional<Cliente> guardado = clienteRepo.findById(cedulaCliente);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        return guardado.get();
    }

    @Override
    public Cliente login(String correo, String password) throws Exception {
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if (cliente == null) {
            throw new Exception("Los datos de autentificacion son incorrectos");
        }
        return cliente;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        boolean correoExiste = esRepetido(cliente.getCorreo());
        if (correoExiste) {
            throw new Exception("Este correo ya esta registrado");
        }
        emailServicio.enviarEmail("Registro en unicine", "Por favor acceda al siguiente enlace para activar la cuenta: https://www.instagram.com/henry_barraganp/", cliente.getCorreo());
        return clienteRepo.save(cliente);
    }

    private boolean esRepetido(String correo) {

        return clienteRepo.findByCorreo(correo).orElse(null) != null;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findById(cliente.getCedula());

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer cedulaCliente) throws Exception {

        Optional<Cliente> guardado = clienteRepo.findById(cedulaCliente);

        if (guardado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        clienteRepo.delete(guardado.get());
    }

    @Override
    public List<Cliente> listarClientes() {

        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarHistorialCompras(Integer cedulaCliente) throws Exception {
        return clienteRepo.obtenerComprasCedula(cedulaCliente);
    }

    @Override
    public List<Compra> listarComprasPorCorreo(String correo) throws Exception {
        return clienteRepo.obtenerCompras(correo);
    }


    @Override
    public Compra hacerCompra(Compra compra) throws Exception {
        return null;
    }

    @Override
    public Float redimirCupon(Integer codigoCupon, Float valorInicialCompra) throws Exception {
        float valorFinalCompra = valorInicialCompra;

        Cupon cupon = cuponRepo.obtenerCuponPorCodigo(codigoCupon);

        LocalDateTime fechaActual = LocalDateTime.now();

        if (cupon.getFechaVencimiento().isAfter(fechaActual) && !cupon.getEstado()) {

            valorFinalCompra = valorInicialCompra *(cupon.getDescuento()/100);
            cupon.setEstado(true);
        }


        return valorFinalCompra;
    }


    @Override
    public List<Pelicula> buscarPeliculas(String nombrePelicula) throws Exception {
        return clienteRepo.buscarPeliculas(nombrePelicula);
    }

    @Override
    public boolean cambiarPassword(Integer codigo) throws Exception {
        return false;
    }
}
