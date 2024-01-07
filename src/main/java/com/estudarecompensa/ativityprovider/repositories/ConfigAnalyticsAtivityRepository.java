package com.estudarecompensa.ativityprovider.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;


public interface ConfigAnalyticsAtivityRepository extends JpaRepository<ConfigAnalyticsAtivity, Long>
{
     public List<ConfigAnalyticsAtivity> findAll();
}
