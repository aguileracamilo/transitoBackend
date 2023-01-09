package prueba.quileia.paquetes.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;
import prueba.quileia.paquetes.respositorios.ViaRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ViaTest {

    @Autowired
    private ViaRepo viaRepo;

    @Test
    public void registrarVia()
    {
        //se inicializa el administrador
        Via via = new Via();
        via.setIdVia(100);
        via.setTipoVia(TipoVia.AUTOPISTA);
        via.setTipoCalle(TipoCalle.CALLE);
        via.setNivelCongestion(10.5);


        //Guardamos el registro
        Via guardado = viaRepo.save(via);

        Assertions.assertNotNull(guardado);
    }

    @Test
    public void eliminarViarTest()
    {

        Via via = new Via();
        via.setIdVia(100);
        via.setTipoVia(TipoVia.AUTOPISTA);
        via.setTipoCalle(TipoCalle.CALLE);
        via.setNivelCongestion(10.5);                   //se define una contraseña

        //Guardamos el registro
        Via guardado = viaRepo.save(via);

        //Luego lo eliminamos
        viaRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Via buscado = viaRepo.findById(100).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void actualizarViaTest()
    {

        Via via = new Via();
        via.setIdVia(100);
        via.setTipoVia(TipoVia.AUTOPISTA);
        via.setTipoCalle(TipoCalle.CALLE);
        via.setNivelCongestion(10.5);

        //Guardamos el registro
        Via guardado = viaRepo.save(via);

        //Modificamos el nombre
       guardado.setTipoVia(TipoVia.CARRETERA_PRINCIPAL);

        //Con save guardamos el registro modificado
       viaRepo.save(guardado);

        Via buscado = viaRepo.findById(100).orElse(null);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(TipoVia.CARRETERA_PRINCIPAL,buscado.getTipoVia());
    }



}
