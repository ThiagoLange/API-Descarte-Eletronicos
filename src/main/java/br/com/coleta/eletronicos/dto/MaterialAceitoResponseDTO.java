package br.com.coleta.eletronicos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialAceitoResponseDTO {
    private Long id;
    private LixoEletronicoResponseDTO lixoEletronico; // Reutilizando o DTO que já temos!
    private double capacidadeMaxima;
}