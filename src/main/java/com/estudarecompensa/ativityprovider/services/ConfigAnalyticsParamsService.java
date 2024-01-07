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
    
    // Neste caso, é feita uma consulta à base de dados, pedindo os parametros Analiticos
    // Essa consulta é feita pelo metodo "findAll()" que devolve uma lista de Objectos do tipo 
    // "ConfigAnaliticsAtivity". Essa lista é devolvida à classe "JasonAdapter" (Classe Adapter do Padrão Adapter/Wrapper Pattern)
    // Que após receber, como resposta, esta lista, vai converte-la em um objecto JSON, no formato correto
    // (O formato que o Inve!RA espera), e devolve esse objecto ao Controller "ConfigManagerResources" que o Devolve, como 
    // resposta, ao pedido do Inven!RA.
    @Override
    public List<ConfigAnalyticsAtivity> getAllAnalyticsParams()
    {
       List<ConfigAnalyticsAtivity> listFromRepository = repository.findAll();
       return listFromRepository;
    }

  
 

}
