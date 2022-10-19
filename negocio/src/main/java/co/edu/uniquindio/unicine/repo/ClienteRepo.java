package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.correo = :correo and c.password = :password")
    Cliente comprobarAutenticacion(String correo, String password);

    @Query("select compra from Cliente cliente, in (cliente.compras) compra where cliente.correo = :correo")
    List<Compra> obtenerCompras(String correo);

    @Query("select c from Cliente cl join cl.compras c where cl.cedula = :cedula")
    List<Compra> obtenerComprasCedula(Integer cedula);

    Optional<Cliente> findByCorreo(String correo);

    @Query("select p from Pelicula p where p.nombre = :nombrePelicula")
    List<Pelicula> buscarPeliculas(String nombrePelicula);

}
