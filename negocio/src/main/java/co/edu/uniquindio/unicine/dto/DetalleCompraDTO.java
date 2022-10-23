package co.edu.uniquindio.unicine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DetalleCompraDTO {

    private Float valorTotal;
    private LocalDateTime fechaCompra;
    private Integer codigoFuncion;
    private Float precioEntrada;
    private Float precioConfiteria;
}
