package br.com.coleta.eletronicos.model;

// IMPORT NECESSÁRIO
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lixo_eletronico")
@Data
@NoArgsConstructor
// ADICIONE ESTA ANOTAÇÃO AQUI
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LixoEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_lixo", nullable = false, length = 100)
    private String tipoDoLixo;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}