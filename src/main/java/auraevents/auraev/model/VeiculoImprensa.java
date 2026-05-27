package auraevents.auraev.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoImprensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String razaoSocial;
    private String telefone;
    private String nomeResponsavel;

    @Enumerated(EnumType.STRING)
    private TipoVeiculoImprensa tipo;

    private String numero;

    @Enumerated(EnumType.STRING)
    private FrequenciaRadio frequencia;

    private String canal;
}
