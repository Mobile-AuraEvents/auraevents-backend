package auraevents.auraev.controller;

import auraevents.auraev.dto.ConvidadoRequestDto;
import auraevents.auraev.dto.ConvidadoResponseDto;
import auraevents.auraev.service.ConvidadoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService convidadoService;

    @GetMapping("/patrocinadores/{patrocinadorId}/convidados")
    public List<ConvidadoResponseDto> listarPorPatrocinador(@PathVariable Long patrocinadorId) {
        return convidadoService.listarPorPatrocinador(patrocinadorId);
    }

    @PostMapping("/patrocinadores/{patrocinadorId}/convidados")
    public ResponseEntity<ConvidadoResponseDto> cadastrar(
            @PathVariable Long patrocinadorId,
            @Valid @RequestBody ConvidadoRequestDto dto
    ) {
        ConvidadoRequestDto request = new ConvidadoRequestDto(dto.cpf(), dto.nome(), dto.telefone(), patrocinadorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(convidadoService.cadastrar(request));
    }

    @PutMapping("/convidados/{convidadoId}")
    public ConvidadoResponseDto editar(
            @PathVariable Long convidadoId,
            @Valid @RequestBody ConvidadoRequestDto dto
    ) {
        return convidadoService.editar(convidadoId, dto);
    }

    @DeleteMapping("/convidados/{convidadoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long convidadoId) {
        convidadoService.excluir(convidadoId);
        return ResponseEntity.noContent().build();
    }
}
