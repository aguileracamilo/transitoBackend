package prueba.quileia.paquetes.servicio;

import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.entidades.Agente;

import java.util.List;

public interface AgenteServicio {

    List<Agente> enlistarAgentes();

    void crearAgente(Agente agente, int idVia);

    void actualizarAgente(Agente agente, int idVia, String codigoNuevo);

    boolean existeAgente(String codigoAgente);

    void eliminarAgente(String codigo);

    List<AgenteDTO> agenteToDTO(List<Agente> agentes);
}
