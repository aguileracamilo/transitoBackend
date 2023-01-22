package prueba.quileia.paquetes.servicio;

import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.Via;

import java.util.List;

public interface AgenteServicio {

    List<Agente> enlistarAgentes();

    Agente crearAgente(Agente a);

    Agente actualizarAgente(Agente a, String codigoNuevo);

    boolean existeAgente(String codigoAgente);

    void eliminarAgente(String codigo);

    List<AgenteDTO> agenteToDTO(List<Agente> agentes);

    List<Agente> obtenerAgentesPorCodigo(List<String> codigos);

    void asignarAgentes(List<String> agentes, Via via);

    void eliminarAsignacion(List<String> agentesAsignadosNuevo, int id);

    List<String> traerAsignadosNuevos(List<String> agentes, int idVia);

    boolean esMismaAsignacion(String codigoAgente, int idVia);
}
