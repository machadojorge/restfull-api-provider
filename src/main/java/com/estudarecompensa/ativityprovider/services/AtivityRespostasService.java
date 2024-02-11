package com.estudarecompensa.ativityprovider.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;

import com.estudarecompensa.ativityprovider.repositories.AtivityRespostasRepository;

@Service
public class AtivityRespostasService implements IAtivityRespostas <AtivityRespostas>{

    @Autowired
    private AtivityRespostasRepository repository;


    @Override
    public List<AtivityRespostas> findByStudentInstance(String value) {
        // TODO Auto-generated method stub
        List<AtivityRespostas> recordFromRepository = repository.encontrarPorColunas(value);
        return recordFromRepository;
    }
    @Override
    public AtivityRespostas findByativityStudent(String ativityID, String studentID) 
    {
        AtivityRespostas recordFromRepository = repository.findByStudentAtivity(ativityID, studentID);

        return recordFromRepository;
    }


    @Override
    public boolean saveValues(AtivityRespostas deployActivity) {
        repository.save(deployActivity);
        return true;
    }


    @Override
    public AtivityRespostas update(Long id, AtivityRespostas obj) {
        AtivityRespostas entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    @Override
    public List<AtivityRespostas> findAllRecords() {
        // TODO Auto-generated method stub
        List<AtivityRespostas> recordFromRepository = repository.findAll();
        return recordFromRepository;
    }

    
  
    private void updateData(AtivityRespostas entity, AtivityRespostas obj) {

       
            entity.setQuestions(obj.getQuestions());
        // entity.setAtivity_instance(obj.getAtivity_instance());
        // entity.setStudent_id(obj.getStudent_id());
        // entity.setAcede_atividade(obj.getAcede_atividade());
        // entity.setAcede_atividade_info(obj.getAcede_atividade_info());
        // entity.setResponde_questoes_modulo(obj.getResponde_questoes_modulo());
        // entity.setRespostas_corretas(obj.getRespostas_corretas());
        // entity.setRespostas_erradas(obj.getRespas_erradas());
        // entity.setPercentagem_acertos(obj.getPercentagem_acertos());
        // entity.setRecompensa(obj.getRecompensa());
        // entity.setRecompensa_nivel_1(obj.getRecompensa_nivel_1());
        // entity.setRecompensa_nivel_2(obj.getRecompensa_nivel_2());
        // entity.setConcluiu_modulo(obj.getConcluiu_modulo());

    }





   
    
}
