package prueba.quileia.paquetes.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prueba.quileia.paquetes.entidades.Historial;

import java.util.Date;
import java.util.List;


@Repository
public interface HistorialRepo extends JpaRepository<Historial, Integer> {

    @Query(value = "INSERT INTO historial (codigo_agente, id_via, fecha) VALUES(?1, ?2, ?3)", nativeQuery = true)
    void insertarPorIds(String codigoAgente, int idVia, Date fecha);

    @Query(value = "SELECT * FROM historial WHERE agente_id=?1", nativeQuery = true)
    List<Historial> findByAgente_Codigo(String codigoAgentes);

    @Query(value = "SELECT * FROM historial WHERE via_id=?1", nativeQuery = true)
    List<Historial> findByVia_id(int id);
}
