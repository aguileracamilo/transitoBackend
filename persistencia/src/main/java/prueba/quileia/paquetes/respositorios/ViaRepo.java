package prueba.quileia.paquetes.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.quileia.paquetes.entidades.Via;

@Repository
public interface ViaRepo extends JpaRepository<Via,Integer> {

}
