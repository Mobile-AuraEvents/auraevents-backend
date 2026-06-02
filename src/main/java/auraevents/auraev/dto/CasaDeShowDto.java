package auraevents.auraev.dto;

import java.util.List;

public record CasaDeShowDto(
        Long id,
        String nome,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String uf,
        Integer capacidadeMaxima,
        String telefone,
        String fotoUrl
) {
}
