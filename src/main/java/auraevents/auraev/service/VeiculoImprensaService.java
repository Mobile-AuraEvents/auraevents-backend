package auraevents.auraev.service;

import auraevents.auraev.dto.VeiculoImprensaDto;
import auraevents.auraev.model.VeiculoImprensa;
import auraevents.auraev.repository.VeiculoImprensaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoImprensaService {

    private final VeiculoImprensaRepository repository;

    public List<VeiculoImprensaDto> listarTodos() { return repository.findAll().stream().map(this::toDto).toList(); }
    public VeiculoImprensaDto buscarPorId(Long id) { return toDto(findById(id)); }
    public VeiculoImprensaDto criar(VeiculoImprensaDto dto) { return toDto(repository.save(toEntity(dto))); }

    public VeiculoImprensaDto atualizar(Long id, VeiculoImprensaDto dto) {
        VeiculoImprensa existente = findById(id);
        existente.setCnpj(dto.cnpj());
        existente.setRazaoSocial(dto.razaoSocial());
        existente.setTelefone(dto.telefone());
        existente.setNomeResponsavel(dto.nomeResponsavel());
        existente.setTipo(dto.tipo());
        existente.setNumero(dto.numero());
        existente.setFrequencia(dto.frequencia());
        existente.setCanal(dto.canal());
        return toDto(repository.save(existente));
    }

    public void excluir(Long id) { repository.delete(findById(id)); }

    private VeiculoImprensa findById(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo de imprensa nao encontrado")); }

    private VeiculoImprensa toEntity(VeiculoImprensaDto dto) {
        return VeiculoImprensa.builder().id(dto.id()).cnpj(dto.cnpj()).razaoSocial(dto.razaoSocial())
                .telefone(dto.telefone()).nomeResponsavel(dto.nomeResponsavel()).tipo(dto.tipo()).numero(dto.numero())
                .frequencia(dto.frequencia()).canal(dto.canal()).build();
    }

    private VeiculoImprensaDto toDto(VeiculoImprensa e) {
        return new VeiculoImprensaDto(e.getId(), e.getCnpj(), e.getRazaoSocial(), e.getTelefone(), e.getNomeResponsavel(),
                e.getTipo(), e.getNumero(), e.getFrequencia(), e.getCanal());
    }
}
