package br.com.coleta.eletronicos.repository;

import br.com.coleta.eletronicos.model.PontoDeColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoDeColetaRepository extends JpaRepository<PontoDeColeta, Long> {
}