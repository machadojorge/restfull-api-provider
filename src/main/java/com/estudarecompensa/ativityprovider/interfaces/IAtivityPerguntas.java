package com.estudarecompensa.ativityprovider.interfaces;

import java.util.List;

import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;

public interface IAtivityPerguntas <T> {
 
    // Estes dois Métodos foram adicionados na interface AnaliticDaoRepository com as 
    // respetivas Query SQL.    
    public List<T> findByStudentInstance(String value);
    public AtivityPerguntas  findByativityStudent(String instance, String Student);

    public boolean saveValues(AtivityPerguntas deployActivity);
}
    
