package prueba.quileia.paquetes.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    private String tipoVia;

    private String tipoCalle;

    private int numeroRuta;




}
