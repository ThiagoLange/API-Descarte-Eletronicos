package br.com.coleta.eletronicos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "materiais_ponto_coleta")
@Data
@NoArgsConstructor
public class MateriaisPontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponto_de_coleta_id", nullable = false)
    @JsonBackReference
    private PontoDeColeta pontoDeColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lixo_eletronico_id", nullable = false)
    private LixoEletronico lixoEletronico;

    @Column(name = "capacidade_maxima_kg")
    private double capacidadeMaxima;
}