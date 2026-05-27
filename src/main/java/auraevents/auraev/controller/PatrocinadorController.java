package auraevents.auraev.controller;

import auraevents.auraev.dto.PatrocinadorDto;
import auraevents.auraev.service.PatrocinadorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrocinadores")
@RequiredArgsConstructor
public class PatrocinadorController {

    private final PatrocinadorService service;

    @GetMapping
    public List<PatrocinadorDto> listarTodos() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public PatrocinadorDto buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<PatrocinadorDto> criar(@RequestBody PatrocinadorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public PatrocinadorDto atualizar(@PathVariable Long id, @RequestBody PatrocinadorDto dto) { return service.atualizar(id, dto); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
