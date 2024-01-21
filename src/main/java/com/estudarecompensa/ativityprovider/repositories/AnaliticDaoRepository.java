package com.estudarecompensa.ativityprovider.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public interface AnaliticDaoRepository extends JpaRepository<AnaliticDao, Long> {
   
    // Metodos adicionados juntamente com as respetivas Querys para efetuar pesquisas personalizadas na Base de dados
    // Neste caso será realizada uma pesquisa por um valor em uma determinada coluna ("arivity_instance");
      @Query("SELECT e FROM AnaliticDao e WHERE e.ativity_instance = :valorColuna")
    List<AnaliticDao> encontrarPorColunas(@Param("valorColuna") String valorColuna);

    
    // Neste caso, será efetuada uma pesquisa por dois valores em duas colunas diferentes ("ativity_instance" e "student_id")
    @Query("SELECT e FROM AnaliticDao e WHERE e.ativity_instance = :valorColuna AND e.student_id = :valorColuna_")
    List<AnaliticDao> encontrarPorStudent(@Param("valorColuna") String valorColuna, @Param("valorColuna_") String valorColuna_);
    
}
