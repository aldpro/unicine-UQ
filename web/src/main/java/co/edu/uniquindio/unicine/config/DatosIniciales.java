package co.edu.uniquindio.unicine.config;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatosIniciales implements CommandLineRunner {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Override
    public void run(String... args) throws Exception {

        try {

            List<Ciudad> ciudades = adminServicio.listarCiudades();

            if (ciudades.isEmpty()) {

                Ciudad c1 = Ciudad.builder().nombre("Armenia").build();
                Ciudad c2 = Ciudad.builder().nombre("Pereira").build();
                Ciudad c3 = Ciudad.builder().nombre("Cali").build();
                Ciudad c4 = Ciudad.builder().nombre("Bogota").build();
                Ciudad c5 = Ciudad.builder().nombre("Choco").build();
                Ciudad c6 = Ciudad.builder().nombre("Manizales").build();

                adminServicio.crearCiudad(c1);
                adminServicio.crearCiudad(c2);
                adminServicio.crearCiudad(c3);
                adminServicio.crearCiudad(c4);
                adminServicio.crearCiudad(c5);
                adminServicio.crearCiudad(c6);

                AdministradorTeatro adminTeatro1 = AdministradorTeatro.builder().cedula(1228000000).nombre("Maria").correo("mariaf.camachog@uqvirtual.edu.co").password("1234").build();
                AdministradorTeatro adminTeatro2 = AdministradorTeatro.builder().cedula(1119000000).nombre("Jhon").correo("jhona.belloc@uqvirtual.edu.co").password("1234").build();
                AdministradorTeatro adminTeatro3 = AdministradorTeatro.builder().cedula(1337000000).nombre("Cristian").correo("cristians.barreram@uqvirtual.edu.co").password("1234").build();
                AdministradorTeatro adminTeatro4 = AdministradorTeatro.builder().cedula(1446000000).nombre("Alejandro").correo("henrya.barraganp@uqvirtual.edu.co").password("1234").build();
                AdministradorTeatro adminTeatro5 = AdministradorTeatro.builder().cedula(1557000000).nombre("Rodolfo").correo("rodolfo@email.com").password("1234").build();
                AdministradorTeatro adminTeatro6 = AdministradorTeatro.builder().cedula(1657000800).nombre("Jose").correo("jose@email.com").password("1234").build();

                adminServicio.crearAdminTeatro(adminTeatro1);
                adminServicio.crearAdminTeatro(adminTeatro2);
                adminServicio.crearAdminTeatro(adminTeatro3);
                adminServicio.crearAdminTeatro(adminTeatro4);
                adminServicio.crearAdminTeatro(adminTeatro5);
                adminServicio.crearAdminTeatro(adminTeatro6);

                Administrador admin1 = Administrador.builder().cedula(87783).nombre("Henry").correo("henrya.barraganp@uqvirtual.edu.co").password("1234").build();

                adminServicio.crearAministrador(admin1);

                Map<String, String> imagenesP1 = new HashMap<>();
                imagenesP1.put("https://res.cloudinary.com/dwu4xtiun/image/upload/v1667875113/unicine/peliculas/pinocho_wehtj2.jpg",
                        "unicine/peliculas/pinocho_wehtj2");


                Pelicula peli1 = Pelicula.builder().estado(EstadoPelicula.valueOf("PREVENTA")).nombre("Pinocho")
                        .imagenes(imagenesP1)
                        .puntuacion(4.2f).reparto("Tom Hanks, Cynthia Erivo, Luke Evans").
                        sinopsis("En un pueblo italiano, el títere de madera Pinocho cobra vida gracias al Hada Azul. Pinocho se esfuerza por comportarse como un niño de carne y hueso, pero su vida da un giro al abandonar a su padre para unirse a un circo.")
                        .urlTrailer("https://www.youtube.com/embed/TITv1TNi5mI").build();
                Pelicula peli2 = Pelicula.builder().estado(EstadoPelicula.valueOf("CARTELERA")).nombre("Dragon Ball: Super Hero")
                        .puntuacion(3.5f).reparto("	Masako Nozawa, Toshio Furukawa, Ryō Horikawa, Yūko Minaguchi")
                        .sinopsis("La malvada organización Red Ribbon Army se reforma con nuevos y más poderosos androides, Gamma {1} y Gamma {2} para buscar venganza.")
                        .urlTrailer("https://www.youtube.com/embed/lXLPVQ-WrU4").build();
                Pelicula peli3 = Pelicula.builder().estado(EstadoPelicula.valueOf("CARTELERA")).nombre("Smile").puntuacion(4.0f).reparto("Sosie Bacon, Jessie T Usher")
                        .sinopsis("Después de ser testigo de un extraño y traumático accidente que involucró a una paciente, la Dr. Rose Cotter (Sosie Bacon) empieza a experimentar sucesos aterradores que no puede explicarse. A medida que el terror comienza a apoderarse de su vida, Rose debe enfrentarse a su pasado para sobrevivir y escapar de su horrible nueva realidad.")
                        .urlTrailer("https://www.youtube.com/embed/yhKiQGJop_8").build();
                Pelicula peli4 = Pelicula.builder().estado(EstadoPelicula.valueOf("CARTELERA")).nombre("Minions")
                        .puntuacion(4.5f).reparto("Sandra Bullock, Jon Hamm, Michael Keaton")
                        .sinopsis("En los años 70, Gru crece siendo un gran admirador de <<Los salvajes seis>>, un supergrupo de villanos. Para demostrarles que puede ser malvado, Gru idea un plan con la esperanza de formar parte de la banda. Por suerte, cuenta con la ayuda de sus fieles seguidores, los Minions, siempre dispuestos a sembrar el caos.")
                        .urlTrailer("https://www.youtube.com/embed/W27moupirnI").build();
                Pelicula peli5 = Pelicula.builder().estado(EstadoPelicula.valueOf("PREVENTA")).nombre("Encanto").puntuacion(4.1f)
                        .reparto("Stephanie Beatriz, María Cecilia Botero, John Leguizamo")
                        .sinopsis("En lo alto de las montañas de Colombia hay un lugar encantado llamado Encanto. Aquí, en una casa mágica, vive la extraordinaria familia Madrigal donde todos tienen habilidades fantásticas.")
                        .urlTrailer("https://www.youtube.com/embed/SAH_W9q_brE").build();

                adminServicio.crearPelicula(peli1);
                adminServicio.crearPelicula(peli2);
                adminServicio.crearPelicula(peli3);
                adminServicio.crearPelicula(peli4);
                adminServicio.crearPelicula(peli5);

                Cupon cup1 = Cupon.builder().descripcion("Cupon del 15% de descuento por registrarse por primera vez en nuestra plataforma").descuento(15.0f)
                        .criterio("Primer registro").fechaVencimiento(LocalDateTime.parse("2022-12-25T20:00:00")).build();
                Cupon cup2 = Cupon.builder().descripcion("Cupon del 10% de descuento por realizar una primera compra por medio de nuestra plataforma")
                        .descuento(10.0f).criterio("Primera compra").fechaVencimiento(LocalDateTime.parse("2022-12-19T15:45:00")).build();

                adminServicio.crearCupon(cup1);
                adminServicio.crearCupon(cup2);

                Confiteria conf1 = Confiteria.builder().nombre("Combo para niños").precio(15000f)
                        .urlImagen("https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927564/unicine/confiteria/combo_ni%C3%B1os_ydpbay.jpg").build();
                Confiteria conf2 = Confiteria.builder().nombre("Combo para dos").precio(49900f)
                        .urlImagen("https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927565/unicine/confiteria/combo_para_dos_r5rvxp.jpg").build();
                Confiteria conf3 = Confiteria.builder().nombre("Crispeta + Dos Gaseosas").precio(29800f)
                        .urlImagen("https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927564/unicine/confiteria/crispeta_2gaseosas_vnrpli.jpg").build();
                Confiteria conf4 = Confiteria.builder().nombre("Gaseosa + Perro caliente + Crispeta + KitKat").precio(19900f)
                        .urlImagen("https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927565/unicine/confiteria/combo_para_dos_r5rvxp.jpg").build();
                Confiteria conf5 = Confiteria.builder().nombre("Nevado de arequipe").precio(6000f)
                        .urlImagen("https://res.cloudinary.com/dwu4xtiun/image/upload/v1667927565/unicine/confiteria/nevado_arequipe_afpfeo.jpg").build();

                adminServicio.crearConfiteria(conf1);
                adminServicio.crearConfiteria(conf2);
                adminServicio.crearConfiteria(conf3);
                adminServicio.crearConfiteria(conf4);
                adminServicio.crearConfiteria(conf5);

                Teatro teatro1 = Teatro.builder().direccion("Carrera 14 # 4-6 Norte").telefono("3185469257").ciudad(c5).administradorTeatro(adminTeatro2).build();
                Teatro teatro2 = Teatro.builder().direccion("Calle 3 # 1 A 24 Sur").telefono("3185749321").ciudad(c4).administradorTeatro(adminTeatro1).build();
                Teatro teatro3 = Teatro.builder().direccion("Calle 16 4-2 Centro").telefono("3124720846").ciudad(c4).administradorTeatro(adminTeatro3).build();
                Teatro teatro4 = Teatro.builder().direccion("Carrera 7 # B 12-13 Sur").telefono("3001247585").ciudad(c3).administradorTeatro(adminTeatro4).build();
                Teatro teatro5 = Teatro.builder().direccion("Carrera 9 # 4-7 Oeste").telefono("3186347896").ciudad(c2).administradorTeatro(adminTeatro5).build();
                Teatro teatro6 = Teatro.builder().direccion("Calle 4 # 4-2 Sur").telefono("3178532410").ciudad(c1).administradorTeatro(adminTeatro6).build();
                //adminTeatro2.getTeatros().add(teatro1);

                adminTeatroServicio.crearTeatro(teatro1);
                adminTeatroServicio.crearTeatro(teatro2);
                adminTeatroServicio.crearTeatro(teatro3);
                adminTeatroServicio.crearTeatro(teatro4);
                adminTeatroServicio.crearTeatro(teatro5);
                adminTeatroServicio.crearTeatro(teatro6);

                Sala sala1 = Sala.builder().nombre("Atlantis").tipoSala(TipoSala.XD).teatro(teatro5).build();
                Sala sala2 = Sala.builder().nombre("FLoresta").tipoSala(TipoSala.NORMAL).teatro(teatro5).build();
                Sala sala3 = Sala.builder().nombre("Gran Plaza Bosa").tipoSala(TipoSala.IMAX).teatro(teatro4).build();
                Sala sala4 = Sala.builder().nombre("Altavista").tipoSala(TipoSala.IMAX).teatro(teatro6).build();
                Sala sala5 = Sala.builder().nombre("Multiplaza").tipoSala(TipoSala.XD).teatro(teatro3).build();
                Sala sala6 = Sala.builder().nombre("Parque Colonia").tipoSala(TipoSala.NORMAL).teatro(teatro2).build();
                Sala sala7 = Sala.builder().nombre("Plaza Imperial").tipoSala(TipoSala.DX4).teatro(teatro1).build();
                Sala sala8 = Sala.builder().nombre("Colon").tipoSala(TipoSala.IMAX).teatro(teatro6).build();

                adminTeatroServicio.crearSala(sala1);
                adminTeatroServicio.crearSala(sala2);
                adminTeatroServicio.crearSala(sala3);
                adminTeatroServicio.crearSala(sala4);
                adminTeatroServicio.crearSala(sala5);
                adminTeatroServicio.crearSala(sala6);
                adminTeatroServicio.crearSala(sala7);
                adminTeatroServicio.crearSala(sala8);

                Horario hora1 = Horario.builder().fechaFin(LocalDate.of(2022, 12, 20) ).fechaInicio(LocalDate.of(2022, 12, 20)).hora("20:00").build();
                Horario hora2 = Horario.builder().fechaFin(LocalDate.of(2022, 12, 15)).fechaInicio(LocalDate.of(2022, 12, 15)).hora("21:00").build();
                Horario hora3 = Horario.builder().fechaFin(LocalDate.of(2022, 12, 16)).fechaInicio(LocalDate.of(2022, 12, 16)).hora("22:00").build();
                Horario hora4 = Horario.builder().fechaFin(LocalDate.of(2022, 12, 17)).fechaInicio(LocalDate.of(2022, 12, 17)).hora("20:00").build();
                Horario hora5 = Horario.builder().fechaFin(LocalDate.of(2022, 12, 22)).fechaInicio(LocalDate.of(2022, 12, 22)).hora("20:00").build();
                Horario hora6 = Horario.builder().fechaFin(LocalDate.of(2022, 12, 17)).fechaInicio(LocalDate.of(2022, 12, 17)).hora("20:00").build();

                adminTeatroServicio.crearHorario(hora1);
                adminTeatroServicio.crearHorario(hora2);
                adminTeatroServicio.crearHorario(hora3);
                adminTeatroServicio.crearHorario(hora4);
                adminTeatroServicio.crearHorario(hora5);
                adminTeatroServicio.crearHorario(hora6);

                Funcion funcion1 = Funcion.builder().precio(7000f).horario(hora1).pelicula(peli1).sala(sala6).build();
                Funcion funcion2 = Funcion.builder().precio(6500f).horario(hora2).pelicula(peli2).sala(sala5).build();
                Funcion funcion3 = Funcion.builder().precio(6800f).horario(hora3).pelicula(peli3).sala(sala4).build();
                Funcion funcion4 = Funcion.builder().precio(6800f).horario(hora4).pelicula(peli4).sala(sala3).build();
                Funcion funcion5 = Funcion.builder().precio(7100f).horario(hora5).pelicula(peli5).sala(sala2).build();
                Funcion funcion6 = Funcion.builder().precio(6800f).horario(hora6).pelicula(peli4).sala(sala1).build();
                Funcion funcion7 = Funcion.builder().precio(10000f).horario(hora1).pelicula(peli3).sala(sala8).build();

                adminTeatroServicio.crearFuncion(funcion1);
                adminTeatroServicio.crearFuncion(funcion2);
                adminTeatroServicio.crearFuncion(funcion3);
                adminTeatroServicio.crearFuncion(funcion4);
                adminTeatroServicio.crearFuncion(funcion5);
                adminTeatroServicio.crearFuncion(funcion6);
                adminTeatroServicio.crearFuncion(funcion7);

                adminServicio.actualizarAdminTeatro(adminTeatro2);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
