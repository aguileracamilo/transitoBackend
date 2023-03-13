package prueba.quileia.paquetes.servicio;

import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;

import java.util.List;

public interface ViaServicio {
    List<Via> enlistarVias();

    boolean existeVia(int codigoVia);

    Via crearVia(Via via, List<String> agentes);

    void eliminarVia(int idVia);

    TipoVia getTipoVia(String tipo);

    TipoCalle getTipoCalle(String tipo);

    Via actualizarVia(Via via, List<String> agentes, int idNueva);

    Via traerViaPorId(int idVia);

    //Verifica si el agente tiene los datos que no pueden ser nulos
    boolean estaCompleto(Via v);

    boolean existeNumeroVia(int idVia, int numeroVia, TipoCalle tipoCalle);

    int traerIdViaPorDireccion(int numeroRuta, String tipoCalle);
}
