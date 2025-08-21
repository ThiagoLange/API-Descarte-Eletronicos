package br.com.coleta.eletronicos.mapper;

import br.com.coleta.eletronicos.dto.LixoEletronicoRequestDTO;
import br.com.coleta.eletronicos.dto.LixoEletronicoResponseDTO;
import br.com.coleta.eletronicos.model.LixoEletronico;

public class LixoEletronicoMapper {

    public static LixoEletronico toEntity(LixoEletronicoRequestDTO dto) {
        LixoEletronico entity = new LixoEletronico();
        entity.setTipoDoLixo(dto.getTipoDoLixo());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }

    public static LixoEletronicoResponseDTO toResponseDTO(LixoEletronico entity) {
        LixoEletronicoResponseDTO dto = new LixoEletronicoResponseDTO();
        dto.setId(entity.getId());
        dto.setTipoDoLixo(entity.getTipoDoLixo());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }
}