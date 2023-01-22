package prueba.quileia.paquetes.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agente {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "codigo", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "experiencia", length = 150, nullable = false)
    private double experienciaAnios;
    @Column(name = "codigoSecretaria", length = 10, nullable = false)
    private String codigoSecretaria;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "viaAsignada", nullable = true, foreignKey = @ForeignKey(name = "agente_via_fk"))
    private Via viaAsignada;

    @JsonIgnore
    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL)
    private List<Historial> registrosAgente;

    @Override
    public String toString() {
        return "Agente [codigo=" + codigo + ", nombre=" + nombre + ", experienciaAnios=" + experienciaAnios + ", codigoSecretaria=" + codigoSecretaria + ", viaAsignadaId=" + (viaAsignada != null ? viaAsignada.getIdVia() : "null") + "]";
    }


}
