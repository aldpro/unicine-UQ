package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServicioImpl implements AdminServicio{

    @Autowired
    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final CuponRepo cuponRepo;
    private final CiudadRepo ciudadRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private  final  AdministradorRepo administradorRepo;

    public AdminServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo, CiudadRepo ciudadRepo, ConfiteriaRepo confiteriaRepo, AdministradorRepo administradorRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.ciudadRepo = ciudadRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Administrador iniciarSesion(String correo, String password) throws Exception {

        if(correo.isEmpty() || password.isEmpty()){
            throw new Exception("Por favor rellenar todo los campos de texto");
        }

        Administrador administrador = administradorRepo.comprobarAutenticacionAdmin(correo, password);

        if (administrador == null) {
            throw new Exception("Los datos de autentificacion son incorrectos");
        }

        return administrador;
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad){
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception {

        Optional<Ciudad> ciudad = ciudadRepo.findById(codigoCiudad);

        if (ciudad.isEmpty()){
            throw new Exception("La ciudad no existe");
        }
        return ciudad.get();
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(pelicula.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("La pelicula no existe");
        }
        return peliculaRepo.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer codigoPelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(codigoPelicula);

        if (guardado.isEmpty()){
            throw new Exception("La pelicula no existe");
        }
        peliculaRepo.delete(guardado.get());
    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return peliculaRepo.findAll();
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepo.findById(codigoPelicula);

        if (guardado.isEmpty()){
            throw new Exception("La pelicula no existe");
        }
        return guardado.get();
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception{

        LocalDateTime fechaActual = LocalDateTime.now();
        if (cupon.getDescuento()<=0){
            throw new Exception("El valor del decuento debe ser mayor a 0");
        }else{
            if(fechaActual.isAfter(cupon.getFechaVencimiento())){
                throw new Exception("La fecha de vencimineto no puede ser menor a la fecha actual");
            }
        }
        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(cupon.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        return cuponRepo.save(cupon);
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigoCupon);

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        cuponRepo.delete(guardado.get());
    }

    @Override
    public List<Cupon> listaCupones() {
        return cuponRepo.findAll();
    }

    @Override
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigoCupon);

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        return guardado.get();
    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {

        Optional<Confiteria> confiteriaBuscada = confiteriaRepo.findByUrlImagen(confiteria.getUrlImagen());

        if(confiteriaBuscada.isPresent()) {
            throw new Exception("Dos imagenes no pueden ser iguales");
        }
        return confiteriaRepo.save(confiteria);
    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepo.findById(confiteria.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("La confiteria no existe");
        }
        return confiteriaRepo.save(confiteria);
    }

    @Override
    public void eliminarConfiteria(Integer codigoConfiteria) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepo.findById(codigoConfiteria);

        if (guardado.isEmpty()){
            throw new Exception("Esta confiteria no existe");
        }
        confiteriaRepo.delete(guardado.get());
    }

    @Override
    public List<Confiteria> listarConfiteria() {
        return confiteriaRepo.findAll();
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepo.findById(codigoConfiteria);

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        return guardado.get();
    }

    @Override
    public AdministradorTeatro crearAdminTeatro(AdministradorTeatro administradorTeatro) throws Exception {

        Optional<AdministradorTeatro> cedulaRegistrada = administradorTeatroRepo.findById(administradorTeatro.getCedula());
        Optional<AdministradorTeatro> correoRegistrado = administradorTeatroRepo.findByCorreo(administradorTeatro.getCorreo());

        if (cedulaRegistrada.isPresent()) {
            throw new Exception("Esta cedula ya esta registrada");
        }
        if (correoRegistrado.isPresent()){
            throw new Exception("El correo electronico ya se encuentra registrado");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public AdministradorTeatro actualizarAdminTeatro(AdministradorTeatro administradorTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(administradorTeatro.getCedula());

        if (guardado.isEmpty()){
            throw new Exception("El administrador de teatro no existe");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public void eliminarAdminTeatro(Integer cedulaAdminTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(cedulaAdminTeatro);

        if (guardado.isEmpty()){
            throw new Exception("Este administrador de teatro no existe");
        }
        administradorTeatroRepo.delete(guardado.get());
    }

    @Override
    public List<AdministradorTeatro> listarAdminsTeatros() {
        return administradorTeatroRepo.findAll();
    }

    @Override
    public AdministradorTeatro obtenerAdminTeatro(Integer cedulaAdminTeatro) throws Exception {
        Optional<AdministradorTeatro> guardado = administradorTeatroRepo.findById(cedulaAdminTeatro);

        if(cedulaAdminTeatro == null){
            throw new Exception("Por favor envie una cedula");
        }

        if (guardado.isEmpty()){
            throw new Exception("El administrador de teatro no existe");
        }
        return guardado.get();
    }
}
