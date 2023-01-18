package prueba.quileia.paquetes.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prueba.quileia.paquetes.entidades.Via;

@Repository
public interface ViaRepo extends JpaRepository<Via,Integer> {
    @Query(value = "delete from via  where id_via= ?1 ", nativeQuery = true)
    void eliminarViaPorId(int codigo);
    @Query(value = "update Via set id_via=?2   where id_via= ?1 ", nativeQuery = true)
    void actualizarId(int id, int idNuevo);

}
