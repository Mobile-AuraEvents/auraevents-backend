package auraevents.auraev.service;

import auraevents.auraev.dto.CasaDeShowDto;
import auraevents.auraev.model.CasaDeShow;
import auraevents.auraev.repository.CasaDeShowRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CasaDeShowService {

    private final CasaDeShowRepository repository;

    public List<CasaDeShowDto> listarTodos() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public CasaDeShowDto buscarPorId(Long id) {
        return toDto(findById(id));
    }

    public CasaDeShowDto criar(CasaDeShowDto dto) {
        CasaDeShow saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    public CasaDeShowDto atualizar(Long id, CasaDeShowDto dto) {
        CasaDeShow existente = findById(id);
        existente.setNome(dto.nome());
        existente.setRua(dto.rua());
        existente.setNumero(dto.numero());
        existente.setBairro(dto.bairro());
        existente.setCidade(dto.cidade());
        existente.setUf(dto.uf());
        existente.setCapacidadeMaxima(dto.capacidadeMaxima());
        existente.setTelefone(dto.telefone());
        existente.setFotoUrl(dto.fotoUrl());
        return toDto(repository.save(existente));
    }

    public void excluir(Long id) {
        repository.delete(findById(id));
    }

    private CasaDeShow findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Casa de show nao encontrada"));
    }

    private CasaDeShow toEntity(CasaDeShowDto dto) {
        return CasaDeShow.builder()
                .id(dto.id())
                .nome(dto.nome())
                .rua(dto.rua())
                .numero(dto.numero())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .uf(dto.uf())
                .capacidadeMaxima(dto.capacidadeMaxima())
                .telefone(dto.telefone())
                .fotoUrl(dto.fotoUrl())
                .build();
    }

    private CasaDeShowDto toDto(CasaDeShow entity) {
        return new CasaDeShowDto(entity.getId(), entity.getNome(), entity.getRua(), entity.getNumero(), entity.getBairro(),
                entity.getCidade(), entity.getUf(), entity.getCapacidadeMaxima(), entity.getTelefone(), entity.getFotoUrl());
    }
}
