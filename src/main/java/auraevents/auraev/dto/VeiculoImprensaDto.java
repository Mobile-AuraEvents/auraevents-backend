package auraevents.auraev.dto;

import auraevents.auraev.model.FrequenciaRadio;
import auraevents.auraev.model.TipoVeiculoImprensa;

public record VeiculoImprensaDto(
        Long id,
        String cnpj,
        String razaoSocial,
        String telefone,
        String nomeResponsavel,
        TipoVeiculoImprensa tipo,
        String numero,
        FrequenciaRadio frequencia,
        String canal
) {
}
