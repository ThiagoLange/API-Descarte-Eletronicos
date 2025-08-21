package br.com.coleta.eletronicos.service;

import br.com.coleta.eletronicos.dto.LixoEletronicoRequestDTO;
import br.com.coleta.eletronicos.dto.LixoEletronicoResponseDTO;
import br.com.coleta.eletronicos.exception.ResourceNotFoundException;
import br.com.coleta.eletronicos.mapper.LixoEletronicoMapper;
import br.com.coleta.eletronicos.model.LixoEletronico;
import br.com.coleta.eletronicos.repository.LixoEletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LixoEletronicoService {

    @Autowired
    private LixoEletronicoRepository repository;

    @Transactional
    public LixoEletronicoResponseDTO create(LixoEletronicoRequestDTO requestDTO) {
        LixoEletronico entity = LixoEletronicoMapper.toEntity(requestDTO);
        LixoEletronico savedEntity = repository.save(entity);
        return LixoEletronicoMapper.toResponseDTO(savedEntity);
    }

    @Transactional(readOnly = true)
    public List<LixoEletronicoResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(LixoEletronicoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LixoEletronicoResponseDTO findById(Long id) {
        LixoEletronico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lixo Eletrônico com id " + id + " não encontrado."));
        return LixoEletronicoMapper.toResponseDTO(entity);
    }

    @Transactional
    public LixoEletronicoResponseDTO update(Long id, LixoEletronicoRequestDTO requestDTO) {
        LixoEletronico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lixo Eletrônico com id " + id + " não encontrado."));

        entity.setTipoDoLixo(requestDTO.getTipoDoLixo());
        entity.setDescricao(requestDTO.getDescricao());
        LixoEletronico updatedEntity = repository.save(entity);

        return LixoEletronicoMapper.toResponseDTO(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lixo Eletrônico com id " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }
}