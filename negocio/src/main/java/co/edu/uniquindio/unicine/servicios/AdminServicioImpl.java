package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AdminServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, PeliculaRepo peliculaRepo, CuponRepo cuponRepo, CiudadRepo ciudadRepo, ConfiteriaRepo confiteriaRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.peliculaRepo = peliculaRepo;
        this.cuponRepo = cuponRepo;
        this.ciudadRepo = ciudadRepo;
        this.confiteriaRepo = confiteriaRepo;
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad){
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception {

        Optional<Ciudad> ciudad = ciudadRepo.findById(codigoCiudad);

        if (ciudad.isEmpty()){
            throw new Exception("No hay una ciudad con ese codigo");
        }
        return ciudad.get();
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) {
        return null;
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        return null;
    }

    @Override
    public void eliminarPelicula(Integer codigoPelicula) throws Exception {

    }

    @Override
    public List<Pelicula> listarPeliculas() {
        return null;
    }

    @Override
    public Pelicula obtenerPelicula(Pelicula pelicula) throws Exception {
        return null;
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {
        return null;
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        return null;
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {

    }

    @Override
    public List<Cupon> listaCupones() {
        return null;
    }

    @Override
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        return null;
    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {
        return null;
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
