package br.com.coleta.eletronicos.mapper;

import br.com.coleta.eletronicos.dto.MaterialAceitoResponseDTO;
import br.com.coleta.eletronicos.dto.PontoDeColetaResponseDTO;
import br.com.coleta.eletronicos.model.MateriaisPontoColeta;
import br.com.coleta.eletronicos.model.PontoDeColeta;

import java.util.stream.Collectors;

public class PontoDeColetaMapper {

    public static PontoDeColetaResponseDTO toResponseDTO(PontoDeColeta entity) {
        PontoDeColetaResponseDTO dto = new PontoDeColetaResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(entity.getEndereco());
        dto.setDiaDeColeta(entity.getDiaDeColeta());

        if (entity.getMateriaisAceitos() != null) {
            dto.setMateriaisAceitos(entity.getMateriaisAceitos().stream()
                    .map(PontoDeColetaMapper::toMaterialAceitoResponseDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private static MaterialAceitoResponseDTO toMaterialAceitoResponseDTO(MateriaisPontoColeta materialEntity) {
        MaterialAceitoResponseDTO materialDTO = new MaterialAceitoResponseDTO();
        materialDTO.setId(materialEntity.getId());
        materialDTO.setCapacidadeMaxima(materialEntity.getCapacidadeMaxima());
        // Aqui usamos o mapper que já existe para LixoEletronico!
        if (materialEntity.getLixoEletronico() != null) {
            materialDTO.setLixoEletronico(LixoEletronicoMapper.toResponseDTO(materialEntity.getLixoEletronico()));
        }
        return materialDTO;
    }
}