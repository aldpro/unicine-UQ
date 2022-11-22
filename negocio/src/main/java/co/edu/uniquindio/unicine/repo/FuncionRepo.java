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



    @Query("select f.pelicula from Funcion f where  f.codigo = : codigoPelicula")
    Optional<Pelicula> obtenerPelicula(Integer codigoPelicula);

    @Query("select f from Funcion f where f.sala.distribucionSilla.filas = :fila and f.sala.distribucionSilla.columnas = :columna")
    Optional<Funcion> verificarDisponibilidadSillas(Integer fila, Integer columna);

    @Query("select f from Funcion f where f.horario = :horario")
    Funcion verificarDisponibilidad(Horario horario);
}
