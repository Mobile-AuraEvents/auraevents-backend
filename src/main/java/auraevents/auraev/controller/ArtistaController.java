package auraevents.auraev.controller;

import auraevents.auraev.dto.ArtistaDto;
import auraevents.auraev.service.ArtistaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artistas")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaService service;

    @GetMapping
    public List<ArtistaDto> listarTodos() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public ArtistaDto buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<ArtistaDto> criar(@RequestBody ArtistaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ArtistaDto atualizar(@PathVariable Long id, @RequestBody ArtistaDto dto) { return service.atualizar(id, dto); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
