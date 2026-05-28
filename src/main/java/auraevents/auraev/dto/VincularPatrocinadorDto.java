package auraevents.auraev.dto;

import java.math.BigDecimal;

public record VincularPatrocinadorDto(
        Long patrocinadorId,
        BigDecimal valorPatrocinado
) {
}
