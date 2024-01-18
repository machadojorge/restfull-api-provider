package com.estudarecompensa.ativityprovider.interfaces;

import java.util.List;

import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public interface IAnaliticDao <T> {
 
    
    public List<T> getAllDeployInstance();

    public boolean saveValues(AnaliticDao deployActivity);
    
    public AnaliticDao update(Long id, AnaliticDao obj);
}
    
