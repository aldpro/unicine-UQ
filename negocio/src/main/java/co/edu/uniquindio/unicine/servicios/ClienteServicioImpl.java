package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
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
    private final FuncionRepo funcionRepo;
    private final CuponClienteRepo cuponClienteRepo;
    private final ConfiteriaRepo confiteriaRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio,
                               CompraConfiteriaRepo compraConfiteriaRepo, CompraRepo compraRepo,
                               EntradaRepo entradaRepo, CuponClienteRepo cuponClienteRepo,
                               FuncionRepo funcionRepo, ConfiteriaRepo confiteriaRepo) {
        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.cuponClienteRepo =cuponClienteRepo;
        this.funcionRepo = funcionRepo;
        this.confiteriaRepo = confiteriaRepo;
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
        Cliente cliente = clienteRepo.comprobarAutenticacionCliente(correo, password);

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
    public Compra hacerCompra(List<Entrada> entradas, Cliente cliente, List<CompraConfiteria> confiterias, Funcion funcion, CuponCliente cuponCliente) throws Exception {

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

        total += funcion.getPrecio();

        if (verificarDisponibilidadCupon(cuponCliente.getCodigo())) {
            redimirCupon(cuponCliente.getCodigo(),total);
        }

        Compra compra = new Compra();

        compra.setFechaCompra(LocalDateTime.now());
        compra.setValorTotal(total);
        compra.setCliente(cliente);
        compra.setFuncion(funcion);
        compra.setCompraConfiterias(confiterias);
        compra.setCuponCliente(cuponCliente);

        Compra registro = compraRepo.save(compra);

        for (CompraConfiteria c : confiterias) {
            c.setCompra(registro);
            compraConfiteriaRepo.save(c);
        }

        for(Entrada e : entradas){
            e.setCompra(registro);
            entradaRepo.save(e);
        }


        if (clienteRepo.obtenerComprasPorEmail(cliente.getCorreo()).isEmpty()){
            Period periodoVencimiento = Period.ofMonths(1);
            LocalDateTime fechaVencimiento = LocalDateTime.now();
            Cupon cuponPrimeraCompra = new Cupon("Cupon del 10% de descuento por realizar una primera compra por medio de nuestra plataforma", 10f, "Primera compra",fechaVencimiento.plus(periodoVencimiento));
            cuponCliente = new CuponCliente(true,cuponPrimeraCompra,cliente);
            emailServicio.enviarEmail("Primera compra","Obtuvo cupon por realizar la primera compra", cliente.getCorreo());
            cliente.getCuponClientes().add(cuponCliente);
        }
        return  compra;
    }

    private boolean verificarDisponibilidadCupon(Integer codigo) {
        CuponCliente cuponCliente = cuponClienteRepo.getReferenceById(codigo);

        if (cuponCliente.getEstado()==true){
            return true;
        }
        return false;
    }

    private boolean verificarDisponibilidad(Entrada entrada) {
        Optional<Entrada> disponibilidadBuscada = entradaRepo.findByFilaAndColumna(entrada.getFila(),entrada.getColumna());
        if (disponibilidadBuscada != null){
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
            cuponCliente.setEstado(false);
        }else{
            throw new Exception("El cupon no esta disponible");
        }
        return valorFinalCompra;
    }

    @Override
    public List<Pelicula> buscarPeliculas(String nombrePelicula){

        return clienteRepo.buscarPeliculas(nombrePelicula);
    }

    @Override
    public Cliente cambiarPassword(Cliente cliente,String nuevaPassword) throws Exception {
        Optional<Cliente> guardado = clienteRepo.findById(cliente.getCedula());

        if (guardado.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        emailServicio.enviarEmail("Cambio de Contraseña","Para reestablecer tu contraseña ve al siguiente enlace: ", cliente.getCorreo());
        cliente.setPassword(nuevaPassword);
        return clienteRepo.save(cliente);
    }

    @Override
    public Compra obtenercompra(Integer codigo) throws Exception {
        Optional<Compra> guardado = compraRepo.findById(codigo);

        if (guardado.isEmpty()) {
            throw new Exception("La compra no existe");
        }
        return guardado.get();
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception{
        Optional<Confiteria> guardado = confiteriaRepo.findById(codigoConfiteria);

        if (guardado.isEmpty()) {
            throw new Exception("La confiteria no existe");
        }
        return guardado.get();
    }

    @Override
    public Funcion obtenerFuncion(Integer codigo) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(codigo);

        if (guardado.isEmpty()) {
            throw new Exception("La funcion no existe");
        }
        return guardado.get();
    }

    @Override
    public CuponCliente obtenerCuponCliente(Integer codigo) throws Exception {
        Optional<CuponCliente> guardado = cuponClienteRepo.findById(codigo);

        if (guardado.isEmpty()) {
            throw new Exception("El cupon no existe no existe");
        }
        return guardado.get();
    }

    @Override
    public Entrada obtenerEntrada(Integer codigoEntrada)throws Exception {
        Optional<Entrada> guardado = entradaRepo.findById(codigoEntrada);

        if (guardado.isEmpty()) {
            throw new Exception("La entrada no existe no existe");
        }
        return guardado.get();
    }


}
