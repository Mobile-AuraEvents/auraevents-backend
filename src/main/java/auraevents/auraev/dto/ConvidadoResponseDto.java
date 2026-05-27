package auraevents.auraev.dto;

public record ConvidadoResponseDto(
        Long id,
        String cpf,
        String nome,
        String telefone,
        Long patrocinadorId,
        String patrocinadorNome
) {
}
