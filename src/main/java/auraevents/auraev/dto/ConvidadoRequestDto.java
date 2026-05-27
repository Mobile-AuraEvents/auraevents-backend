package auraevents.auraev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConvidadoRequestDto(
        @NotBlank String cpf,
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotNull Long patrocinadorId
) {
}
