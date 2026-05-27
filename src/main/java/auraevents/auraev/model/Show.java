package auraevents.auraev.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @ManyToOne(optional = false)
    @JoinColumn(name = "casa_de_show_id")
    private CasaDeShow casaDeShow;

    @ManyToMany
    @JoinTable(
            name = "show_veiculo_imprensa",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "veiculo_imprensa_id")
    )
    @Builder.Default
    private Set<VeiculoImprensa> veiculosImprensa = new HashSet<>();
}
