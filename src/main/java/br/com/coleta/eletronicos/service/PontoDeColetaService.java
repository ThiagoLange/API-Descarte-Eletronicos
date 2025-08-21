package br.com.coleta.eletronicos.service;

import br.com.coleta.eletronicos.dto.PontoDeColetaRequestDTO;
import br.com.coleta.eletronicos.dto.PontoDeColetaResponseDTO;
import br.com.coleta.eletronicos.exception.ResourceNotFoundException;
import br.com.coleta.eletronicos.mapper.PontoDeColetaMapper;
import br.com.coleta.eletronicos.model.LixoEletronico;
import br.com.coleta.eletronicos.model.MateriaisPontoColeta;
import br.com.coleta.eletronicos.model.PontoDeColeta;
import br.com.coleta.eletronicos.repository.LixoEletronicoRepository;
import br.com.coleta.eletronicos.repository.PontoDeColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoDeColetaService {

    @Autowired
    private PontoDeColetaRepository pontoDeColetaRepository;

    @Autowired
    private LixoEletronicoRepository lixoEletronicoRepository;

    @Transactional
    public PontoDeColetaResponseDTO create(PontoDeColetaRequestDTO requestDTO) {
        PontoDeColeta entity = new PontoDeColeta();
        entity.setNome(requestDTO.getNome());
        entity.setEndereco(requestDTO.getEndereco());
        entity.setDiaDeColeta(requestDTO.getDiaDeColeta());

        requestDTO.getMateriaisAceitos().forEach(materialDTO -> {
            LixoEletronico lixo = lixoEletronicoRepository.findById(materialDTO.getLixoEletronicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lixo Eletrônico com ID " + materialDTO.getLixoEletronicoId() + " não encontrado."));
            MateriaisPontoColeta material = new MateriaisPontoColeta();
            material.setLixoEletronico(lixo);
            material.setCapacidadeMaxima(materialDTO.getCapacidadeMaxima());
            material.setPontoDeColeta(entity); // Link bidirecional
            entity.getMateriaisAceitos().add(material);
        });

        PontoDeColeta savedEntity = pontoDeColetaRepository.save(entity);
        return PontoDeColetaMapper.toResponseDTO(savedEntity);
    }

    @Transactional(readOnly = true)
    public List<PontoDeColetaResponseDTO> findAll() {
        return pontoDeColetaRepository.findAll().stream()
                .map(PontoDeColetaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PontoDeColetaResponseDTO findById(Long id) {
        PontoDeColeta entity = pontoDeColetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de Coleta com id " + id + " não encontrado."));
        return PontoDeColetaMapper.toResponseDTO(entity);
    }

    @Transactional
    public PontoDeColetaResponseDTO update(Long id, PontoDeColetaRequestDTO requestDTO) {
        PontoDeColeta entity = pontoDeColetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de Coleta com id " + id + " não encontrado."));

        entity.setNome(requestDTO.getNome());
        entity.setEndereco(requestDTO.getEndereco());
        entity.setDiaDeColeta(requestDTO.getDiaDeColeta());

        entity.getMateriaisAceitos().clear();

        requestDTO.getMateriaisAceitos().forEach(materialDTO -> {
            LixoEletronico lixo = lixoEletronicoRepository.findById(materialDTO.getLixoEletronicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lixo Eletrônico com ID " + materialDTO.getLixoEletronicoId() + " não encontrado."));
            MateriaisPontoColeta material = new MateriaisPontoColeta();
            material.setLixoEletronico(lixo);
            material.setCapacidadeMaxima(materialDTO.getCapacidadeMaxima());
            material.setPontoDeColeta(entity);
            entity.getMateriaisAceitos().add(material);
        });

        PontoDeColeta updatedEntity = pontoDeColetaRepository.save(entity);
        return PontoDeColetaMapper.toResponseDTO(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        if (!pontoDeColetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ponto de Coleta com id " + id + " não encontrado.");
        }
        pontoDeColetaRepository.deleteById(id);
    }

    // NOVO MÉTODO ADICIONADO
    @Transactional(readOnly = true)
    public List<PontoDeColetaResponseDTO> findByLixoEletronicoName(String name) {
        List<PontoDeColeta> entities = pontoDeColetaRepository.findByLixoEletronicoNameContaining(name);
        return entities.stream()
                .map(PontoDeColetaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}