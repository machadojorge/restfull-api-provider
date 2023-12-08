package com.estudarecompensa.ativityprovider.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.repositories.ConfigAnalyticsAtivityRepository;


@Service
public class ConfigAnalyticsParamsService {

    @Autowired
    private ConfigAnalyticsAtivityRepository repository;
    
    // Neste caso: 
    // 1 - parametros Quantitativos
    // 2 - parametros Qualitativos
    // Este método vai buscar os dados à base de dados
    public ConfigAnalyticsAtivity findAll(ConfigAnalyticsAtivity configParams)
    {
       List<ConfigAnalyticsAtivity> listFromRepository = repository.findAll();
       this.addToMap(listFromRepository, configParams );
       return configParams;
    }


    // Este metodo vai criar o Map com o formato do JSON pretendido. Primeiro adiciona às listas (qualitativo e quantitativo)
    // Depois adiciona essas listas a um Map.
    public void addToMap(List<ConfigAnalyticsAtivity> dataFromDatabase, ConfigAnalyticsAtivity configParams)
    {
     
        for(ConfigAnalyticsAtivity t : dataFromDatabase)
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name",t.getAttribute());
            map.put("type", t.getType());

            if (t.getTypeOfAnalyses() == 1)
            {
                configParams.addToQuantityList(map);
            }
            if(t.getTypeOfAnalyses() == 0)
            {
                configParams.addToQualityList(map);
            }   
        }
        configParams.addAnalyticsListToMap();
    }

}
