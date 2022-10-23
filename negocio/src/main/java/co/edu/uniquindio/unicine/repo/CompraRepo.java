package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.DetalleCompraDTO;
import co.edu.uniquindio.unicine.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    @Query("select new co.edu.uniquindio.unicine.dto.DetalleCompraDTO(c.valorTotal, c.fechaCompra, c.funcion.codigo, (select e.precio from Entrada e where e.compra.codigo = c.codigo), (select conf.precio*conf.unidades from CompraConfiteria conf where conf.compra.codigo = c.codigo))from Compra c where c.cliente.cedula = :cedulaCliente")
    List<DetalleCompraDTO> obtenerInformacionCompra(Integer cedulaCliente);

}
