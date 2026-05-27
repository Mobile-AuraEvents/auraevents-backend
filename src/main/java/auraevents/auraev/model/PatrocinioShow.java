package auraevents.auraev.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrocinioShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patrocinador_id")
    private Patrocinador patrocinador;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorPatrocinado;
}
