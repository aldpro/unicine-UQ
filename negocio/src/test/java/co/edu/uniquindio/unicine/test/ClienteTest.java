package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        String[] telefonos = new String[] {"3145876258", "3174085147"};
        Cliente cliente = new Cliente(1007569040, "Cristian", "cristian@gmail.com", "Rxrl01", false, "C:\\", Arrays.asList(telefonos));

        Cliente registrado = clienteRepo.save(cliente);

        Assertions.assertNotNull(registrado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        Cliente buscado = clienteRepo.findById(1009000011).orElse(null);

        clienteRepo.delete(buscado);
        Assertions.assertNull(clienteRepo.findById(1009000011).orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        Cliente guardado = clienteRepo.findById(1009000011).orElse(null);

        guardado.setCorreo("pepe_nuevo@mail.com");

        Cliente nuevo = clienteRepo.save(guardado);
        Assertions.assertEquals("pepe_nuevo@mail.com", nuevo.getCorreo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){

        Optional<Cliente> buscado = clienteRepo.findById(1009000011);
        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){

        List<Cliente> lista = clienteRepo.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion(){

        Cliente cliente = clienteRepo.comprobarAutenticacion("pepe@hotmail.com", "CjT30mNQ1dV");
        System.out.println(cliente);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){

        List<Compra> compras = clienteRepo.obtenerCompras("juan@outlook.com");
        compras.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorCorreo(){

        Cliente cliente = clienteRepo.findByCorreo("pepe@hotmail.com").orElse(null);
        System.out.println(cliente);
    }
}
