package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.DTO.AgenteDTO;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.respositorios.AgenteRepo;


import java.util.*;

@Service
public class AgenteServicioImpl implements AgenteServicio {
    @Autowired
    private AgenteRepo agenteRepo;

    @Override
    public List<Agente> enlistarAgentes() {
        try {
            return agenteRepo.findAll();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Agente crearAgente(Agente a) {

        Via via = a.getViaAsignada();

        //La condición obliga estar completo para registrarse y opcionalmente a si se asignó vía que su congestión sea >= a 30
        try {
            if (estaCompleto(a) || (via != null && via.getNivelCongestion() >= 30.0 && estaCompleto(a))) {

                return agenteRepo.save(a);
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        //No cumple con las condiciones, no se actualiza y devuelve null
        return null;
    }

    public Agente actualizarAgente(Agente a, String codigoNuevo) {

        Via via = a.getViaAsignada();
        //Los dos siguientes son necesarios si se cambia el codigo
        String codigoAnt = "";
        Agente actualizado;

        if (estaCompleto(a) || (via != null && via.getNivelCongestion() >= 30.0 && estaCompleto(a))) {
            try {
                actualizado = agenteRepo.save(a);
                //Si código nuevo no es el mismo o no es vacío se hace un registro con el nuevo codigo
                //se actualiza el historial y se elimina el anterior registro
                if (!codigoNuevo.equals(a.getCodigo()) && !codigoNuevo.equals("")) {

                    codigoAnt = a.getCodigo();
                    a.setCodigo(codigoNuevo);
                    actualizado = agenteRepo.save(a);

                    agenteRepo.actualizarCodigoHistorial(codigoNuevo, codigoAnt);
                    eliminarAgente(codigoAnt);
                    return actualizado;
                } else {

                    return agenteRepo.save(a);
                }

            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }
        }
        //No cumple con las condiciones, no se actualiza y devuelve null
        return null;
    }

    //Verifica si el agente tiene los datos que no pueden ser nulos
    public boolean estaCompleto(Agente a) {

        if (!a.getNombre().equals("") && a.getExperienciaAnios() >= 0 && !a.getCodigo().equals("") && !a.getCodigoSecretaria().equals("")) {
            return true;
        }
        return false;
    }

    public boolean existeAgente(String codigoAgente) {

        try {
            return agenteRepo.existsById(codigoAgente);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void eliminarAgente(String codigo) {
        try {
            agenteRepo.borrarHistorial(codigo);
            agenteRepo.eliminarAgentePorCodigo(codigo);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Agente> obtenerAgentesPorCodigo(List<String> codigos) {

        List<Agente> agentes = new ArrayList<>();
        try {
            for (String codigo : codigos) {
                Agente agente = agenteRepo.getById(codigo);
                agentes.add(agente);
            }
            return agentes;
        } catch (Exception e) {

            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    //necesario para poder captar las los objetos foraneos para evitar errores
    public List<AgenteDTO> agenteToDTO(List<Agente> agentes) {
        List<AgenteDTO> agentesDTO = new ArrayList<>();
        for (Agente agente : agentes) {
            AgenteDTO agenteDTO = new AgenteDTO(agente);
            agentesDTO.add(agenteDTO);
        }
        return agentesDTO;
    }

    //Quita y asigna agente deacuerdo a la lista
    public void asignarAgentes(List<String> agentes, Via via) {

        if (via != null && via.getNivelCongestion() >= 30.0) {
            eliminarAsignacion(agentes, via.getIdVia());
            try {
                for (String codigo : agentes) {
                    agenteRepo.actualizarVia(codigo, via.getIdVia());
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    //Compara los asignados con la nueva lista para quitar los que ya no están en la nueva
    public void eliminarAsignacion(List<String> agentesAsignadosNuevo, int id) {
        try {
            List<String> agentesAsignados = agenteRepo.traerAgentesVias(id);

            for (String codigo : agentesAsignados) {
                if (!agentesAsignadosNuevo.contains(codigo)) {
                    agenteRepo.borrarViasAsignadas(codigo);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //Metodo encargado de devolver una lista de ids de las nuevas asignaciones
    public List<String> traerAsignadosNuevos(List<String> agentes, int idVia) {

        List<String> listaNueva = new ArrayList<String>();

        try {
            List<String> agentesAsignados = agenteRepo.traerAgentesVias(idVia);

            for (String codigo : agentes) {
                if (!agentesAsignados.contains(codigo)) {
                    listaNueva.add(codigo);
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return listaNueva;

    }

    public boolean esMismaAsignacion(String codigoAgente, int idVia) {

        if (agenteRepo.traerIdVia(codigoAgente) != idVia) {
            return true;
        } else {
            return false;
        }
    }
}
