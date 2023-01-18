package prueba.quileia.paquetes.servicio;

import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;

import java.util.List;

public interface ViaServicio {
    List<Via> enlistarVias();

    boolean existeVia(int codigoVia);

    void crearVia(Via via);

    void eliminarVia(int idVia);

    TipoVia getTipoVia(String tipo);
    TipoCalle getTipoCalle(String tipo);
}
