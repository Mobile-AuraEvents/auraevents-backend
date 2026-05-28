package auraevents.auraev.dto;

import java.util.List;

public record ArtistaDto(
        Long id,
        String nome,
        String assessorResponsavel,
        String fotoUrl,
        List<String> telefones
) {
}
