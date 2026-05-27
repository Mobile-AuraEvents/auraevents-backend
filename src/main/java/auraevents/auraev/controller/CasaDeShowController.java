package auraevents.auraev.controller;

import auraevents.auraev.dto.CasaDeShowDto;
import auraevents.auraev.service.CasaDeShowService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/casas-de-show")
@RequiredArgsConstructor
public class CasaDeShowController {

    private final CasaDeShowService service;

    @GetMapping
    public List<CasaDeShowDto> listarTodos() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public CasaDeShowDto buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<CasaDeShowDto> criar(@RequestBody CasaDeShowDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public CasaDeShowDto atualizar(@PathVariable Long id, @RequestBody CasaDeShowDto dto) { return service.atualizar(id, dto); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
