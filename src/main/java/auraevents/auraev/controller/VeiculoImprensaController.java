package auraevents.auraev.controller;

import auraevents.auraev.dto.VeiculoImprensaDto;
import auraevents.auraev.service.VeiculoImprensaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculos-imprensa")
@RequiredArgsConstructor
public class VeiculoImprensaController {

    private final VeiculoImprensaService service;

    @GetMapping
    public List<VeiculoImprensaDto> listarTodos() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public VeiculoImprensaDto buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<VeiculoImprensaDto> criar(@RequestBody VeiculoImprensaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public VeiculoImprensaDto atualizar(@PathVariable Long id, @RequestBody VeiculoImprensaDto dto) { return service.atualizar(id, dto); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
