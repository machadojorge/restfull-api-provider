package com.estudarecompensa.ativityprovider.interfaces;

import java.util.List;

import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;

public interface IAtivityRespostas <T> {
 
    // Estes dois Métodos foram adicionados na interface AnaliticDaoRepository com as 
    // respetivas Query SQL.    
    public List<T> findByStudentInstance(String value);
    public AtivityRespostas  findByativityStudent(String instance, String Student);

    public boolean saveValues(AtivityRespostas deployActivity);
    public AtivityRespostas update(Long id, AtivityRespostas obj);
    public List<T> findAllRecords();
}
    
