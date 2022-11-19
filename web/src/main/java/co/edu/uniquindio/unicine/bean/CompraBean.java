package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class CompraBean implements Serializable {

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaSesion;
    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter @Setter
    private String funcionCodigo;

    @Value("#{param['d']}")
    private String diaSeleccionado;

    @Value("#{param['m']}")
    private String mesSeleccionado;

    @Value("#{param['a']}")
    private String anioSeleccionado;

    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private List<Entrada> entradas;

    @Getter @Setter
    private Confiteria confiteria;

    @Getter @Setter
    private List<Confiteria> confiterias;

    @Getter @Setter
    private List<CompraConfiteria> confiteriaFormulario;

    @Getter @Setter
    private DistribucionSilla distribucion;

    @Getter @Setter
    private List<MedioPago> medioPagos;

    @Getter @Setter
    private MedioPago medioPagoSeleccionado;

    @Getter @Setter
    private Integer codigoCupon;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private ArrayList<Integer> filas, columnas;

    @Getter @Setter
    private Float precioCompra;

    @Getter @Setter
    private LocalDateTime fechaSeleccionada;

    @PostConstruct
    public void init(){
        confiterias = adminServicio.listarConfiteria();
        confiteriaFormulario = new ArrayList<>();
        medioPagos = Arrays.asList(MedioPago.values());
        filas = new ArrayList<>();
        columnas = new ArrayList<>();
        entradas = new ArrayList<>();
        precioCompra = 0f;

        confiterias.forEach(c ->{
            confiteriaFormulario.add(CompraConfiteria.builder().confiteria(c).precio(c.getPrecio()).unidades(0).build());
        });

        try {
            cliente = clienteServicio.obtenerCliente(1009000011);

            if (funcionCodigo != null && !funcionCodigo.isEmpty()){
                funcion = adminTeatroServicio.obtenerFuncion(Integer.parseInt(funcionCodigo));
                crearDistribucionSala();
            }

            if (diaSeleccionado != null && mesSeleccionado != null && anioSeleccionado != null){
                String[] hora = funcion.getHorario().getHora().split(":");
                fechaSeleccionada = LocalDateTime.of(Integer.parseInt(anioSeleccionado), Integer.parseInt(mesSeleccionado), Integer.parseInt(diaSeleccionado),Integer.parseInt(hora[0]),Integer.parseInt(hora[1]));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void crearDistribucionSala() {
        distribucion = funcion.getSala().getDistribucionSilla();
        String esquema = distribucion.getUrlEsquema();

        for (int i = 0; i < distribucion.getFilas(); i++){
            filas.add( i );
        }

        for (int i = 0; i <distribucion.getColumnas(); i++){
            columnas.add( i );
        }
    }

    public String hacerCompra(){
        if ( !entradas.isEmpty()){

            try {
                List<CompraConfiteria> lista = confiteriaFormulario.stream().filter(c -> c.getUnidades() > 0).collect(Collectors.toList());

                Compra compra = clienteServicio.hacerCompra(cliente, funcion, medioPagoSeleccionado, lista, codigoCupon, entradas, fechaSeleccionada);

                if (compra != null){
                    FacesMessage fm;

                    if (compra.getCuponCliente() != null) {
                        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente, se le ha enviado un email con la confirmación y se ha redimido el cupon");
                    }else {
                        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada correctamente, se le ha enviado un email con la confirmación");
                    }

                    FacesContext.getCurrentInstance().addMessage("msj_bean", fm);
                    Thread.sleep(2000);
                    return "/cliente/detalle_compra.xhtml?faces-redirect=true&amp;compra_id="+compra.getCodigo();
                }
            }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj_bean", fm);
            }
        }else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario elegir las sillas");
            FacesContext.getCurrentInstance().addMessage("msj_bean", fm);
        }
        return "";
    }

    public void restarUnidades(Integer pos){
        CompraConfiteria compraConfiteria = confiteriaFormulario.get(pos);

        if (compraConfiteria.getUnidades() >= 0) {
            confiteriaFormulario.get(pos).setUnidades(confiteriaFormulario.get(pos).getUnidades() - 1);
        }
    }

    public void sumarUnidades(Integer pos){
        confiteriaFormulario.get(pos).setUnidades(confiteriaFormulario.get(pos).getUnidades() + 1);
    }


    public Integer obtenerUnidadesConfites(Integer pos){
        return confiteriaFormulario.get(pos).getUnidades();
    }

    public boolean buscar(Integer fila, Integer columna) {
        for(Entrada e : entradas){
            if(e.getColumna() == columna && e.getFila() == fila){
                return true;
            }
        }
        return false;
    }

    public void seleccionarSilla(Integer fila, Integer columna){
        if(!buscar(fila, columna)) {
            Entrada entrada = new Entrada();
            entrada.setFila(fila);
            entrada.setColumna(columna);
            entradas.add(entrada);
        }
        else{
            for (int i = 0; i < entradas.size(); i++) {
                Entrada e = entradas.get(i);

                if(e.getColumna() == columna && e.getFila() == fila){
                    entradas.remove(i);
                }
            }
        }
    }
}
