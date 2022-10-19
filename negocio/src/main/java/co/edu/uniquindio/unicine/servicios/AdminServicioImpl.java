package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
    public Administrador iniciarSesion(String email, String password) throws Exception {

        if(email.isEmpty() || password.isEmpty()){
            throw new Exception("Por favor rellenar todo los campos de texto");
        }

        Optional<Administrador> administrador = administradorRepo.findByCorreo(email);

        if(administrador.equals(null)){
            throw new Exception("Los datos de autenticacion son incorrectos");
        }

        return administrador.get();
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

        return null;
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

        if (cupon.getDescuento()>0){
            return cuponRepo.save(cupon);
        }else{
            throw new Exception("El descuento debe ser un numero mayor a cero");
        }
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

        if(confiteriaBuscada.equals(null)) {
            throw new Exception("Dos imagenes no pueden ser iguales");
        }

        return confiteriaRepo.save(confiteria);
    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {
        return null;
    }

    @Override
    public void eliminarConfiteria(Integer codigoConfiteria) throws Exception {

    }

    @Override
    public List<Confiteria> listarConfiteria() {
        return null;
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {
        return null;
    }

    @Override
    public AdministradorTeatro crearAdminTeatro(AdministradorTeatro administradorTeatro) throws Exception {
        return null;
    }

    @Override
    public AdministradorTeatro actualizarAdminTeatro(AdministradorTeatro administradorTeatro) throws Exception {
        return null;
    }

    @Override
    public void eliminarAdminTeatro(Integer cedulaAdminTeatro) throws Exception {

    }

    @Override
    public List<AdministradorTeatro> listarAdminsTeatros() {
        return null;
    }

    @Override
    public AdministradorTeatro obtenerAdminTeatro(Integer cedulaAdminTeatro) throws Exception {
        return null;
    }
}
