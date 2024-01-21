package com.estudarecompensa.ativityprovider.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.interfaces.IDeploy;
import com.estudarecompensa.ativityprovider.repositories.DeployRepository;



@Service
public class DeployService implements IDeploy<List<DeployActivity>>{

    @Autowired
    private DeployRepository repository;

    // Metodo para pesquisar por todos os registo na tabela
    @Override
    public List<DeployActivity> getAllDeployInstance() {
        List<DeployActivity> listFromRepository = repository.findAll();
       return listFromRepository;
    }
    

    // Metodo para guardar um registo na tabela, que é passado por parametro
    // neste caso, um objeto do tipo DeployActivity
    @Override
    public boolean saveValues(DeployActivity activity)
    {
        try{
              repository.save(activity);
            return true;
        }
        catch (DataAccessException e)
        {
            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    // Metodo personalizado para fazer uma pesquisa por ID da atividade
    public List<DeployActivity> findByInstanceAtivity(String valorColuna)
    {
        List<DeployActivity> listFromRepository = repository.findByInstanceAtivity(valorColuna);

        return listFromRepository;
        
    }
  
}