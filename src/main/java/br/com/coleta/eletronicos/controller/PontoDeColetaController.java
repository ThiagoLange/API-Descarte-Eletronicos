package br.com.coleta.eletronicos.controller;

import br.com.coleta.eletronicos.dto.PontoDeColetaRequestDTO;
import br.com.coleta.eletronicos.dto.PontoDeColetaResponseDTO;
import br.com.coleta.eletronicos.service.PontoDeColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/collection-points")
public class PontoDeColetaController {

    @Autowired
    private PontoDeColetaService service;

    @PostMapping
    public ResponseEntity<PontoDeColetaResponseDTO> create(@Valid @RequestBody PontoDeColetaRequestDTO requestDTO) {
        PontoDeColetaResponseDTO responseDTO = service.create(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PontoDeColetaResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoDeColetaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PontoDeColetaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PontoDeColetaRequestDTO requestDTO) {
        return ResponseEntity.ok(service.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}