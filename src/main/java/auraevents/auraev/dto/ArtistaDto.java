package auraevents.auraev.dto;

import java.util.List;

public record ArtistaDto(
        Long id,
        String nome,
        String assessorResponsavel,
        List<String> telefones
) {
}
