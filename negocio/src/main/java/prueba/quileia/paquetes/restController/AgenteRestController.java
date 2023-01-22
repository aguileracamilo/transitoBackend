package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.Historial;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.servicio.AgenteServicio;
import prueba.quileia.paquetes.servicio.HistorialServicio;
import prueba.quileia.paquetes.servicio.ViaServicio;

import java.util.List;

@RestController
public class AgenteRestController {

    @Autowired
    private AgenteServicio agenteServicio;

    @Autowired
    private HistorialServicio historialServicio;
    @Autowired
    private ViaServicio viaServicio;

    @CrossOrigin
    @GetMapping("/TodosAgentes")
    public List<AgenteDTO> enviarAgentes() throws JsonProcessingException {

        return agenteServicio.agenteToDTO(agenteServicio.enlistarAgentes());
    }

    //Permite registrar a un agente, asignarle una vía y crear un registro de asignación si se cumple las condiciones
    @CrossOrigin
    @PostMapping("/RegistrarAgente")
    public String registrarAgente(@RequestHeader("nombre") String nombre, @RequestHeader("experiencia") double experiencia, @RequestHeader("codigo") String codigo, @RequestHeader("codigo_secretaria") String codigoSecretaria, @RequestHeader("via_asignada") int viaAsignada) {

        String respuesta;

        if (agenteServicio.existeAgente(codigo)) {
            respuesta = "Ya existe";
        } else {
            //Si es 0 significa no se asigno ninguna y si el nivel es menor a 30 no se puede asignar
            Via via = (viaAsignada != 0) ? viaServicio.traerViaPorId(viaAsignada) : null;

            Agente agente = new Agente(codigo, nombre, experiencia, codigoSecretaria, via, null);

            //Es necesario guardar el agente que devuelve de la base de datos para que no haya inconsistencias
            Agente agenteRegistrado = agenteServicio.crearAgente(agente);
            historialServicio.registrarAsignacion(via, agenteRegistrado);

            if (agenteServicio.existeAgente(codigo)) {

                respuesta = "Registrado";
            } else {
                respuesta = "error";
            }
        }
        return respuesta;
    }

    @CrossOrigin
    @PostMapping("/ActualizarAgente")
    public String actualizarAgente(@RequestHeader("nombre") String nombre, @RequestHeader("experiencia") double experiencia, @RequestHeader("codigo") String codigo, @RequestHeader("codigo_nuevo") String codigoNuevo, @RequestHeader("codigo_secretaria") String codigoSecretaria, @RequestHeader("via_asignada") int viaAsignada) {
        //Necesario ejecutar esta sentencia antes de actualizar
        boolean esNuevaAsignacion = agenteServicio.esMismaAsignacion(codigo, viaAsignada);

        if (agenteServicio.existeAgente(codigo)) {
            //Si es 0 significa que no se asignó una vía
            Via via = (viaAsignada != 0) ? viaServicio.traerViaPorId(viaAsignada) : null;
            Agente agente = new Agente(codigo, nombre, experiencia, codigoSecretaria, via, null);

            //Es necesario guardar el agente que devuelve de la base de datos para que no haya inconsistencias
            Agente agenteActualizado = agenteServicio.actualizarAgente(agente, codigoNuevo);
            //Se verifica que no se vaya registrar una asignacion ya existente
            if (esNuevaAsignacion) {
                historialServicio.registrarAsignacion(via, agenteActualizado);
            }
            return "Actualizado";
        } else {

            return "no existe";
        }
    }

    @CrossOrigin
    @PostMapping("/EliminarAgente")
    public String eliminarAgente(@RequestHeader("codigo") String codigo) {

        agenteServicio.eliminarAgente(codigo);

        if (!agenteServicio.existeAgente(codigo)) {
            return "Eliminado";
        } else {
            return "error";
        }
    }
}
