package br.com.coleta.eletronicos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ponto_de_coleta")
@Data
@NoArgsConstructor
public class PontoDeColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(name = "dia_coleta", length = 50)
    private String diaDeColeta;

    @OneToMany(mappedBy = "pontoDeColeta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MateriaisPontoColeta> materiaisAceitos = new ArrayList<>();
}