package br.com.coleta.eletronicos.controller;

import br.com.coleta.eletronicos.model.LixoEletronico;
import br.com.coleta.eletronicos.repository.LixoEletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lixo-eletronico")
public class LixoEletronicoController {

    @Autowired
    private LixoEletronicoRepository lixoEletronicoRepository;

    @PostMapping
    public ResponseEntity<LixoEletronico> criar(@RequestBody LixoEletronico lixoEletronico) {
        return ResponseEntity.ok(lixoEletronicoRepository.save(lixoEletronico));
    }

    @GetMapping
    public ResponseEntity<List<LixoEletronico>> listar() {
        return ResponseEntity.ok(lixoEletronicoRepository.findAll());
    }
}