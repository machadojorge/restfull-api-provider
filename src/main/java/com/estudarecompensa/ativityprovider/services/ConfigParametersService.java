package com.estudarecompensa.ativityprovider.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.ConfigParameters;
import com.estudarecompensa.ativityprovider.interfaces.IConfigParametersService;
import com.estudarecompensa.ativityprovider.repositories.ConfigParametersRepository;


@Service
public class ConfigParametersService implements IConfigParametersService<ConfigParameters> {
    
    @Autowired
    private ConfigParametersRepository repository;

    // Este método vai buscar os dados à base de dados
    @Override
    public ConfigParameters getAllParameters(ConfigParameters configParams)
    {
       List<ConfigParameters> listFromRepository = repository.findAll();
       this.addToMap(listFromRepository, configParams );

       return configParams;

    }

    // Este metodo vai criar o Map com o formato do JSON pretendido
    public void addToMap(List<ConfigParameters> dataFromDatabase, ConfigParameters configParams)
    {
        for(ConfigParameters t : dataFromDatabase)
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name",t.getAttribute());
            map.put("type", t.getType());
            configParams.addList(map);
        }
    }

}
