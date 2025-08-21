package br.com.coleta.eletronicos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LixoEletronicoResponseDTO {
    private Long id;
    private String tipoDoLixo;
    private String descricao;
}