package auraevents.auraev.dto;

import java.math.BigDecimal;

public record PatrocinioShowDto(
        Long id,
        Long patrocinadorId,
        String patrocinadorNome,
        BigDecimal valorPatrocinado
) {
}
