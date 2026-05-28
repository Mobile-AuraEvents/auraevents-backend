package auraevents.auraev.service;

import auraevents.auraev.dto.ArtistaDto;
import auraevents.auraev.model.Artista;
import auraevents.auraev.repository.ArtistaRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistaService {

    private final ArtistaRepository repository;

    public List<ArtistaDto> listarTodos() { return repository.findAll().stream().map(this::toDto).toList(); }

    public ArtistaDto buscarPorId(Long id) { return toDto(findById(id)); }

    public ArtistaDto criar(ArtistaDto dto) { return toDto(repository.save(toEntity(dto))); }

    public ArtistaDto atualizar(Long id, ArtistaDto dto) {
        Artista existente = findById(id);
        existente.setNome(dto.nome());
        existente.setAssessorResponsavel(dto.assessorResponsavel());
        existente.setFotoUrl(dto.fotoUrl());
        existente.setTelefones(dto.telefones() == null ? new ArrayList<>() : new ArrayList<>(dto.telefones()));
        return toDto(repository.save(existente));
    }

    public void excluir(Long id) { repository.delete(findById(id)); }

    private Artista findById(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Artista nao encontrado")); }

    private Artista toEntity(ArtistaDto dto) {
        return Artista.builder().id(dto.id()).nome(dto.nome()).assessorResponsavel(dto.assessorResponsavel()).fotoUrl(dto.fotoUrl())
                .telefones(dto.telefones() == null ? new ArrayList<>() : new ArrayList<>(dto.telefones())).build();
    }

    private ArtistaDto toDto(Artista e) { return new ArtistaDto(e.getId(), e.getNome(), e.getAssessorResponsavel(), e.getFotoUrl(), e.getTelefones()); }
}
