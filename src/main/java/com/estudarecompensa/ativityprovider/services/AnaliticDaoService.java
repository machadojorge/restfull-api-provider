package com.estudarecompensa.ativityprovider.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDaoService;
import com.estudarecompensa.ativityprovider.repositories.AnaliticDaoRepository;

@Service
public class AnaliticDaoService implements IAnaliticDaoService <AnaliticDao>{

    @Autowired
    private AnaliticDaoRepository repository;


      // Metodo que vai efetuar uma pesquisa dos registos na base de dados que tenham um determinado valor
    // Neste caso, vai pesquisar por ativity Deploy ID ou por Student ID
    @Override
    public List<AnaliticDao> findByativityInstance(String value) {
        List<AnaliticDao> listFromRepository = repository.encontrarPorColunas(value);

        return listFromRepository;
    }


    // Metodo que  vai efetuar uma pesquisa de acordo com duas condiçoes, 
    // o ativityID e o id do utilizador, devolvendo o registo analitico desse utilizador nessa atividade
    @Override
    public List<AnaliticDao> findByativityInstanceStudent(String instance, String Student) 
    {
        List<AnaliticDao> listFromRepository = repository.encontrarPorStudent(instance, Student);

        return listFromRepository;
    }


    // Este método vai guardar na base de dados os valores analiticos obtidos pelo utilizador
    // durante a atividade
    @Override
    public boolean saveValues(AnaliticDao deployActivity) {
        repository.save(deployActivity);
        return true;
    }

     // Este método vai atualizar os valores, obtem o registo existente na base de dados e chama
     // um metod para atualizar esses valores com os valores recebidos e volta a guardar esses valores na base de dados
     // Ainda não esta completo nem testado
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
