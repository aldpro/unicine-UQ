package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponClienteRepo extends JpaRepository<CuponCliente, Integer> {

    @Query("select c from CuponCliente c where c.codigo= :codigoCupon")
    CuponCliente obtenerCuponPorCodigo(Integer codigoCupon);
}
