package br.com.coleta.eletronicos.controller;

import br.com.coleta.eletronicos.dto.LixoEletronicoRequestDTO;
import br.com.coleta.eletronicos.dto.LixoEletronicoResponseDTO;
import br.com.coleta.eletronicos.service.LixoEletronicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/electronic-waste")
public class LixoEletronicoController {

    @Autowired
    private LixoEletronicoService service;

    @PostMapping
    public ResponseEntity<LixoEletronicoResponseDTO> create(@Valid @RequestBody LixoEletronicoRequestDTO requestDTO) {
        LixoEletronicoResponseDTO responseDTO = service.create(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LixoEletronicoResponseDTO>> findAll() {
        List<LixoEletronicoResponseDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LixoEletronicoResponseDTO> findById(@PathVariable Long id) {
        LixoEletronicoResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LixoEletronicoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody LixoEletronicoRequestDTO requestDTO) {
        LixoEletronicoResponseDTO updatedDto = service.update(id, requestDTO);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}