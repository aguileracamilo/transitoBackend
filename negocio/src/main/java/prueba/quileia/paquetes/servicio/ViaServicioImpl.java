package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.respositorios.AgenteRepo;
import prueba.quileia.paquetes.respositorios.ViaRepo;

import java.util.List;

@Service
public class ViaServicioImpl implements ViaServicio {
    @Autowired
    private ViaRepo viaRepo;

    @Autowired
    private AgenteRepo agenteRepo;

    public boolean existeVia(int codigoVia) {

        return viaRepo.existsById(codigoVia);

    }

    public void crearVia(Via via,List<String> agentes) {
        viaRepo.save(via);

        if(!agentes.isEmpty()&&via.getNivelCongestion()>=30){
            asignarAgentes(agentes,via.getIdVia());
        }
    }
    public void asignarAgentes(List<String> agentes,int idVia){
        for(String codigo:agentes){
            agenteRepo.actualizarVia(codigo,idVia);
        }
    }
    public void actualizarAgente(Via via, List<String> agentes, int idNueva) {
            viaRepo.save(via);
             eliminarAsignacion(agentes,via.getIdVia());
            if (!agentes.isEmpty()&&via.getNivelCongestion()>=30) {
                asignarAgentes(agentes,via.getIdVia());
            }
            if (idNueva != via.getIdVia() && idNueva != 0) {

                viaRepo.actualizarId(via.getIdVia(), idNueva);
            }


    }
    public void eliminarAsignacion(List<String> agentesAsignadosNuevo,int id){

        List<String> agentesAsignados = agenteRepo.traerAgentesVias(id);

        for(String codigo: agentesAsignados){
            if(!agentesAsignadosNuevo.contains(codigo)){
                agenteRepo.borrarViasAsignadas(codigo);
            }
        }
        System.out.println(agentesAsignados);

    }

    @Override
    public List<Via> enlistarVias() {

        return viaRepo.findAll();
    }

    public void eliminarVia(int idVia) {

        viaRepo.eliminarViaPorId(idVia);
    }

    @Override
    public TipoVia getTipoVia(String tipo) {
        TipoVia tipoViaEnum = null;
        switch (tipo) {
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
        return tipoViaEnum;
    }

    @Override
    public TipoCalle getTipoCalle(String tipo) {
        TipoCalle tipoCalleEnum = null;
        switch (tipo) {
            case "opcion1":
                tipoCalleEnum = TipoCalle.CALLE;
                break;
            case "opcion2":
                tipoCalleEnum = TipoCalle.CARRERA;
                break;
        }
        return tipoCalleEnum;
    }
}
