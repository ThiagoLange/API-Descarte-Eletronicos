package br.com.coleta.eletronicos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialAceitoRequestDTO {

    @NotNull(message = "O ID do lixo eletrônico não pode ser nulo.")
    private Long lixoEletronicoId;

    @Positive(message = "A capacidade máxima deve ser um valor positivo.")
    private double capacidadeMaxima;
}