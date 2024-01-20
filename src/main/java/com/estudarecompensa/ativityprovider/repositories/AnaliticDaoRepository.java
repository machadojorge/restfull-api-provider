package com.estudarecompensa.ativityprovider.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public interface AnaliticDaoRepository extends JpaRepository<AnaliticDao, Long> {
    // List<AnaliticDao> findByativity_instance(String atividadeId);
      @Query("SELECT e FROM AnaliticDao e WHERE e.ativity_instance = :valorColuna")
    List<AnaliticDao> encontrarPorColunas(@Param("valorColuna") String valorColuna);//, @Param("valorColuna2") String valorColuna2);

    @Query("SELECT e FROM AnaliticDao e WHERE e.ativity_instance = :valorColuna AND e.student_id = :valorColuna_")
    List<AnaliticDao> encontrarPorStudent(@Param("valorColuna") String valorColuna, @Param("valorColuna_") String valorColuna_);
    
}
