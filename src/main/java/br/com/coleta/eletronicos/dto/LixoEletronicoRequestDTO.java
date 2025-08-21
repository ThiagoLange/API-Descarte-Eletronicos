package br.com.coleta.eletronicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LixoEletronicoRequestDTO {

    @NotBlank(message = "O tipo do lixo não pode ser vazio.")
    @Size(min = 3, max = 100, message = "O tipo do lixo deve ter entre 3 e 100 caracteres.")
    private String tipoDoLixo;

    private String descricao;
}