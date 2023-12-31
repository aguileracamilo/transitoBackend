package prueba.quileia.paquetes.restController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import prueba.quileia.paquetes.DTO.HistorialDTO;
import prueba.quileia.paquetes.entidades.Historial;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.servicio.AgenteServicio;
import prueba.quileia.paquetes.servicio.HistorialServicio;
import prueba.quileia.paquetes.servicio.ViaServicio;

import java.util.List;

@RestController
public class HistorialRestController {


    @Autowired
    private HistorialServicio historialServicio;

    @Autowired
    private  ViaServicio viaServicio;


    @CrossOrigin
    @GetMapping("/AgenteHistorial")
    public List<HistorialDTO> enviarHistorialAgentes(@RequestHeader("codigo") String codigoAgente) {
        List<HistorialDTO> a = historialServicio.historialToDTO(historialServicio.obtenerHistorialAgente(codigoAgente));

        return a;
    }

    @CrossOrigin
    @GetMapping("/ViaHistorial")
    public List<HistorialDTO> enviarHistorialVias(@RequestHeader("tipo_calle") String tipoCalle,@RequestHeader("numero_ruta") int numeroRuta) {
        int  idVia;

        if(tipoCalle.equalsIgnoreCase("CALLE")||tipoCalle.equalsIgnoreCase("CARRERA")){
            idVia=viaServicio.traerIdViaPorDireccion(numeroRuta,tipoCalle);

            return historialServicio.historialToDTO(historialServicio.obtenerHistorialVia(idVia));
        }
            return null;
    }

    @CrossOrigin
    @GetMapping("/ViaHistorialPorId")
    public List<HistorialDTO> enviarHistorialViaPorId(@RequestHeader("id_via") int idVia) {

            return historialServicio.historialToDTO(historialServicio.obtenerHistorialVia(idVia));
    }
}
