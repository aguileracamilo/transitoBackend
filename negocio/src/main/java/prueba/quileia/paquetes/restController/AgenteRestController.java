package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.servicio.AgenteServicio;

import java.util.List;

@RestController
public class AgenteRestController {

    @Autowired
    private AgenteServicio agenteServicio;

    @CrossOrigin
    @GetMapping("/TodosAgentes")
    public List<Agente> enviarAgentes() throws JsonProcessingException {

        return agenteServicio.enlistarAgentes();
    }
    @CrossOrigin
    @PostMapping("/RegistrarAgente")
    public String registrarAgente(@RequestHeader("nombre") String nombre, @RequestHeader("experiencia") double experiencia,@RequestHeader("codigo") String codigo,@RequestHeader("codigo_secretaria") String codigoSecretaria,@RequestHeader("via_asignada") String viaAsignada) {

        Agente agente= new Agente(codigo,nombre,experiencia,codigoSecretaria,null);
        agenteServicio.crearAgente(agente);

        boolean existe = agenteServicio.existeAgente(agente.getCodigo());

        if(existe==true){
            return "Registrado" ;
        }else{
            return "error";
        }


    }

    @CrossOrigin
    @PostMapping("/EliminarAgente")
    public String eliminarAgente(@RequestHeader("codigo") String codigo) {

        System.out.println(codigo);
        agenteServicio.eliminarAgente(codigo);

        boolean existe = agenteServicio.existeAgente(codigo);

        if(existe==true){
            return "Eliminado" ;
        }else{
            return "error";
        }


    }
}
