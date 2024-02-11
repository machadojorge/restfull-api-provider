package com.estudarecompensa.ativityprovider.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IConfigAnalyticsParams;
import com.estudarecompensa.ativityprovider.repositories.ConfigAnalyticsAtivityRepository;


@Service
public class ConfigAnalyticsParamsService implements IConfigAnalyticsParams<ConfigAnalyticsAtivity>{

    @Autowired
    private ConfigAnalyticsAtivityRepository repository;
    
    // Consulta à base de dados por todos os parametros analiticos para configurar
    // a atividade
    @Override
    public List<ConfigAnalyticsAtivity> getAllAnalyticsParams()
    {
       List<ConfigAnalyticsAtivity> listFromRepository = repository.findAll();
       return listFromRepository;
    }
}
