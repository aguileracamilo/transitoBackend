package prueba.quileia.paquetes.servicio;

import prueba.quileia.paquetes.entidades.Agente;

import java.util.List;

public interface AgenteServicio {

    List<Agente> enlistarAgentes();
    public void crearAgente(Agente agente);
    public boolean existeAgente(String codigoAgente);
    public void eliminarAgente(String codigo);
}
