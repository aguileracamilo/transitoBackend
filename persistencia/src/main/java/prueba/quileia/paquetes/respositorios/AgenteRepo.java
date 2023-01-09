package prueba.quileia.paquetes.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba.quileia.paquetes.entidades.Agente;

@Repository
public interface AgenteRepo extends JpaRepository<Agente,String> {
}
