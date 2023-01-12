package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.servicio.ViaServicio;

@RestController
public class ViaRestController {

    @Autowired
    private ViaServicio viaServicio;

    @CrossOrigin
    @GetMapping("/CrearVia")
    public String enviarVias() {
            System.out.println("entra");
            return "Hola";
    }

/*
    @CrossOrigin
    @PostMapping("/Viass")
    public String pruebaApi(@RequestHeader("as") String as,@RequestHeader("ds") int ds) {
        System.out.println(ds);
        return "You sent: " + ds;
    }
    */

}
