package br.com.coleta.eletronicos.controller;

import br.com.coleta.eletronicos.model.PontoDeColeta;
import br.com.coleta.eletronicos.repository.PontoDeColetaRepository;
import br.com.coleta.eletronicos.service.PontoDeColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos-coleta")
public class PontoDeColetaController {

    @Autowired
    private PontoDeColetaService pontoDeColetaService;

    @Autowired
    private PontoDeColetaRepository pontoDeColetaRepository;

    @PostMapping
    public ResponseEntity<PontoDeColeta> criarPonto(@RequestBody PontoDeColeta pontoDeColeta) {
        // Delega toda a lógica de criação para a camada de serviço
        PontoDeColeta novoPonto = pontoDeColetaService.criarPontoDeColeta(pontoDeColeta);
        return ResponseEntity.ok(novoPonto);
    }

    @GetMapping
    public ResponseEntity<List<PontoDeColeta>> listarTodos() {
        return ResponseEntity.ok(pontoDeColetaRepository.findAll());
    }
}