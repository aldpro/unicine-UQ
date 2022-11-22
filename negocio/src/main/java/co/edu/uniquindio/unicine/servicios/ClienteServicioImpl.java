package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    private final PeliculaRepo peliculaRepo;

    private final CuponRepo cuponRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, EmailServicio emailServicio,
                               CompraConfiteriaRepo compraConfiteriaRepo, CompraRepo compraRepo,
                               EntradaRepo entradaRepo, CuponClienteRepo cuponClienteRepo,
                               FuncionRepo funcionRepo, ConfiteriaRepo confiteriaRepo, CuponRepo cuponRepo,
                               PeliculaRepo peliculaRepo) {
        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.cuponClienteRepo =cuponClienteRepo;
        this.funcionRepo = funcionRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.cuponRepo = cuponRepo;
        this.peliculaRepo = peliculaRepo;
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
        Cliente cliente = clienteRepo.findByCorreo(correo).orElse(null);

        if (cliente == null) {
            throw new Exception("El correo no existe");
        }
        if (cliente.getEstado()==false){
            throw new Exception("El cliente no esta activo, debe activarla con el enlace que fue enviado a su correo");
        }
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        if (!spe.checkPassword(password, cliente.getPassword())){
            throw new Exception("La constraseña es incorrecta");
        }

        return cliente;
    }

    @Override
    public CuponCliente crearCuponCliente(Integer codigoCupon, Cliente cliente) throws Exception {

        CuponCliente cuponCliente = cuponClienteRepo.obtenerPorCuponYCliente(codigoCupon, cliente.getCedula());

        if (cuponCliente == null){
            throw new Exception("El codigo o el cliente invalidos");
        }
        return cuponClienteRepo.save(cuponCliente);
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        boolean correoExiste = esRepetido(cliente.getCorreo());

        if (correoExiste) {
            throw new Exception("Este correo ya esta registrado");
        }
        StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
        cliente.setPassword(spe.encryptPassword(cliente.getPassword()));
        Cliente registro = clienteRepo.save(cliente);

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("teclado");

        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Bogota"));

        String param1 = textEncryptor.encrypt(registro.getCorreo());
        String param2 = textEncryptor.encrypt(""+zdt.toInstant().toEpochMilli());

        emailServicio.enviarEmail("Registro en unicine", "Por favor acceda al siguiente enlace para activar la cuenta: http://localhost:8080/activar_cuenta.xhtml?p1="+param1+"&p2="+param2, cliente.getCorreo());
        return registro;
    }

    //obtener funciones por ciudad | teatro
    public List<Funcion> listarFuncionesCiudad(Integer codigoCiudad){
        return clienteRepo.listarFuncionesCiudad(codigoCiudad);
    }

    public List<Funcion> listarFuncionesTeatro(Integer codigoTeatro){
        return clienteRepo.listarFuncionesTeatro(codigoTeatro);
    }

    public void activarCliente(String correo, String fecha) throws Exception{

        correo = correo.replaceAll(" ", "+");
        fecha = fecha.replaceAll(" ", "+");

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("teclado");

        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Bogota"));

        String correoDes = textEncryptor.decrypt(correo);
        String fechaDes = textEncryptor.decrypt(fecha);

        Cliente guardado = clienteRepo.findByCorreo(correoDes).orElse(null);

        if (guardado == null){
            throw new Exception("El cliente no existe");
        }

        guardado.setEstado(true);
        clienteRepo.save(guardado);

        crearCuponCliente(1, guardado);
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
    public List<PeliculaFuncion> listarFuncionesPelicula(String nombre) {
        return peliculaRepo.buscarPeliculas(nombre);
    }

    @Override
    public Compra hacerCompra(Cliente cliente, Funcion funcion, MedioPago medioPago, List<CompraConfiteria> confiterias, Integer codigoCupon, List<Entrada> entradas, LocalDateTime fechaCompra) throws Exception {

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

        if (verificarDisponibilidadCupon(codigoCupon)) {
            redimirCupon(codigoCupon,total);
        }

        Compra compra = new Compra();

        compra.setFechaCompra(LocalDateTime.now());
        compra.setValorTotal(total);
        compra.setCliente(cliente);
        compra.setFuncion(funcion);
        compra.setCompraConfiterias(confiterias);

        Compra registro = compraRepo.save(compra);

        for (CompraConfiteria c : confiterias) {
            c.setCompra(registro);
            compraConfiteriaRepo.save(c);
        }

        for(Entrada e : entradas){
            e.setCompra(registro);
            entradaRepo.save(e);
        }

        /*
        if (clienteRepo.obtenerComprasPorEmail(cliente.getCorreo()).size() ==1 ){
            Period periodoVencimiento = Period.ofMonths(1);
            LocalDateTime fechaVencimiento = LocalDateTime.now();
            Cupon cuponPrimeraCompra = new Cupon("Cupon del 10% de descuento por realizar una primera compra por medio de nuestra plataforma", 10f, "Primera compra",fechaVencimiento.plus(periodoVencimiento));
            cuponCliente = new CuponCliente(true,cuponPrimeraCompra,cliente);
            emailServicio.enviarEmail("Primera compra","Obtuvo cupon por realizar la primera compra", cliente.getCorreo());
            cliente.getCuponClientes().add(cuponCliente);
        }

         */
        return  compra;
    }

    @Override
    public CuponCliente validarCupon(Integer codigoCupon) {
        return null;
    }

    private boolean verificarDisponibilidadCupon(Integer codigo) { //validar el estadoy la fecha de vencimiento
        CuponCliente cuponCliente = cuponClienteRepo.getReferenceById(codigo);

        if (cuponCliente.getEstado()!=true){
            return true;
        }
        return false;
    }

    private boolean verificarDisponibilidad(Entrada entrada) {
        Optional<Funcion> disponibilidadSillas = funcionRepo.verificarDisponibilidadSillas(entrada.getFila(), entrada.getColumna());
        if (disponibilidadSillas == null){
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
        return compraRepo.findById(codigo).orElseThrow(()-> new Exception("No se encontro la compra"));
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
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigoCupon);

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

    @Override
    public List<Cupon> listarCuponesCliente(Integer cedula) {
        return clienteRepo.listarCuponesCliente(cedula);
    }

    @Override
    public List<Pelicula> listarPeliculasEstadoCiudad(Integer codigoCiudad, EstadoPelicula estadoPelicula) {
        return clienteRepo.listarPeliculasEstadoCiudad(codigoCiudad, estadoPelicula);
    }

    @Override
    public List<Pelicula> listarPeliculasEstado(EstadoPelicula estadoPelicula) {
        return clienteRepo.listarPeliculasEstado(estadoPelicula);
    }

}
