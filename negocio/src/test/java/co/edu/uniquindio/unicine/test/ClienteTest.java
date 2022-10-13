package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registrar(){

        String[] telefonos = new String[] {"3145876258", "3174085147"};
        Cliente cliente = new Cliente(1007569040, "Cristian", "cristian@gmail.com", "Rxrl01", false, "C:\\", Arrays.asList(telefonos));

        Cliente registrado = clienteRepo.save(cliente);

        Assertions.assertNotNull(registrado);
        Assertions.assertEquals("Cristian", registrado.getNombre());
    }

    @Test
    public void eliminar(){

        String[] telefonos = new String[] {"3145876258", "3174085147"};
        Cliente cliente = new Cliente(1007569040, "Cristian", "cristian@gmail.com", "Rxrl01", false, "C:\\", Arrays.asList(telefonos));

        Cliente registrado = clienteRepo.save(cliente);

        clienteRepo.delete(registrado);

        Cliente buscado = clienteRepo.findById(1007569040).orElse(null);
        Assertions.assertNull(buscado);
    }
    @Test
    public void actualizar(){

        String[] telefonos = new String[] {"3145876258", "3174085147"};
        Cliente cliente = new Cliente(1007569040, "Cristian", "cristian@gmail.com", "Rxrl01", false, "C:\\", Arrays.asList(telefonos));

        Cliente registrado = clienteRepo.save(cliente);

        registrado.setEstado(true);

        clienteRepo.save(registrado);

        Cliente buscado = clienteRepo.findById(1007569040).orElse(null);
        Assertions.assertEquals(true, buscado.getEstado());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){
        Cliente buscado = clienteRepo.findById(1007000033).orElse(null);
        System.out.println(buscado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){
        List<Cliente> lista = clienteRepo.findAll();

        lista.forEach(System.out::println);
    }
}
