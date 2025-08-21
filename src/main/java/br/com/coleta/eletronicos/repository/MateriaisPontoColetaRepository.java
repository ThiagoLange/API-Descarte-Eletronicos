package br.com.coleta.eletronicos.repository;

import br.com.coleta.eletronicos.model.MateriaisPontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaisPontoColetaRepository extends JpaRepository<MateriaisPontoColeta, Long> {
}