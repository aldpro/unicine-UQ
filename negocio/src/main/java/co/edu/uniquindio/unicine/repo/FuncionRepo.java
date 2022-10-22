package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre, f.pelicula.estado,f.pelicula.urlImagen, f.sala.codigo, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombre, f.horario from Funcion f where f.pelicula.codigo = :codigoPelicula")
    List<Object[]> listarFunciones(Integer codigoPelicula);
}
