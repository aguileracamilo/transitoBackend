package prueba.quileia.paquetes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prueba.quileia.paquetes.entidades.TipoCalle;
import prueba.quileia.paquetes.entidades.TipoVia;
import prueba.quileia.paquetes.entidades.Via;

import prueba.quileia.paquetes.respositorios.ViaRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaServicioImpl implements ViaServicio {
    @Autowired
    private ViaRepo viaRepo;

    public boolean existeVia(int codigoVia) {

        try {
            return viaRepo.existsById(codigoVia);
        } catch (Exception e) {

            e.printStackTrace();
            return true;
        }

    }

    //Metodo para verificar si ya hay otra via que tiene esa direccion true para si existe ya otra via y false si no esta ocupada por otra
    public boolean existeNumeroVia(int idVia, int numeroVia, TipoCalle tipoCalle) {

        Via viaVerificar = viaRepo.buscarViaPorDireccion(numeroVia, tipoCalle.toString());

        try {
            //Si la via ya existe y existe una con esa direccion se valida si es la misma y entra
            if (viaRepo.existsById(idVia) && viaVerificar != null && viaVerificar.getIdVia() == idVia) {

                return false;
            } else if (viaVerificar != null) {
            //Se validó que no eran la misma via asi que si existe una con esa direccion se envia true y de lo contrario false

                return true;
            } else {

                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return true;
        }


    }

    public Via crearVia(Via via, List<String> agentes) {

        Via registroVia = null;

        try {
            registroVia = viaRepo.save(via);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return registroVia;
    }


    public Via actualizarVia(Via via, List<String> agentes, int idNueva) {

        Via registroVia;
        int idAnt;
        //Si id nueva no es el mismo o no es vacío se hace un registro con el nuevo codigo
        // se actualiza las asignaciones, se actualiza el historial y se elimina el anterior registro
        if (idNueva != via.getIdVia() && idNueva != 0) {
            idAnt = via.getIdVia();
            via.setIdVia(idNueva);

            registroVia = viaRepo.save(via);

            viaRepo.actualizarCodigoAsignacion(idNueva, idAnt);
            viaRepo.actualizarIdViaHistorial(idAnt, idNueva);
            viaRepo.actualizarId(via.getIdVia(), idNueva);
            viaRepo.eliminarViaPorId(idAnt);
            return registroVia;


        } else {

            return viaRepo.save(via);
        }
    }


    //Verifica si la vía tiene los datos que no pueden ser nulos
    public boolean estaCompleto(Via v) {

        if (v.getTipoVia() == null || v.getTipoCalle() == null || v.getIdVia() <= 0 || v.getNumeroRuta() <= 0 || v.getNivelCongestion() < 0 || v.getNivelCongestion() > 100) {
            return false;
        }
        return true;
    }


    @Override
    public List<Via> enlistarVias() {

        try {
            return viaRepo.findAll();
        } catch (Exception e) {

            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void eliminarVia(int idVia) {

        try {
            viaRepo.quitarAsignacionAgente(idVia);
            viaRepo.borrarHistorial(idVia);
            viaRepo.eliminarViaPorId(idVia);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //Para devolver el tipo de enum correcto
    @Override
    public TipoVia getTipoVia(String tipo) {
        TipoVia tipoViaEnum = null;
        switch (tipo) {
            case "opcion1":
                tipoViaEnum = TipoVia.AUTOPISTA;
                break;
            case "opcion2":
                tipoViaEnum = TipoVia.CARRETERA_PRINCIPAL;
                break;
            case "opcion3":
                tipoViaEnum = TipoVia.CARRETERA_SECUNDARIA;
                break;
        }
        return tipoViaEnum;
    }

    //Para devolver el tipo de enum correcto
    @Override
    public TipoCalle getTipoCalle(String tipo) {

        TipoCalle tipoCalleEnum = null;

        switch (tipo) {

            case "opcion1":
                tipoCalleEnum = TipoCalle.CALLE;
                break;
            case "opcion2":
                tipoCalleEnum = TipoCalle.CARRERA;
                break;
        }
        return tipoCalleEnum;
    }

    @Override
    public Via traerViaPorId(int idVia) {

        //valida si la via existe y la trae
        try {
            if (viaRepo.existsById(idVia)) {
                return viaRepo.getById(idVia);
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
        return null;
    }
    public int traerIdViaPorDireccion( int numeroRuta,String tipoCalle){
        Via via= viaRepo.buscarViaPorDireccion(numeroRuta,tipoCalle);
        if(via!=null){
            return via.getIdVia();
        }
        return 0;
    }

}
