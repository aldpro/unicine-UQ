package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.EntradaRepo;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest(){
        try {
            clienteServicio.login("henryalejandro.702@gmail.com", "CjT30mNQ1dV");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest(){
        Cliente cliente = Cliente.builder().cedula(1003496468).nombre("aldo").password("2312").correo("henryalejandro.702@gmail.com").urlFoto("C/gfeds").build();
        try {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTest(){
        try {
            Cliente cliente = clienteServicio.obtenerCliente(1009000011);
            cliente.setNombre("Nuevo nombre");
            Cliente nuevo = clienteServicio.actualizarCliente(cliente);
            Assertions.assertEquals("Nuevo nombre", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarHistorialComprasTest(){
        List<Compra> lista = clienteServicio.listarHistorialCompras(1008000022);
        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarHistorialComprasCorreoTest(){
        List<Compra> lista = clienteServicio.listarComprasPorCorreo("luisa@google.com");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest(){
        try {
            clienteServicio.eliminarCliente(1009000011);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            clienteServicio.obtenerCliente(1009000011);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
/*
    @Test
    @Sql("classpath:dataset.sql")
    public void hacerCompraTest(){

    }
 */

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClienteTest(){
        List<Cliente> lista = clienteServicio.listarClientes();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculasTest(){
        List<Pelicula> lista = clienteServicio.buscarPeliculas("Pinocho");
        lista.forEach(System.out::println);
    }

    @Test
    public void enviarCorreoTest(){
        emailServicio.enviarEmail("Prueba envio", "Siganme pues :) https://www.instagram.com/henry_barraganp/", "henrya.barraganp@uqvirtual.edu.co");
    }
}
