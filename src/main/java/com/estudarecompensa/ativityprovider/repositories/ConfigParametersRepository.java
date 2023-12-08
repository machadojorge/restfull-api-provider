package com.estudarecompensa.ativityprovider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estudarecompensa.ativityprovider.entities.ConfigParameters;

public interface ConfigParametersRepository extends JpaRepository<ConfigParameters, Long> 
{
    
}
