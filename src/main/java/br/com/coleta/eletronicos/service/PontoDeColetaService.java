package br.com.coleta.eletronicos.service;

import br.com.coleta.eletronicos.model.LixoEletronico;
import br.com.coleta.eletronicos.model.MateriaisPontoColeta;
import br.com.coleta.eletronicos.model.PontoDeColeta;
import br.com.coleta.eletronicos.repository.LixoEletronicoRepository;
import br.com.coleta.eletronicos.repository.PontoDeColetaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PontoDeColetaService {

    @Autowired
    private PontoDeColetaRepository pontoDeColetaRepository;

    @Autowired
    private LixoEletronicoRepository lixoEletronicoRepository;

    @Transactional
    public PontoDeColeta criarPontoDeColeta(PontoDeColeta pontoDeColeta) {
        // Se a lista de materiais for nula ou vazia, não há o que processar.
        if (pontoDeColeta.getMateriaisAceitos() == null || pontoDeColeta.getMateriaisAceitos().isEmpty()) {
            return pontoDeColetaRepository.save(pontoDeColeta);
        }

        // Itera sobre a cópia da lista para evitar problemas de modificação concorrente se necessário
        for (MateriaisPontoColeta material : pontoDeColeta.getMateriaisAceitos()) {
            // VERIFICAÇÃO DE NULIDADE para previnir o erro 500
            if (material.getLixoEletronico() == null || material.getLixoEletronico().getId() == null) {
                throw new IllegalArgumentException("Cada item em 'materiaisAceitos' deve conter um 'lixoEletronico' com um 'id' válido.");
            }

            // Garante a referência bidirecional
            material.setPontoDeColeta(pontoDeColeta);

            // Busca a entidade LixoEletronico COMPLETA do banco de dados
            Long lixoId = material.getLixoEletronico().getId();
            LixoEletronico lixoGerenciado = lixoEletronicoRepository.findById(lixoId)
                    .orElseThrow(() -> new EntityNotFoundException("Lixo Eletrônico com ID " + lixoId + " não encontrado."));

            // Substitui o objeto com apenas o ID pelo objeto completo gerenciado pelo JPA
            material.setLixoEletronico(lixoGerenciado);
        }

        // Salva o PontoDeColeta, e o cascade cuidará de salvar os MateriaisPontoColeta
        return pontoDeColetaRepository.save(pontoDeColeta);
    }
}