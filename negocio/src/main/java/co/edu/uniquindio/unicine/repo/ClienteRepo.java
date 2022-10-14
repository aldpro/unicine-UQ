package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.correo = :correo and c.password = :password")
    Cliente comprobarAutenticacion(String correo, String password);

    @Query("select compra from Cliente cliente, in (cliente.compras) compra where cliente.correo = :correo")
    List<Compra> obtenerCompras(String correo);
}
