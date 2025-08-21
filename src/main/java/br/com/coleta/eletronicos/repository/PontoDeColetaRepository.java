package br.com.coleta.eletronicos.repository;

import br.com.coleta.eletronicos.model.PontoDeColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List; // Import necessário

@Repository
public interface PontoDeColetaRepository extends JpaRepository<PontoDeColeta, Long> {

    /**
     * Busca pontos de coleta que aceitam um tipo de lixo eletrônico cujo nome (tipoDoLixo)
     * contém a string fornecida, de forma insensível ao caso.
     * @param name O texto a ser buscado no nome do lixo eletrônico.
     * @return Uma lista de PontoDeColeta distintos que atendem ao critério.
     */
    @Query("SELECT DISTINCT pc FROM PontoDeColeta pc " +
            "JOIN pc.materiaisAceitos ma " +
            "JOIN ma.lixoEletronico le " +
            "WHERE LOWER(le.tipoDoLixo) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<PontoDeColeta> findByLixoEletronicoNameContaining(@Param("name") String name);
}