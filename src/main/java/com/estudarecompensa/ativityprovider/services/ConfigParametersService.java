package com.estudarecompensa.ativityprovider.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.ConfigParameters;
import com.estudarecompensa.ativityprovider.interfaces.IConfigParametersService;
import com.estudarecompensa.ativityprovider.repositories.ConfigParametersRepository;


@Service
public class ConfigParametersService implements IConfigParametersService<ConfigParameters> {
    
    @Autowired
    private ConfigParametersRepository repository;

    public List<ConfigParameters> getAllParameters()
    {
       List<ConfigParameters> listFromRepository = repository.findAll();
       return listFromRepository;

    }
}