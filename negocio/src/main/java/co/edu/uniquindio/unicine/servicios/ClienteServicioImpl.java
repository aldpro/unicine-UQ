package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private final ClienteRepo clienteRepo;
    private final EmailServicio emailServicio;
    private final CompraConfiteriaRepo compraConfiteriaRepo;
    private final CompraRepo compraRepo;
    private final EntradaRepo entradaRepo;
    private final CuponClienteRepo cuponClienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio,
                               CompraConfiteriaRepo compraConfiteriaRepo, CompraRepo compraRepo,
                               EntradaRepo entradaRepo, CuponClienteRepo cuponClienteRepo) {
        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.cuponClienteRepo =cuponClienteRepo;
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
        if (cliente.getEstado()==false){
            throw new Exception("El cliente no esta activo");
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
        cliente.setEstado(true);
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
    public List<Compra> listarHistorialCompras(Integer cedulaCliente){
        return clienteRepo.obtenerComprasCedula(cedulaCliente);
    }

    @Override
    public List<Compra> listarComprasPorCorreo(String correo){
        return clienteRepo.obtenerCompras(correo);
    }
    
    @Override
    public Compra hacerCompra(Cupon cupon, List<Entrada> entradas, Cliente cliente, List<CompraConfiteria> confiterias, Funcion funcion, CuponCliente cuponCliente) throws Exception {

        float total = 0;

        for (Entrada e : entradas) {
            boolean disponible = verificarDisponibilidad(e);
            if (!disponible) {
                throw new Exception("La silla no está disponible");
            }
            total += e.getPrecio();
        }

        for (CompraConfiteria c : confiterias) {
            total += c.getPrecio() * c.getUnidades();
        }

        if (cupon == null || cupon.getEstado()==false) {
            throw new Exception("El cupon no esta disponible");
        }

        total += funcion.getPrecio();

        Compra compra = new Compra();
        compra.setValorTotal(total); //antes validar el cupón y restar el descuento
        compra.setCliente(cliente);
        compra.setFuncion(funcion);
        compra.setCompraConfiterias(confiterias);
        redimirCupon(cupon.getCodigo(), total);
        compra.setCuponCliente(cupon);

        Compra registro = compraRepo.save(compra);

        for (CompraConfiteria c : confiterias) {
            c.setCompra(registro);
            compraConfiteriaRepo.save(c);
        }

        for(Entrada e : entradas){
            e.setCompra(registro);
            entradaRepo.save(e);
        }

        //enviar correo de compra
        //si es la primera enviar cupón
        if (clienteRepo.obtenerComprasPorEmail(cliente.getCorreo())==null){
            Period periodoVencimiento = Period.ofMonths(1);
            LocalDateTime fechaVencimiento = LocalDateTime.now();
            Cupon cuponPrimeraCompra = new Cupon("Cupon del 10% de descuento por realizar una primera compra por medio de nuestra plataforma", 0.1f, "Primera compra",fechaVencimiento.plus(periodoVencimiento));
            CuponCliente cuponCliente = new CuponCliente(true,cuponPrimeraCompra,cliente);
            emailServicio.enviarEmail("Primera compra","Obtuvo cupon por realizar la primera compra", cliente.getCorreo());
            cliente.getCuponClientes().add(cuponCliente);
        }
        return  compra;
    }

    private boolean verificarDisponibilidad(Entrada entrada) {
        Optional<Entrada> disponibilidadBuscada = entradaRepo.findByFilaAndColumna(entrada.getFila(),entrada.getColumna());
        if (disponibilidadBuscada == null){
            return true;
        }
        return false;
    }

    @Override
    public Float redimirCupon(Integer codigoCupon, Float valorInicialCompra) throws Exception {
        float valorFinalCompra = valorInicialCompra;

        CuponCliente cuponCliente = cuponClienteRepo.obtenerCuponPorCodigo(codigoCupon);

        if (!cuponCliente.getEstado()) {
            valorFinalCompra = valorInicialCompra *(cuponCliente.getCupon().getDescuento()/100);
            cuponCliente.setEstado(true);
        }else{
            throw new Exception("El cupon no esta disponible");
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
