package prueba.quileia.paquetes.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Historial {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agente_id", foreignKey = @ForeignKey(name = "historial_agente_fk"))
    private Agente agente;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "via_id", foreignKey = @ForeignKey(name = "historial_via_fk"))
    private Via via;

    public Historial(Date fecha, Agente agente, Via via) {
        this.fecha = fecha;
        this.agente = agente;
        this.via = via;
    }
}
