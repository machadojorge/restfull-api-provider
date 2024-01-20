package com.estudarecompensa.ativityprovider.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDao;
import com.estudarecompensa.ativityprovider.repositories.AnaliticDaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnaliticDaoService implements IAnaliticDao <AnaliticDao>{

    @Autowired
    private AnaliticDaoRepository repository;


    public List<AnaliticDao> findByativity_instance(String valorColuna)
    {
        List<AnaliticDao> listFromRepository = repository.encontrarPorColunas(valorColuna);

        return listFromRepository;
        
    }

    public List<AnaliticDao> findByativity_student(String valorColuna, String valorColuna_)
    {
        List<AnaliticDao> listFromRepository = repository.encontrarPorStudent(valorColuna, valorColuna_);

        return listFromRepository;
        
    }
    @Override
    public List<AnaliticDao> getAllDeployInstance() {
        List<AnaliticDao> listFromRepository = repository.findAll();


       return listFromRepository;
    }

    @Override
    public boolean saveValues(AnaliticDao deployActivity) {
        repository.save(deployActivity);
        return true;
    }

     
    @Override
    public AnaliticDao update(Long id, AnaliticDao obj)
    {
        AnaliticDao entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(AnaliticDao entity, AnaliticDao obj) {

       

        entity.setAtivity_instance(obj.getAtivity_instance());
        entity.setStudent_id(obj.getStudent_id());
        entity.setAcede_atividade(obj.getAcede_atividade());
        entity.setAcede_atividade_info(obj.getAcede_atividade_info());
        entity.setResponde_questoes_modulo(obj.getResponde_questoes_modulo());
        entity.setRespostas_corretas(obj.getRespostas_corretas());
        entity.setRespostas_erradas(obj.getRespostas_erradas());
        entity.setPercentagem_acertos(obj.getPercentagem_acertos());
        entity.setRecompensa(obj.getRecompensa());
        entity.setRecompensa_nivel_1(obj.getRecompensa_nivel_1());
        entity.setRecompensa_nivel_2(obj.getRecompensa_nivel_2());
        entity.setConcluiu_modulo(obj.getConcluiu_modulo());

    }
    

}
