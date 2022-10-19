package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends JpaRepository<Cupon, Integer> {

    @Query("select c from Cupon c where c.codigo= :codigoCupon")
    Cupon obtenerCuponPorCodigo(Integer codigoCupon);
}
