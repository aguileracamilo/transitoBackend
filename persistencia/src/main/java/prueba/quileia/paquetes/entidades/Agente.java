package prueba.quileia.paquetes.entidades;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agente {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "codigo", length=10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", length =50 , nullable = false)
    private String nombre;

    @Column(name = "experiencia", length =150 , nullable = false)
    private double experienciaAnios;
    @Column(name = "codigoSecretaria", length=10, nullable = false)
    private String codigoSecretaria;

    @ManyToOne
    @JoinColumn(name = "viaAsignada",nullable = true)
    private Via viaAsignada;

    @Override
    public String toString() {
        return "Agente [codigo=" + codigo + ", nombre=" + nombre + ", experienciaAnios=" + experienciaAnios + ", codigoSecretaria=" + codigoSecretaria + ", viaAsignadaId=" + (viaAsignada != null ? viaAsignada.getIdVia() : "null") + "]";
    }


}
