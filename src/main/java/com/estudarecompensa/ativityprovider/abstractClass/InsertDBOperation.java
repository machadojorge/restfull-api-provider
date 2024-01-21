package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDaoService;
import com.estudarecompensa.ativityprovider.services.DeployService;

public class InsertDBOperation<T, M> extends AbstractDBOperation {


     // Mapa que vai ser criado para mapear "Strings-> Metodos"
     // Este mapa vai ser criado em um metodo que é herdado e vai
     // conter os nomes dos metodos necessários para esta classe relacionados com 
     // Strings que identificam as classes de serviços concretas
    private Map<String, Method> methodMap = new HashMap<String, Method>();


    // É necessário passar sempre para o construtor o Objecto do Service e o Objeto
    // Da classe que vai ser para adicionar.
    public InsertDBOperation(T service, M instance)
    {
        super(service, instance);
    }


    // Metodo abstrato para criar o Mapa para mapear "Strings -> Methodos" nesta classe
    @Override
    protected void createMethodMap() {
        try{
            Class<?> classeAtual = this.getClass();
            methodMap.put("addInstanceAtivity", classeAtual.getMethod("addInstanceAtivity"));
            methodMap.put("StudentAnalitics", classeAtual.getMethod("StudentAnalitics"));      
        }
        catch(NoSuchMethodException e){
            System.out.println("Error NoSuchMethodException: " + e.getMessage());
        }
        catch (SecurityException e){
            System.out.println("Error SecurityException: " + e.getMessage());
        }
    }

    // Este método vais receber a chave do método correspondente no Mapa de métodos, a fim de,
    // Chamar o método para realizar a o insert na tabela especifica
    @Override
    public JSONObject executeOperation(String value) {
        JSONObject result = new JSONObject();
        try {
            result =  (JSONObject) methodMap.get(value).invoke(this);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
        return result;

    }

    // Metodo para inserir os IDs de novos deploys que tenham sido feitos, para manter um registo
    // dos deploys das atividades
    public JSONObject addInstanceAtivity()
    {
        JSONObject jsonObject = new JSONObject();
        String instanceAtivity = (String)this.getObjectInstance();
        List<DeployActivity> activities = ((DeployService) this.getService()).findByInstanceAtivity(instanceAtivity);
      
        if (activities.isEmpty())
        {
           boolean result =  ((DeployService) this.getService()).saveValues(new DeployActivity(instanceAtivity));
           if (result)
           {
               jsonObject.put("Save Record:", "True");
               return jsonObject;
           }
        }
        jsonObject.put("Save Record:", "False");
        return jsonObject;
    } 


    // Este metodo vai adicionar na tabela a lista de analiticos para este aluno nesta atividade
    public JSONObject StudentAnalitics()
    {
        JSONObject jsonObject = new JSONObject();
        String instanceAtivity = (String) ((JSONObject) this.getObjectInstance()).get("InstanceID");
        String instanceStudent = (String) ((JSONObject) this.getObjectInstance()).get("StudentID"); 
        boolean result =  ((IAnaliticDaoService) this.getService()).saveValues(new AnaliticDao(instanceAtivity, instanceStudent));
        if (result)
        {
            jsonObject.put("Save Record:", "True");
            System.out.println("Object Saved: " + jsonObject.toString());
            return jsonObject;
        }     
        jsonObject.put("Save Record:", "False");
        return jsonObject;
    }
}

    

