package auraevents.auraev.service;

import auraevents.auraev.dto.ConvidadoRequestDto;
import auraevents.auraev.dto.ConvidadoResponseDto;
import auraevents.auraev.model.Convidado;
import auraevents.auraev.model.Patrocinador;
import auraevents.auraev.repository.ConvidadoRepository;
import auraevents.auraev.repository.PatrocinadorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvidadoService {

    private final ConvidadoRepository convidadoRepository;
    private final PatrocinadorRepository patrocinadorRepository;

    public List<ConvidadoResponseDto> listarPorPatrocinador(Long patrocinadorId) {
        return convidadoRepository.findByPatrocinadorId(patrocinadorId).stream().map(this::toResponse).toList();
    }

    public ConvidadoResponseDto cadastrar(ConvidadoRequestDto dto) {
        Patrocinador patrocinador = buscarPatrocinador(dto.patrocinadorId());
        Convidado convidado = Convidado.builder()
                .cpf(dto.cpf())
                .nome(dto.nome())
                .telefone(dto.telefone())
                .patrocinador(patrocinador)
                .build();
        return toResponse(convidadoRepository.save(convidado));
    }

    public ConvidadoResponseDto editar(Long convidadoId, ConvidadoRequestDto dto) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado nao encontrado"));
        Patrocinador patrocinador = buscarPatrocinador(dto.patrocinadorId());

        convidado.setCpf(dto.cpf());
        convidado.setNome(dto.nome());
        convidado.setTelefone(dto.telefone());
        convidado.setPatrocinador(patrocinador);

        return toResponse(convidadoRepository.save(convidado));
    }

    public void excluir(Long convidadoId) {
        Convidado convidado = convidadoRepository.findById(convidadoId)
                .orElseThrow(() -> new RuntimeException("Convidado nao encontrado"));
        convidadoRepository.delete(convidado);
    }

    private Patrocinador buscarPatrocinador(Long patrocinadorId) {
        return patrocinadorRepository.findById(patrocinadorId)
                .orElseThrow(() -> new RuntimeException("Patrocinador nao encontrado"));
    }

    private ConvidadoResponseDto toResponse(Convidado convidado) {
        return new ConvidadoResponseDto(
                convidado.getId(),
                convidado.getCpf(),
                convidado.getNome(),
                convidado.getTelefone(),
                convidado.getPatrocinador().getId(),
                convidado.getPatrocinador().getNome()
        );
    }
}
