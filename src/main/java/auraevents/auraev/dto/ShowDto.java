package auraevents.auraev.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ShowDto(
        Long id,
        LocalDate data,
        Long artistaId,
        Long casaDeShowId,
        List<PatrocinioShowDto> patrocinadores,
        List<Long> veiculosImprensaIds
) {
}
