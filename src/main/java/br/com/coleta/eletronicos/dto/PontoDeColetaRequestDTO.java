package br.com.coleta.eletronicos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PontoDeColetaRequestDTO {

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O endereço não pode ser vazio.")
    private String endereco;

    private String diaDeColeta;

    @Valid // Essencial para validar os objetos aninhados na lista
    @NotEmpty(message = "A lista de materiais aceitos não pode ser vazia.")
    private List<MaterialAceitoRequestDTO> materiaisAceitos;
}