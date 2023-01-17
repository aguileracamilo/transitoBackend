package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.servicio.ViaServicio;

import java.util.List;

@RestController
public class ViaRestController {

    @Autowired
    private ViaServicio viaServicio;


    @CrossOrigin
    @GetMapping("/TodasVias")
    public List<Via> enviarVias() throws JsonProcessingException {

        System.out.println("<aaaa");
        return viaServicio.enlistarVias();
    }

    @CrossOrigin
    @GetMapping("/CrearVia")
    public String envidarVias() {
        System.out.println("entra");
        return "Hola";
    }

    @CrossOrigin
    @PostMapping("/RegistrarVia")
    public String registrarVia(@RequestHeader("id_via") int idVia, @RequestHeader("tipo_via") String tipoVia, @RequestHeader("tipo_calle") String tipoCalle, @RequestHeader("numero_ruta") int numeroRuta, @RequestHeader("nivel_congestion") double nivelCongestion) {
        System.out.println(idVia + " " + tipoVia + " " + tipoCalle + " " + numeroRuta + " " + nivelCongestion);
        boolean existe = false;
        TipoVia tipoViaEnum = null;
        TipoCalle tipoCalleEnum = null;
        switch (tipoVia) {
            case "opcion1":
                tipoViaEnum = TipoVia.AUTOPISTA;
                break;
            case "opcion2":
                tipoViaEnum = TipoVia.CARRETERA_PRINCIPAL;
                break;
            case "opcion3":
                tipoViaEnum = TipoVia.CARRETERA_SECUNDARIA;
                break;
        }
        switch (tipoVia) {
            case "opcion1":
                tipoCalleEnum = TipoCalle.CARRERA;
                break;
            case "opcion2":
                tipoCalleEnum = TipoCalle.CARRERA;
                break;
        }
        if (tipoViaEnum != null && tipoCalleEnum != null) {
            Via via = new Via(idVia, tipoViaEnum, tipoCalleEnum, numeroRuta, nivelCongestion, null);
            viaServicio.crearVia(via);
            existe = viaServicio.existeVia(via.getIdVia());
        } else {
            return "Algun dato esta mal";
        }


        if (existe == true) {
            return "Registrado";
        } else {
            return "error";
        }


    }

    @CrossOrigin
    @PostMapping("/EliminarVia")
    public String eliminarAgente(@RequestHeader("id_via") int idVia) {

        System.out.println(idVia);
        viaServicio.eliminarVia(idVia);

        boolean existe = viaServicio.existeVia(idVia);

        if (existe == true) {
            return "Eliminado";
        } else {
            return "error";
        }

    }

}
