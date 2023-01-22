package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.DTO.HistorialDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.Historial;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.respositorios.HistorialRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HistorialServicioImpl implements HistorialServicio {

    @Autowired
    HistorialRepo historialRepo;

    //Da la fecha actual en formato año mes dia y hora
    public Date enviarFechaActual() {
        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int hour = calendario.get(Calendar.HOUR_OF_DAY);
        calendario.set(year, month, day, hour, 0, 0);
        return calendario.getTime();
    }

    public String fechaAString(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH");
        String fechaString = formato.format(fecha);
        return fechaString;
    }

    //Metodo para registrar asignacion en el historial
    public void registrarAsignacion(Via via, Agente agente) {
        //Si uno es null es porque no existe entonces no se registra
        if (via != null && agente != null && via.getNivelCongestion() >= 30.0) {
            Date fecha = enviarFechaActual();
            Historial registro = new Historial(fecha, agente, via);
            historialRepo.save(registro);
        }
    }

    public void registrarAsignacionAgentes(Via via, List<Agente> agentes) {
        //Si uno es null es porque no existe entonces no se registra
        Date fecha = enviarFechaActual();

        for (Agente agente : agentes) {
            registrarAsignacion(via, agente);
        }
    }

    public List<Historial> obtenerHistorialAgente(String codigoAgente) {

        return historialRepo.findByAgente_Codigo(codigoAgente);
    }

    public List<Historial> obtenerHistorialVia(int idVia) {

        return historialRepo.findByVia_id(idVia);
    }

    //necesario para poder captar las los objetos foraneos para evitar errores
    public List<HistorialDTO> historialToDTO(List<Historial> historiales) {

        List<HistorialDTO> historialDTO = new ArrayList<>();

        for (Historial historial : historiales) {
            HistorialDTO registroDTO = new HistorialDTO(historial);
            historialDTO.add(registroDTO);
        }
        return historialDTO;
    }
}
