package prueba.quileia.paquetes.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prueba.quileia.paquetes.entidades.Via;

@Repository
public interface ViaRepo extends JpaRepository<Via, Integer> {
    @Query(value = "delete from via  where id_via= ?1 ", nativeQuery = true)
    void eliminarViaPorId(int codigo);

    @Query(value = "update Via set id_via=?2   where id_via= ?1 ", nativeQuery = true)
    void actualizarId(int id, int idNuevo);

    @Query(value = "DELETE FROM historial where via_id=?1", nativeQuery = true)
    void borrarHistorial(int idVia);

    @Query(value = "UPDATE agente set via_asignada=null where via_asignada = ?1", nativeQuery = true)
    void quitarAsignacionAgente(int idVia);

    @Query(value = "UPDATE agente SET via_asignada=?1 where via_asignada = ?2", nativeQuery = true)
    void actualizarCodigoAsignacion(int idNuevo, int idAnt);

    @Query(value = "UPDATE historial SET via_id=?1 where via_id=?2", nativeQuery = true)
    void actualizarIdViaHistorial(int idNuevo, int idAnt);


}
