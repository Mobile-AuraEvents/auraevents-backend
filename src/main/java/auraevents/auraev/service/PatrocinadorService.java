package auraevents.auraev.service;

import auraevents.auraev.dto.PatrocinadorDto;
import auraevents.auraev.model.Patrocinador;
import auraevents.auraev.repository.PatrocinadorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatrocinadorService {

    private final PatrocinadorRepository repository;

    public List<PatrocinadorDto> listarTodos() { return repository.findAll().stream().map(this::toDto).toList(); }
    public PatrocinadorDto buscarPorId(Long id) { return toDto(findById(id)); }
    public PatrocinadorDto criar(PatrocinadorDto dto) { return toDto(repository.save(toEntity(dto))); }

    public PatrocinadorDto atualizar(Long id, PatrocinadorDto dto) {
        Patrocinador existente = findById(id);
        existente.setNome(dto.nome());
        existente.setCnpj(dto.cnpj());
        existente.setTelefone(dto.telefone());
        return toDto(repository.save(existente));
    }

    public void excluir(Long id) { repository.delete(findById(id)); }

    private Patrocinador findById(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Patrocinador nao encontrado")); }

    private Patrocinador toEntity(PatrocinadorDto dto) {
        return Patrocinador.builder().id(dto.id()).nome(dto.nome()).cnpj(dto.cnpj()).telefone(dto.telefone()).build();
    }

    private PatrocinadorDto toDto(Patrocinador e) { return new PatrocinadorDto(e.getId(), e.getNome(), e.getCnpj(), e.getTelefone()); }
}
