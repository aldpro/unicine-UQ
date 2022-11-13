package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, Integer> {

    @Query("select a from Administrador a where a.correo = :correo and a.password = :password")
    Administrador comprobarAutenticacionAdmin(String correo, String password);

    Optional<Administrador> findByCorreoAndPassword(String correo, String password);
}
