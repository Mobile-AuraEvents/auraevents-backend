package auraevents.auraev.controller;

import auraevents.auraev.dto.ShowDto;
import auraevents.auraev.dto.VincularPatrocinadorDto;
import auraevents.auraev.dto.VincularVeiculoImprensaDto;
import auraevents.auraev.service.ShowService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<ShowDto> cadastrar(@RequestBody ShowDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.cadastrar(dto));
    }

    @GetMapping
    public List<ShowDto> listar() {
        return showService.listar();
    }

    @GetMapping("/{id}")
    public ShowDto buscarPorId(@PathVariable Long id) {
        return showService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ShowDto editar(@PathVariable Long id, @RequestBody ShowDto dto) {
        return showService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        showService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/patrocinadores")
    public ShowDto vincularPatrocinador(@PathVariable Long id, @RequestBody VincularPatrocinadorDto dto) {
        return showService.vincularPatrocinador(id, dto);
    }

    @PostMapping("/{id}/veiculos-imprensa")
    public ShowDto vincularVeiculo(@PathVariable Long id, @RequestBody VincularVeiculoImprensaDto dto) {
        return showService.vincularVeiculoImprensa(id, dto);
    }
}
