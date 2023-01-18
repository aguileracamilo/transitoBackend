package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.servicio.AgenteServicio;

import java.util.List;

@RestController
public class AgenteRestController {

    @Autowired
    private AgenteServicio agenteServicio;

    @CrossOrigin
    @GetMapping("/TodosAgentes")
    public List<AgenteDTO> enviarAgentes() throws JsonProcessingException {


        return agenteServicio.agenteToDTO(agenteServicio.enlistarAgentes());
    }

    @CrossOrigin
    @PostMapping("/RegistrarAgente")
    public String registrarAgente(@RequestHeader("nombre") String nombre, @RequestHeader("experiencia") double experiencia, @RequestHeader("codigo") String codigo, @RequestHeader("codigo_secretaria") String codigoSecretaria, @RequestHeader("via_asignada") int viaAsignada) {
        String respuesta;
        boolean existe = agenteServicio.existeAgente(codigo);
        System.out.println(viaAsignada);
        if (existe) {
            respuesta = "Ya existe";
        } else {
            Agente agente = new Agente(codigo, nombre, experiencia, codigoSecretaria, null);
            agenteServicio.crearAgente(agente, viaAsignada);

            existe = agenteServicio.existeAgente(agente.getCodigo());

            if (existe == true) {
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

        System.out.println(nombre + " " + codigo + " " + codigoSecretaria + " " + viaAsignada + " " + experiencia);
        if (agenteServicio.existeAgente(codigo)) {
            Agente agente = new Agente(codigo, nombre, experiencia, codigoSecretaria, null);

            agenteServicio.actualizarAgente(agente, viaAsignada, codigoNuevo);
            return "Actualizado";
        } else {
            return "no existe";
        }


    }


    @CrossOrigin
    @PostMapping("/EliminarAgente")
    public String eliminarAgente(@RequestHeader("codigo") String codigo) {

        System.out.println(codigo);
        agenteServicio.eliminarAgente(codigo);

        boolean existe = agenteServicio.existeAgente(codigo);

        if (existe == false) {
            return "Eliminado";
        } else {
            return "error";
        }


    }
}
