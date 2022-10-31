package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    @Query("select f.pelicula.nombre, f.pelicula.estado,f.pelicula.urlImagen, f.sala.codigo, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombre, f.horario from Funcion f where f.pelicula.codigo = :codigoPelicula")
    List<Object[]> listarFunciones(Integer codigoPelicula);

    @Query("select f from Funcion f where f.sala.codigo = :codigoSala and f.horario.codigo = :codigoHorario") //verificar los rangos de las horas, no los ids
    Funcion verificarDisponibilidad(Integer codigoSala, Integer codigoHorario);

    @Query("select f.pelicula from Funcion f where  f.codigo = : codigoPelicula")
    Optional<Pelicula> obtenerPelicula(Integer codigoPelicula);
}
