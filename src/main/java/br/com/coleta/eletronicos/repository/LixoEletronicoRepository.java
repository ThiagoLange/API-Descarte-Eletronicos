package br.com.coleta.eletronicos.repository;

import br.com.coleta.eletronicos.model.LixoEletronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LixoEletronicoRepository extends JpaRepository<LixoEletronico, Long> {
}