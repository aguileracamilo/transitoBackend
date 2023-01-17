package prueba.quileia.paquetes.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class Via {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "idVia", length=10, nullable = false, unique = true)
    private int idVia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVia tipoVia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCalle tipoCalle;

    @Column(name = "numeroRuta", nullable = false)
    private int numeroRuta;
    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "nivelCongestion", nullable = false)
    private double nivelCongestion;


    @OneToMany(mappedBy = "viaAsignada")
    private List<Agente> agentes;





}
