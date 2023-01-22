package prueba.quileia.paquetes.servicio;

import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.DTO.HistorialDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.Historial;
import prueba.quileia.paquetes.entidades.Via;

import java.util.List;


public interface HistorialServicio {

    void registrarAsignacion(Via via, Agente agente);

    void registrarAsignacionAgentes(Via via, List<Agente> agentes);

    List<Historial> obtenerHistorialAgente(String codigoAgente);

    List<HistorialDTO> historialToDTO(List<Historial> historiales);

    List<Historial> obtenerHistorialVia(int idVia);

}
