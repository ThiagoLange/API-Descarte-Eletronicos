package br.com.coleta.eletronicos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PontoDeColetaResponseDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String diaDeColeta;
    private List<MaterialAceitoResponseDTO> materiaisAceitos;
}