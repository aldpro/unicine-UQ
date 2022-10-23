package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraConfiteriaRepo extends JpaRepository<CompraConfiteria, Integer> {

}
