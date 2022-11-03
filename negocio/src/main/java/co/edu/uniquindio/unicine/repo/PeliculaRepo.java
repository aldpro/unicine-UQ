package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer> {

    @Override
    Optional<Pelicula> findById(Integer codigoPelicula);

    @Query("select new co.edu.uniquindio.unicine.dto.PeliculaFuncion(p, f) from Pelicula p left join p.funciones f where p.nombre like concat('%',:nombre,'%') ")
    List<PeliculaFuncion> buscarPeliculas(String nombre);
}
