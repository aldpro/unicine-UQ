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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Cliente cliente = Cliente.builder().cedula(1003496468).nombre("aldo").password("2312").correo("cristianbarrera100@gmail.com").urlFoto("C/gfeds").build();
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

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPasswordTest(){
        try {
            Cliente cliente = clienteServicio.obtenerCliente(1009000011);
            Cliente clienteCambiado = clienteServicio.cambiarPassword(cliente,"pepe123");
            Assertions.assertEquals("pepe123", clienteCambiado.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

/*
    @Test
    @Sql("classpath:dataset.sql")
    public void hacerCompraTest()  {
        try {
            Compra compra = new Compra();

            Entrada entrada1 = clienteServicio.obtenerEntrada(1);
            entrada1.setCompra(compra);
            List<Entrada> entradas = new ArrayList<>();
            entradas.add(entrada1);
            Cliente cliente = clienteServicio.obtenerCliente(1008000022);
            Confiteria confiteria = clienteServicio.obtenerConfiteria(1);
            CompraConfiteria compraConfiteria = new CompraConfiteria(50000f,5,compra,confiteria);
            compraConfiteria.setCodigo(10);
            List<CompraConfiteria> compraConfiterias = new ArrayList<>();
            compraConfiterias.add(compraConfiteria);
            Funcion funcion = clienteServicio.obtenerFuncion(6);
            Cupon cupon = clienteServicio.obtenerCupon(1);
            CuponCliente cuponCliente = new CuponCliente(true,cupon,cliente);
            cuponCliente.setCodigo(5);

            Compra compraRegistrada = clienteServicio.hacerCompra(entradas,cliente,compraConfiterias,funcion,cuponCliente);

            Assertions.assertNotNull(compraRegistrada);
            System.out.println(compraRegistrada);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

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
