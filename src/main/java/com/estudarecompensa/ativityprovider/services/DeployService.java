package com.estudarecompensa.ativityprovider.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IDeploy;
import com.estudarecompensa.ativityprovider.repositories.DeployRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DeployService implements IDeploy<List<DeployActivity>>{

    @Autowired
    private DeployRepository repository;

    @Override
    public List<DeployActivity> getAllDeployInstance() {
        List<DeployActivity> listFromRepository = repository.findAll();


       return listFromRepository;
    }
    
    @Override
    public boolean saveValues(DeployActivity activity)
    {
        repository.save(activity);
        return true;
    }
  
}