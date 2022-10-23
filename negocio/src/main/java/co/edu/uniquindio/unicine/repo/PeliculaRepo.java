package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer> {

    @Override
    Optional<Pelicula> findById(Integer codigoPelicula);
}
