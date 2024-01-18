package com.estudarecompensa.ativityprovider.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;

public interface DeployRepository extends JpaRepository<DeployActivity, Long> 
{
    public List<DeployActivity> findAll();

}
