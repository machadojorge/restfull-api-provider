package com.estudarecompensa.ativityprovider.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;


public interface DeployRepository extends JpaRepository<DeployActivity, Long> 
{
    public List<DeployActivity> findAll();

    // Metodo adicionado a esta interface para fazer consultas à base de dados por uma condição, o ID do deploy
     @Query("SELECT e FROM DeployActivity e WHERE e.intance_ativity = :valorColuna")
    List<DeployActivity> findByInstanceAtivity(@Param("valorColuna") String valorColuna);

    
}
