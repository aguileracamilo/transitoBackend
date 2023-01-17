package prueba.quileia.paquetes.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import prueba.quileia.paquetes.entidades.Agente;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgenteDTO {

    private String codigo;
    private String nombre;
    private double experienciaAnios;
    private String codigoSecretaria;
    private int viaAsignada;



    public AgenteDTO(Agente agente) {
        this.codigo = agente.getCodigo();
        this.nombre = agente.getNombre();
        this.experienciaAnios = agente.getExperienciaAnios();
        this.codigoSecretaria = agente.getCodigoSecretaria();
        if(agente.getViaAsignada()!=null){
            this.viaAsignada= agente.getViaAsignada().getIdVia();
        }else{
            this.viaAsignada=0;
        }

    }


}
