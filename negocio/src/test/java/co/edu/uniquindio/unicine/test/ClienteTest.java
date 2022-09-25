package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registrar(){

        String[] tels = new String[] {"423431", "532421"};
        Cliente cliente = new Cliente("pepe", "pepe@mail.com", "1122", "ruta", Arrays.asList(tels));
        Cliente guardado = clienteRepo.save(cliente);

        Assertions.assertEquals("pepe", guardado.getNombre());
    }
    public void eliminar(){

    }
    public void actualizar(){

    }
    public void obtener(){

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){
        List<Cliente> lista = clienteRepo.findAll();

        lista.forEach(System.out::println);
    }
}
