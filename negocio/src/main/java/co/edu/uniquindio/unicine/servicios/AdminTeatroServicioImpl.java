package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio{

    @Autowired
    private final AdministradorTeatroRepo adminTeatroRepo;
    private final HorarioRepo horarioRepo;
    private final FuncionRepo funcionRepo;
    private final SalaRepo salaRepo;
    private final TeatroRepo teatroRepo;

    public AdminTeatroServicioImpl(AdministradorTeatroRepo adminTeatroRepo, HorarioRepo horarioRepo, FuncionRepo funcionRepo, SalaRepo salaRepo, TeatroRepo teatroRepo) {
        this.adminTeatroRepo = adminTeatroRepo;
        this.horarioRepo = horarioRepo;
        this.funcionRepo = funcionRepo;
        this.salaRepo = salaRepo;
        this.teatroRepo = teatroRepo;
    }

    @Override
    public AdministradorTeatro validarLogin(String email, String password) throws Exception {
        if(email.isEmpty() || password.isEmpty()){
            throw new Exception("Por favor rellenar todo los campos de texto");
        }

        AdministradorTeatro administradorTeatro = adminTeatroRepo.comprobarAutenticacion(email, password);

        if (administradorTeatro == null) {
            throw new Exception("Los datos de autentificacion son incorrectos");
        }

        return administradorTeatro;
    }

    @Override
    public Horario crearHorario(Horario horario) {
        return horarioRepo.save(horario);
    }

    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }

    @Override
    public Horario obtenerHorario(Integer codigoHorario) throws Exception {
        Optional<Horario> guardado = horarioRepo.findById(codigoHorario);

        if (guardado.isEmpty()){
            throw new Exception("El horario no existe");
        }
        return guardado.get();
    }

    @Override
    public void eliminarHorario(Integer codigoHorario) throws Exception {
        if(codigoHorario ==  null){
            throw new Exception("No envio niguno codigo");
        }
        Horario horario = obtenerHorario(codigoHorario);

        if(horario == null){
            throw new Exception("El horario no existe");
        }
        horarioRepo.delete(horario);
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) {
        return funcionRepo.save(funcion);
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(funcion.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        return funcionRepo.save(funcion);
    }

    @Override
    public void eliminarFuncion(Integer codigoFuncion) throws Exception {
        if(codigoFuncion ==  null){
            throw new Exception("No envio niguno codigo");
        }
        Funcion funcion = obtenerFuncion(codigoFuncion);

        if(funcion == null){
            throw new Exception("La funcion no existe");
        }
        funcionRepo.delete(funcion);
    }

    @Override
    public List<Funcion> listarFuncion() {
        return funcionRepo.findAll();
    }

    @Override
    public Funcion obtenerFuncion(Integer codigoFuncion) throws Exception {
        Optional<Funcion> guardado = funcionRepo.findById(codigoFuncion);

        if (guardado.isEmpty()){
            throw new Exception("La funcion no existe");
        }
        return guardado.get();
    }

    @Override
    public Sala crearSala(Sala sala) {
        return salaRepo.save(sala);
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(sala.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("La sala no existe");
        }
        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer codigoSala) throws Exception {
        if(codigoSala ==  null){
            throw new Exception("No envio niguno codigo");
        }

        Sala sala = obtenerSala(codigoSala);

        if(sala == null){
            throw new Exception("La sala no existe");
        }
        salaRepo.delete(sala);
    }

    @Override
    public List<Sala> listarSalas() {
        return salaRepo.findAll();
    }

    @Override
    public Sala obtenerSala(Integer codigoSala) throws Exception {
        Optional<Sala> guardado = salaRepo.findById(codigoSala);

        if (guardado.isEmpty()){
            throw new Exception("La sala no existe");
        }
        return guardado.get();
    }

    @Override
    public Teatro crearTeatro(Teatro teatro) {
        return teatroRepo.save(teatro);
    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        Optional<Teatro> guardado = teatroRepo.findById(teatro.getCodigo());

        if (guardado.isEmpty()){
            throw new Exception("El teatro no existe");
        }
        return teatroRepo.save(teatro);
    }

    @Override
    public void eliminarTeatro(Integer codigoTeatro) throws Exception {

        if(codigoTeatro ==  null){
            throw new Exception("No envio niguno codigo");
        }
        Teatro teatro = obtenerTeatro(codigoTeatro);

        if(teatro == null){
            throw new Exception("El teatro no existe");
        }
        teatroRepo.delete(teatro);
    }

    @Override
    public List<Teatro> listarTeatros() {
        return teatroRepo.findAll();
    }

    @Override
    public Teatro obtenerTeatro(Integer codigoTeatro) throws Exception {
        if(codigoTeatro == null){
            throw new Exception("Por favor envie un codigo");
        }
        return teatroRepo.findById(codigoTeatro).orElse(null);
    }
}