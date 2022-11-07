package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.correo = :correo and c.password = :password")
    Cliente comprobarAutenticacionCliente(String correo, String password);

    @Query("select compra from Cliente cliente, in (cliente.compras) compra where cliente.correo = :correo")
    List<Compra> obtenerCompras(String correo);

    @Query("select c from Cliente cl join cl.compras c where cl.cedula = :cedula")
    List<Compra> obtenerComprasCedula(Integer cedula);

    @Query("select compra from Cliente cliente, in(cliente.compras) compra where cliente.correo = :correo")
    List<Compra> obtenerComprasPorEmail(String correo);

    Optional<Cliente> findByCorreo(String correo);

    @Query("select p from Pelicula p where p.nombre = :nombrePelicula")
    List<Pelicula> buscarPeliculas(String nombrePelicula);

    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.codigo = :codigoCiudad and f.pelicula.estado = :estadoPelicula")
    List<Pelicula> listarPeliculasEstadoCiudad(Integer codigoCiudad, EstadoPelicula estadoPelicula);

    @Query("select distinct f.pelicula from Funcion f where f.pelicula.estado = :estadoPelicula")
    List<Pelicula> listarPeliculasEstado(EstadoPelicula estadoPelicula);
}
