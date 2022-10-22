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
import java.util.ArrayList;
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

            Ciudad ciudad = new Ciudad("Ibague");
            Ciudad ciudadRegistrada = adminServicio.crearCiudad(ciudad);
            Assertions.assertNotNull(ciudadRegistrada);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTest(){

        try {
           Ciudad ciudad = adminServicio.obtenerCiudad(1);
        }catch (Exception e){
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPeliculaTest(){
        List<String> listaReparto = new ArrayList<>();
        listaReparto.add("Leonardo Di Caprio");
        listaReparto.add("Brad Pitt");
        listaReparto.add("Margot Robbie");

        Pelicula pelicula = new Pelicula(EstadoPelicula.CARTELERA, GeneroPelicula.ACCION, "Nueva pelicula", listaReparto, "Un nuevo amane...", "nuevapelicula.png", "www.youtube.nuevapelicula", 3.5f);
        Pelicula peliculaRegistrada = adminServicio.crearPelicula(pelicula);
        Assertions.assertNotNull(peliculaRegistrada);
    }


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
        LocalDateTime vencimiento = LocalDateTime.of(2023, 06,21,11,42);
        try{
            Cupon cupon = new Cupon("Nuevo cupon", 0.15f,"Nuevo cupon",vencimiento,true);
            Cupon cuponCreado = adminServicio.crearCupon(cupon);
            Assertions.assertNotNull(cuponCreado);
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
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarConfiteriaText(){
        try {
            Confiteria confiteria = adminServicio.obtenerConfiteria(1);
            confiteria.setNombre("Hamburguesa");
            Confiteria nuevo = adminServicio.actualizarConfiteria(confiteria);
            Assertions.assertEquals("Hamburguesa", nuevo.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteriaTest(){
        List<Confiteria> lista = adminServicio.listarConfiteria();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarConfiteriaTest(){
        try {
            adminServicio.eliminarConfiteria(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminServicio.obtenerConfiteria(1);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdminTeatroTest(){

        AdministradorTeatro administradorTeatro = AdministradorTeatro.builder().cedula(1003496468).nombre("Alejandro").correo("alejandro@mail.com").password("alejandro").build();

        try {
            AdministradorTeatro nuevo = adminServicio.crearAdminTeatro(administradorTeatro);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdminTeatroText(){
        try {
            AdministradorTeatro administradorTeatro = adminServicio.obtenerAdminTeatro(1119000000);
            administradorTeatro.setCorreo("ald@gmail.com");
            AdministradorTeatro nuevo = adminServicio.actualizarAdminTeatro(administradorTeatro);
            Assertions.assertEquals("ald@gmail.com", nuevo.getCorreo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdminTeatroTest(){
        try {
            AdministradorTeatro administradorTeatro = adminServicio.obtenerAdminTeatro(1119000000);
            Assertions.assertNotNull(administradorTeatro);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdmninsTeatroTest(){
        List<AdministradorTeatro> lista = adminServicio.listarAdminsTeatros();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdminTeatroTest(){
        try {
            adminServicio.eliminarAdminTeatro(1119000000);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminServicio.obtenerAdminTeatro(1119000000);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
