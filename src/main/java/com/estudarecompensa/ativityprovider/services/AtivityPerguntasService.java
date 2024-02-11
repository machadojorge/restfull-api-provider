package com.estudarecompensa.ativityprovider.services;

import java.io.IOException;
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


    @Override
    public List<AtivityPerguntas> findByStudentInstance(String value) {
        // TODO Auto-generated method stub
        List<AtivityPerguntas> recordFromRepository = repository.encontrarPorColunas(value);
        return recordFromRepository;
    }
    @Override
    public AtivityPerguntas findByativityStudent(String ativityID, String studentID) 
    {
        AtivityPerguntas recordFromRepository = repository.findByStudentAtivity(ativityID, studentID);

        return recordFromRepository;
    }


    @Override
    public boolean saveValues(AtivityPerguntas deployActivity) {
        repository.save(deployActivity);
        return true;
    }


    @Override
    public AtivityPerguntas update(Long id, AtivityPerguntas obj) {
        AtivityPerguntas entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }



    
  
    private void updateData(AtivityPerguntas entity, AtivityPerguntas obj) {

       

        // entity.setAtivity_instance(obj.getAtivity_instance());
        // entity.setStudent_id(obj.getStudent_id());
        // entity.setAcede_atividade(obj.getAcede_atividade());
        // entity.setAcede_atividade_info(obj.getAcede_atividade_info());
        // entity.setResponde_questoes_modulo(obj.getResponde_questoes_modulo());
        // entity.setRespostas_corretas(obj.getRespostas_corretas());
        // entity.setRespostas_erradas(obj.getRespostas_erradas());
        // entity.setPercentagem_acertos(obj.getPercentagem_acertos());
        // entity.setRecompensa(obj.getRecompensa());
        // entity.setRecompensa_nivel_1(obj.getRecompensa_nivel_1());
        // entity.setRecompensa_nivel_2(obj.getRecompensa_nivel_2());
        // entity.setConcluiu_modulo(obj.getConcluiu_modulo());

    }





   
    
}
