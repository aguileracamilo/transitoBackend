package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.servicio.AgenteServicio;
import prueba.quileia.paquetes.servicio.HistorialServicio;
import prueba.quileia.paquetes.servicio.ViaServicio;

import java.util.List;

@RestController
public class ViaRestController {

    @Autowired
    private ViaServicio viaServicio;

    @Autowired
    private AgenteServicio agenteServicio;

    @Autowired
    private HistorialServicio historialServicio;


    @CrossOrigin
    @GetMapping("/TodasVias")
    public List<Via> enviarVias() throws JsonProcessingException {

        return viaServicio.enlistarVias();
    }

    @CrossOrigin
    @PostMapping("/RegistrarVia")
    public String registrarVia(@RequestHeader("id_via") int idVia, @RequestHeader("tipo_via") String tipoVia, @RequestHeader("tipo_calle") String tipoCalle, @RequestHeader("numero_ruta") int numeroRuta, @RequestHeader("nivel_congestion") double nivelCongestion, @RequestHeader("agentes") List<String> agentesAsignados) {
        System.out.println(idVia + " " + tipoVia + " " + tipoCalle + " " + numeroRuta + " " + nivelCongestion);
        //Se encarga de asignar el valor correcto al enum
        TipoVia tipoViaEnum = viaServicio.getTipoVia(tipoVia);
        TipoCalle tipoCalleEnum = viaServicio.getTipoCalle(tipoCalle);

        Via via = new Via(idVia, tipoViaEnum, tipoCalleEnum, numeroRuta, nivelCongestion, null, null);

        if (viaServicio.existeVia(idVia)) {
            return "Ya existe";
        }
        if (!viaServicio.estaCompleto(via)) {
            return "Algun dato esta mal";
        }
        //Se trae la via y los agentes para ser registrados en el historial
        Via viaRegistrada = viaServicio.crearVia(via, agentesAsignados);
        List<Agente> agentes = agenteServicio.obtenerAgentesPorCodigo(agentesAsignados);

        //Se asigna y se registra
        agenteServicio.asignarAgentes(agentesAsignados, viaRegistrada);
        historialServicio.registrarAsignacionAgentes(viaRegistrada, agentes);

        if (viaServicio.existeVia(idVia)) {
            return "Registrado";
        } else {
            return "Error";
        }
    }

    @CrossOrigin
    @PostMapping("/ActualizarVia")
    public String actualizarVia(@RequestHeader("id_nueva") int idNueva, @RequestHeader("id_via") int idVia, @RequestHeader("tipo_via") String tipoVia, @RequestHeader("tipo_calle") String tipoCalle, @RequestHeader("numero_ruta") int numeroRuta, @RequestHeader("nivel_congestion") double nivelCongestion, @RequestHeader("agentes") List<String> agentesAsignados) {

        //Se encarga de asignar el valor correcto al enum
        TipoVia tipoViaEnum = viaServicio.getTipoVia(tipoVia);
        TipoCalle tipoCalleEnum = viaServicio.getTipoCalle(tipoCalle);

        Via via = new Via(idVia, tipoViaEnum, tipoCalleEnum, numeroRuta, nivelCongestion, null, null);

        if (!viaServicio.existeVia(idVia)) {
            return "No existe";
        }
        if (!viaServicio.estaCompleto(via)) {
            return "Algun dato esta mal";
        }

        Via viaActualizada = viaServicio.actualizarVia(via, agentesAsignados, idNueva);
        //Se trae antes de que se asignen los nuevos para evitar problemas
        List<String> asignacionesNuevas = agenteServicio.traerAsignadosNuevos(agentesAsignados, via.getIdVia());
        List<Agente> agentes = agenteServicio.obtenerAgentesPorCodigo(asignacionesNuevas);
        //Se asigna y se registra en el historial
        agenteServicio.asignarAgentes(agentesAsignados, viaActualizada);
        historialServicio.registrarAsignacionAgentes(viaActualizada, agentes);


        if (viaServicio.existeVia((idVia == idNueva) ? idVia : idNueva)) {
            return "Actualizado";
        } else {
            return "Error";
        }

    }

    @CrossOrigin
    @PostMapping("/EliminarVia")
    public String eliminarAgente(@RequestHeader("id_via") int idVia) {

        viaServicio.eliminarVia(idVia);

        if (!viaServicio.existeVia(idVia)) {
            return "Eliminado";
        } else {
            return "error";
        }
    }

}
