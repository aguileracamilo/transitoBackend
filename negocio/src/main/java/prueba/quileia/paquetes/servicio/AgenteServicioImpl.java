package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.respositorios.AgenteRepo;
import prueba.quileia.paquetes.respositorios.ViaRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgenteServicioImpl implements AgenteServicio {
    @Autowired
    private AgenteRepo agenteRepo;

    @Override
    public List<Agente> enlistarAgentes() {

        return agenteRepo.findAll();
    }

    public void crearAgente(Agente agente, int idVia) {

        if (estaCompleto(agente)) {
            agenteRepo.save(agente);
            if (idVia != 0) {
                agenteRepo.actualizarVia(agente.getCodigo(), idVia);
            }
        }
    }

    public boolean estaCompleto(Agente a) {
        System.out.println(!a.getNombre().equals("") );
        if (!a.getNombre().equals("") && a.getExperienciaAnios() >= 0 && !a.getCodigo().equals("") && !a.getCodigoSecretaria().equals("")) {
            return true;
        }
        return false;

    }

    public void actualizarAgente(Agente agente, int idVia, String codigoNuevo) {
        if (estaCompleto(agente)) {
            agenteRepo.save(agente);
            if (idVia != 0) {
                agenteRepo.actualizarVia(agente.getCodigo(), idVia);
            }
            if (codigoNuevo != agente.getCodigo() && codigoNuevo != "") {

                agenteRepo.actualizarCodigo(agente.getCodigo(), codigoNuevo);
            }
        }
    }

    public boolean existeAgente(String codigoAgente) {

        return agenteRepo.existsById(codigoAgente);

    }

    @Override
    public void eliminarAgente(String codigo) {

        agenteRepo.eliminarAgentePorCodigo(codigo);
    }

    public List<AgenteDTO> agenteToDTO(List<Agente> agentes) {
        List<AgenteDTO> agentesDTO = new ArrayList<>();
        for (Agente agente : agentes) {
            AgenteDTO agenteDTO = new AgenteDTO(agente);
            agentesDTO.add(agenteDTO);
        }
        return agentesDTO;
    }
}
