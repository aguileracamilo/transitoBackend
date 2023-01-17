package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.quileia.paquetes.entidades.Agente;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.respositorios.ViaRepo;

import java.util.List;

@Service
public class ViaServicioImpl implements ViaServicio {
    @Autowired
    private ViaRepo viaRepo;

    public boolean existeVia(int codigoVia) {
        boolean existe = false;
        if (viaRepo.findById(codigoVia) != null) {
            existe = true;
        }
        return existe;

    }

    public void crearVia(Via via) {
        viaRepo.save(via);
    }

    @Override
    public List<Via> enlistarVias() {
        Via a = new Via();
        a.setIdVia(10);
        a.setNumeroRuta(20);
        a.setNivelCongestion(2.2);
        a.setTipoVia(TipoVia.CARRETERA_PRINCIPAL);
        a.setTipoCalle(TipoCalle.CALLE);
        viaRepo.save(a);
        return viaRepo.findAll();
    }

    public void eliminarVia(int idVia) {

        viaRepo.eliminarViaPorId(idVia);
    }
}
