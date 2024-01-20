package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.services.AnaliticDaoService;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.DeployService;
import com.estudarecompensa.ativityprovider.utils.CheckExist;

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
            methodMap.put("addAtivityQuestions", classeAtual.getMethod("addAtivityQuestions"));
            methodMap.put("addStudentAnalitics", classeAtual.getMethod("addStudentAnalitics"));
            methodMap.put("updateStudentAnalitics", classeAtual.getMethod("updateStudentAnalitics"));        
        }
        catch(NoSuchMethodException e){
            System.out.println("Error NoSuchMethodException: " + e.getMessage());
        }
        catch (SecurityException e){
            System.out.println("Error SecurityException: " + e.getMessage());
        }
    }


    @Override
    protected String checkInstance() {
      
        if(this.getService() instanceof DeployService)
        {
            System.out.println("Instance of: DeployService");
            return "addInstanceAtivity";
        }

        if(this.getService() instanceof AnaliticDaoService)
        {
            System.out.println("Instance of: AnaliticDaoService");
            if (this.getObjectInstance() instanceof JSONObject)
            {
                return "updateStudentAnalitics";
            }
            return "addStudentAnalitics";
        }
       // Falta adicionar uma tabela e um metodo
        return null;
    }

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

  
 
    protected boolean checkRecord()
    {
        List<DeployActivity> activities = (List<DeployActivity>) ((DeployService) this.getService()).getAllDeployInstance();
        if(!CheckExist.existInDatabase(activities, ((DeployActivity) this.getObjectInstance()).getintance_ativity()))
        {
            System.out.println( "Ativities: !" + activities);
            boolean result = ((DeployService)this.getService()).saveValues((DeployActivity)this.getObjectInstance());
            System.out.println( "Registo guardado code: " + result);
            return result;
        }
        
        return false;
        // if(this.getService() instanceof DeployService)
        // {
        // List<DeployActivity> activities = (List<DeployActivity>) ((DeployService) this.getService()).getAllDeployInstance();
        // if(!CheckExist.existInDatabase(activities, ((DeployActivity) this.getObjectInstance()).getintance_ativity()))
        // {
        //    return true;
        // }
        // }
        // return false;
    }

   

    // @Override
    // public JSONObject executeOperation(String value) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'executeOperation'");
    // }


    
    
}
