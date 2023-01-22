package prueba.quileia.paquetes.DTO;

import lombok.*;
import prueba.quileia.paquetes.entidades.Historial;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialDTO {

    private int id;
    private String fecha;
    private String agente;
    private int via;

    public HistorialDTO(Historial historial) {
        this.id = historial.getId();
        this.fecha = historial.getFecha().toString();
        this.agente = historial.getAgente().getCodigo();
        this.via = historial.getVia().getIdVia();
    }
}
