package com.estudarecompensa.ativityprovider.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.repositories.AtivityPerguntasRepository;

@Service
public class AtivityPerguntasService implements IAtivityPerguntas <AtivityPerguntas>{

    @Autowired
    private AtivityPerguntasRepository repository;
   // Método para pesquisar na por questões por um ID da atividade
    @Override
    public List<AtivityPerguntas> findByStudentInstance(String value) {
        List<AtivityPerguntas> recordFromRepository = repository.encontrarPorColunas(value);
        return recordFromRepository;
    }

    // Método para pesquisar na por questões por um ID de utilizador e por um ID da atividade
    @Override
    public AtivityPerguntas findByativityStudent(String ativityID, String studentID) 
    {
        AtivityPerguntas recordFromRepository = repository.findByStudentAtivity(ativityID, studentID);
        return recordFromRepository;
    }

    // Método para guardar na tabela da BD as questões para uma atividade
    @Override
    public boolean saveValues(AtivityPerguntas deployActivity) {
        repository.save(deployActivity);
        return true;
    }
    
}
