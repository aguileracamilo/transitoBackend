package prueba.quileia.paquetes.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prueba.quileia.paquetes.entidades.Agente;

import java.util.List;

@Repository
public interface AgenteRepo extends JpaRepository<Agente, String> {

    @Query(value = "delete from agente  where codigo= ?1 ", nativeQuery = true)
    void eliminarAgentePorCodigo(String codigo);

    @Query(value = "update Agente set via_asignada=?2   where codigo= ?1 ", nativeQuery = true)
    void actualizarVia(String codigo, int idVia);

    @Query(value = "update Agente set codigo=?2   where codigo= ?1 ", nativeQuery = true)
    void actualizarCodigo(String codigo, String codigoNuevo);

    @Query(value = "select codigo from  agente where via_asignada=?1 ", nativeQuery = true)
    List<String> traerAgentesVias(int id);

    @Query(value = "update agente set via_asignada=null where codigo = ?1", nativeQuery = true)
    void borrarViasAsignadas(String codigo);

    @Query(value = "select via_asignada from  agente where codigo=?1 ", nativeQuery = true)
    int traerIdVia(String codigo);

    @Query(value = "DELETE FROM historial where agente_id=?1", nativeQuery = true)
    void borrarHistorial(String codigo);

    @Query(value = "UPDATE historial SET agente_id=?1 where agente_id=?2", nativeQuery = true)
    void actualizarCodigoHistorial(String codigoNuevo, String codigoAnt);

}
