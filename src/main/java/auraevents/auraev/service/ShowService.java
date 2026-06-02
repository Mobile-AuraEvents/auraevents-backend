package auraevents.auraev.service;

import auraevents.auraev.dto.PatrocinioShowDto;
import auraevents.auraev.dto.ShowDto;
import auraevents.auraev.dto.VincularPatrocinadorDto;
import auraevents.auraev.dto.VincularVeiculoImprensaDto;
import auraevents.auraev.model.*;
import auraevents.auraev.repository.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final ArtistaRepository artistaRepository;
    private final CasaDeShowRepository casaDeShowRepository;
    private final PatrocinadorRepository patrocinadorRepository;
    private final VeiculoImprensaRepository veiculoImprensaRepository;
    private final PatrocinioShowRepository patrocinioShowRepository;

    public ShowDto cadastrar(ShowDto dto) {
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new RuntimeException("Artista nao encontrado"));
        CasaDeShow casaDeShow = casaDeShowRepository.findById(dto.casaDeShowId())
                .orElseThrow(() -> new RuntimeException("Casa de show nao encontrada"));

        Show show = Show.builder()
                .data(dto.data())
                .artista(artista)
                .casaDeShow(casaDeShow)
                .build();

        Show saved = showRepository.save(show);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<ShowDto> listar() {
        return showRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ShowDto buscarPorId(Long id) {
        return toDto(findShow(id));
    }

    public ShowDto editar(Long id, ShowDto dto) {
        Show show = findShow(id);
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new RuntimeException("Artista nao encontrado"));
        CasaDeShow casaDeShow = casaDeShowRepository.findById(dto.casaDeShowId())
                .orElseThrow(() -> new RuntimeException("Casa de show nao encontrada"));

        show.setData(dto.data());
        show.setArtista(artista);
        show.setCasaDeShow(casaDeShow);

        return toDto(showRepository.save(show));
    }

    public void excluir(Long id) {
        Show show = findShow(id);
        List<PatrocinioShow> patrocinadores = patrocinioShowRepository.findByShowId(id);
        patrocinioShowRepository.deleteAll(patrocinadores);
        showRepository.delete(show);
    }

    public ShowDto vincularPatrocinador(Long showId, VincularPatrocinadorDto dto) {
        Show show = findShow(showId);
        Patrocinador patrocinador = patrocinadorRepository.findById(dto.patrocinadorId())
                .orElseThrow(() -> new RuntimeException("Patrocinador nao encontrado"));

        PatrocinioShow patrocinioShow = PatrocinioShow.builder()
                .show(show)
                .patrocinador(patrocinador)
                .valorPatrocinado(dto.valorPatrocinado())
                .build();

        patrocinioShowRepository.save(patrocinioShow);
        return toDto(show);
    }

    public ShowDto vincularVeiculoImprensa(Long showId, VincularVeiculoImprensaDto dto) {
        Show show = findShow(showId);
        VeiculoImprensa veiculo = veiculoImprensaRepository.findById(dto.veiculoImprensaId())
                .orElseThrow(() -> new RuntimeException("Veiculo de imprensa nao encontrado"));

        show.getVeiculosImprensa().add(veiculo);
        return toDto(showRepository.save(show));
    }

    private Show findShow(Long id) {
        return showRepository.findById(id).orElseThrow(() -> new RuntimeException("Show nao encontrado"));
    }

    private ShowDto toDto(Show show) {
        List<PatrocinioShowDto> patrocinadores = patrocinioShowRepository.findByShowId(show.getId()).stream()
                .map(p -> new PatrocinioShowDto(p.getId(), p.getPatrocinador().getId(), p.getPatrocinador().getNome(), p.getValorPatrocinado()))
                .toList();

        List<Long> veiculos = show.getVeiculosImprensa().stream()
                .map(VeiculoImprensa::getId)
                .collect(Collectors.toList());

        return new ShowDto(show.getId(), show.getData(), show.getArtista().getId(), show.getCasaDeShow().getId(), patrocinadores, veiculos);
    }
}
