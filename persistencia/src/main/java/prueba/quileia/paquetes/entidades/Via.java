package prueba.quileia.paquetes.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @JsonIgnore
    @Column(nullable = false)
    private TipoVia tipoVia;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    @Column(nullable = false)
    private TipoCalle tipoCalle;

    @Column(name = "numeroRuta", nullable = false)
    private int numeroRuta;

    @Column(name = "nivelCongestion", nullable = false)
    @Size(min = 0,max = 100, message = "El nivel de congestion es maximo 100")
    private double nivelCongestion;


    @OneToMany(mappedBy = "viaAsignada")
    private List<Agente> agentes;





}
