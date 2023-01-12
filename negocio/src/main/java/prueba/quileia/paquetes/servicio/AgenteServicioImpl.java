package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.respositorios.AgenteRepo;
import prueba.quileia.paquetes.respositorios.ViaRepo;

import java.util.List;

@Service
public class AgenteServicioImpl implements AgenteServicio {
    @Autowired
    private AgenteRepo agenteRepo;
    @Override
    public List<Agente> enlistarAgentes() {
        return agenteRepo.findAll();
    }
    public void crearAgente(Agente agente){
        agenteRepo.save(agente);
    }
    public boolean existeAgente(String codigoAgente){
        boolean existe=false;
        if(agenteRepo.findById(codigoAgente)!=null) {
            existe = true;
        }
        return existe;

    }

    @Override
    public void eliminarAgente(String codigo) {

        agenteRepo.eliminarAgentePorCodigo(codigo);
    }
}
