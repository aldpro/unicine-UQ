package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AdminServicioTest {

    @Autowired
    private AdminServicio adminServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesionTest(){
        try {
            adminServicio.iniciarSesion("cristian@gmail.com", "52t30XzA2dU");
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCiudadTest(){
        try {
            Ciudad ciudad = new Ciudad("Ibague");
            adminServicio.crearCiudad(ciudad);
            System.out.println(ciudad);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTest(){

        try {
           Ciudad ciudad = adminServicio.obtenerCiudad(1);
           System.out.println(ciudad);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }
/*
    @Test
    @Sql("classpath:dataset.sql")
    public void crearPeliculaTest(){
        try {
            Pelicula pelicula = new Pelicula(EstadoPelicula.CARTELERA,);
            adminServicio.crearCiudad(ciudad);
            System.out.println(ciudad);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }
 */

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPeliculaTest(){
        try {
            adminServicio.eliminarPelicula(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminServicio.obtenerPelicula(1);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPeliculaTest(){
        try {
            Pelicula pelicula = adminServicio.obtenerPelicula(1);
            pelicula.setNombre("Nuevo nombre");
            Pelicula nuevo = adminServicio.actualizarPelicula(pelicula);
            Assertions.assertEquals("Nuevo nombre", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasTest(){
        List<Pelicula> lista = adminServicio.listarPeliculas();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponesTest(){
        List<Cupon> lista = adminServicio.listaCupones();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCupontest(){
        LocalDateTime ahora = LocalDateTime.now();
        try{
            Cupon cupon = new Cupon("Nuevo cupon",15.0,"Nuevo cupon",ahora,true);
            adminServicio.crearCupon(cupon);
            System.out.println(cupon);
        }catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCuponText(){
        try {
            Cupon cupon = adminServicio.obtenerCupon(1);
            cupon.setEstado(true);
            Cupon nuevo = adminServicio.actualizarCupon(cupon);
            Assertions.assertEquals(true, nuevo.getEstado());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuponTest(){
        try {
            adminServicio.eliminarCupon(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminServicio.obtenerCupon(1);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void crearConfiteriaTest(){

        Confiteria confiteria = Confiteria.builder().nombre("Aros de cebolla").precio(10000f).urlImagen("C://aroCebolla.png").build();
        try {
            Confiteria nuevo = adminServicio.crearConfiteria(confiteria);
            Assertions.assertNotNull(nuevo);
            System.out.println(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }
}
