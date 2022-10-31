package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class AdminTeatroServicioTest {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;



    @Test
    @Sql("classpath:dataset.sql")
    public void validarLoginTest() throws Exception {

        AdministradorTeatro administradorTeatro = adminTeatroServicio.validarLogin("jhon@yahoo.com", "02T3CXzA2dX");
        Assertions.assertEquals(1119000000, administradorTeatro.getCedula());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearHorarioTest(){

        try{

            LocalDateTime fechaInicio = LocalDateTime.of(2022, 6,20,9,42);
            LocalDateTime fechaFin = LocalDateTime.of(2022, 7,11,10,21);

            Horario horario = new Horario( fechaInicio,fechaFin);
            Horario horarioCreado = adminTeatroServicio.crearHorario(horario);

            Assertions.assertNotNull(horarioCreado);
        }catch (Exception e) {
            Assertions.assertTrue(false);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorarioTest(){

        try {
            List<Horario> horarios = adminTeatroServicio.listarHorarios();
            horarios.forEach(System.out::println);
            Assertions.assertEquals(6, horarios.size());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public  void obtenerHorarioTest() throws Exception {

        try {
            Horario horario = adminTeatroServicio.obtenerHorario(5);
            Assertions.assertEquals(5, horario.getCodigo());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHorarioTest() throws Exception {

        try {
            adminTeatroServicio.eliminarHorario(5);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminTeatroServicio.obtenerHorario(5);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearFuncionTest() {

        try {

            Funcion funcion = new Funcion();
            funcion.setCodigo(15);
            Horario horario = adminTeatroServicio.obtenerHorario(6);
            Pelicula pelicula = adminTeatroServicio.obtenerPelicula(1);
            Sala sala = adminTeatroServicio.obtenerSala(2);
           funcion.setPrecio(2500f);
            Funcion funcionRegistrada = adminTeatroServicio.crearFuncion(funcion,horario,sala,pelicula,funcion.getPrecio());

            Assertions.assertNotNull(funcionRegistrada);


        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncionTest(){

        try {
            Funcion funcion = adminTeatroServicio.obtenerFuncion(1);
            Pelicula pelicula = adminServicio.obtenerPelicula(3);
            funcion.setPelicula(pelicula);
            Funcion nuevaFuncion = adminTeatroServicio.actualizarFuncion(funcion);
            Assertions.assertEquals(3, nuevaFuncion.getPelicula().getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncionTest(){

        try {
            adminTeatroServicio.eliminarFuncion(6);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminTeatroServicio.obtenerFuncion(6);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionTest(){

        try {
            List<Funcion> funciones = adminTeatroServicio.listarFuncion();
            funciones.forEach(System.out::println);

            Assertions.assertEquals(6, funciones.size());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionTest() throws Exception {

        try {
            Funcion funcion = adminTeatroServicio.obtenerFuncion(3);
            Assertions.assertEquals(3, funcion.getCodigo());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearSalaTest(){

        try {

            AdministradorTeatro administradorTeatro = new AdministradorTeatro(10,"julio","julio@meial.com", "julio123" );
            Ciudad ciudad = adminServicio.obtenerCiudad(1);
            Teatro teatro = new Teatro("Calle 5 #15-48", "32547896", ciudad,administradorTeatro );
            administradorTeatro.getTeatros().add(teatro);
            DistribucionSilla distribucionSilla = new DistribucionSilla("distribucion", 25, 5,5);

            Sala sala = new Sala();
            sala.setTeatro(teatro);
            sala.setTipoSala(TipoSala.VIP);
            sala.setDistribucionSilla(distribucionSilla);
            sala.setNombre("x");


            distribucionSilla.getSalas().add(sala);
            teatro.getSalas().add(sala);

            Sala salaRegistrada = adminTeatroServicio.crearSala(sala);

            Assertions.assertNotNull(salaRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSalaTest(){

        try {
            Sala sala = adminTeatroServicio.obtenerSala(1);
            sala.setNombre("Nueva Sala");
            Sala nuevaSala = adminTeatroServicio.actualizarSala(sala);
            Assertions.assertEquals("Nueva Sala", nuevaSala.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSalaTest(){

        try {
            adminTeatroServicio.eliminarSala(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminTeatroServicio.obtenerSala(1);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalaTest(){

      try {
          List<Sala> salas = adminTeatroServicio.listarSalas();
          salas.forEach(System.out::println);

          Assertions.assertEquals(6, salas.size());
      }catch (Exception e){
          e.printStackTrace();
      }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSalaTest() throws Exception {

        try {
            Sala sala = adminTeatroServicio.obtenerSala(6);

            Assertions.assertEquals(6, sala.getCodigo());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearTeatroTest(){

        try {

            Ciudad ciudad = new Ciudad("Pereira");

            AdministradorTeatro administradorTeatro = new AdministradorTeatro(10,"julio","julio@meial.com", "julio123" );

            Teatro teatro = new Teatro("Calle 5 #15-48", "32547896", ciudad,administradorTeatro );

            Teatro teatro1 = adminTeatroServicio.crearTeatro(teatro);
            administradorTeatro.getTeatros().add(teatro);

            Assertions.assertNotNull(teatro1);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatro(){

        try {
            Teatro teatro = adminTeatroServicio.obtenerTeatro(5);
            teatro.setDireccion("Centro comercial Andino calle 5A norte");
            Teatro nuevoTeatro = adminTeatroServicio.actualizarTeatro(teatro);
            Assertions.assertEquals("Centro comercial Andino calle 5A norte", nuevoTeatro.getDireccion());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(){

        try {
            adminTeatroServicio.eliminarTeatro(3);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try {
            adminTeatroServicio.obtenerTeatro(3);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatrosTest(){

        try {
            List<Teatro> teatros = adminTeatroServicio.listarTeatros();
            teatros.forEach(System.out::println);

            Assertions.assertEquals(6, teatros.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroTest() throws Exception {

        try {
            Teatro teatro = adminTeatroServicio.obtenerTeatro(4);
            System.out.println(teatro);

            Assertions.assertEquals(4, teatro.getCodigo());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
