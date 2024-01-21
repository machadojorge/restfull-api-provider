package com.estudarecompensa.ativityprovider.interfaces;

import java.util.List;

import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public interface IAnaliticDaoService <T> {
 
    // Estes dois Métodos foram adicionados na interface AnaliticDaoRepository com as 
    // respetivas Query SQL.    
    public List<T> findByativityInstance(String value);
    public List<T> findByativityInstanceStudent(String instance, String Student);

    public boolean saveValues(AnaliticDao deployActivity);
    public AnaliticDao update(Long id, AnaliticDao obj);
}
    
