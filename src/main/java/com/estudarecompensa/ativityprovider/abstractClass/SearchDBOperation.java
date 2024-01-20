package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.ConfigParameters;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.services.AnaliticDaoService;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.ConfigParametersService;
import com.estudarecompensa.ativityprovider.services.ParamsAtivityDaoService;

public class SearchDBOperation<T, M> extends AbstractDBOperation {

    // Mapa com os metodos atribuidos a cada classe especifica
    // deste modo, não é necessário andar a percorrer sempre a lista 
    // quando se identifica a instancia de uma classe
    // As chaves e os metodos vão ser adicionados no construtor
    private Map<String, Method> methodMap = new HashMap<String, Method>();


    // Construtor passando apenas o Serviço ao qual se irá fazer a consulta à base de dados
    public SearchDBOperation(T service)  {
        super(service);
    }


    // Subrecarga do construtor para efetue Pesquisas de acorco com os parametros "M"
    // à respetiva classe de Serviço "T"
    // o T Poderá ser uma String ou um JSONObject
    public SearchDBOperation(T service, M instance)  {
        super(service, instance);
    }


    // Este método subscreve o método da classe abstrata para criar o MAP que faz o mapeamento entre
    // as Chaves "Strings com o nome dos metodos" e os valores "implementação dos métodos"
    @Override
    protected void createMethodMap() {
        try{
            Class<?> classeAtual = this.getClass();
            methodMap.put("getAllConfigAnalitics", classeAtual.getMethod("getAllConfigAnalitics"));
            methodMap.put("getAllConfigParams", classeAtual.getMethod("getAllConfigParams"));
            methodMap.put("getAllAnalitics", classeAtual.getMethod("getAllAnalitics"));
            methodMap.put("getAllParams", classeAtual.getMethod("getAllParams"));
            methodMap.put("getStudentAnalitics", classeAtual.getMethod("getStudentAnalitics"));
            
        }
        catch(NoSuchMethodException e){
            System.out.println("Error NoSuchMethodException: " + e.getMessage());
        }
        catch (SecurityException e){
            System.out.println("Error SecurityException: " + e.getMessage());
        }
    }

    
    // Este método vai fazer a verificação da instancia, a que classe Service pertence
    // após verificar essa instancia, vai devolver a chave do metodo correspondente a essa classe
    // do mapa. Alguns Services Permitem pesquisas com condições parametro "M" 
    @Override
	protected String checkInstance() {
        if (this.getService() instanceof ConfigAnalyticsParamsService)
        {   System.out.println("Service: " + this.service);
            System.out.println("Instance of: ConfigAnalyticsParamsService");
            return "getAllConfigAnalitics";
        }

        if (this.getService() instanceof ConfigParametersService)
        {
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: ConfigParamsService");
            return "getAllConfigParams";

        }
        if (this.getService() instanceof AnaliticDaoService)
        {
            if (this.getObjectInstance() instanceof JSONObject)
            {
                System.out.println("É instancia ");
                return "getStudentAnalitics";
            }
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: AnaliticsAtivity");
            return "getAllAnalitics";

        }
        if (this.getService() instanceof ParamsAtivityDaoService)
        {
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: ParamsAtivityDao");
            return "getAllParams";
        }
        return null;
    }

    // Este método vais receber a chave do método correspondente no Mapa de métodos, a fim de,
    // Chamar o método para realizar a consulta especifica
    @Override
    public JSONObject executeOperation(String value) {
       JSONObject result = new JSONObject();
       try {
        result =  (JSONObject) methodMap.get(value).invoke(this);
        System.out.println("Vou devolver o result");
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }        
       return result;
    }

   

    // Este método vai pesquisar por todos os parametros analiticos contidos na tabela
    // para fornecer ao inven!RA 
    public JSONObject getAllConfigAnalitics()
    {
        List<ConfigAnalyticsAtivity> analitics = ((ConfigAnalyticsParamsService) this.getService()).getAllAnalyticsParams();
        System.out.println("List Returned --> " + analitics);
        JSONObject finalJson = new JSONObject();

        // criar Listas auxiliares para dados quantitativos e qualitativos
       List<JSONObject> quanlAnalytics = new ArrayList<JSONObject>();
       List<JSONObject> quantAnalytics = new ArrayList<JSONObject>();

        // Converter a lista para o objecto jSON, no formato desejado pronto a ser devolvido na resposta
          for(ConfigAnalyticsAtivity t : analitics)
        {
            JSONObject map = new JSONObject();
            map.put("name",t.getAttribute());
            map.put("type", t.getType());

            if (t.getTypeOfAnalyses() == 1)
            {
                quantAnalytics.add(map);
            }
            if(t.getTypeOfAnalyses() == 0)
            {
                quanlAnalytics.add(map);
            }   
        }
       finalJson.put("qualAnalytics", quanlAnalytics);
       finalJson.put("quatAnalytics", quantAnalytics);
       System.out.println("LISTAS: " + finalJson.toString());

       // Devolver o JSON pronto a ser adicionado à resposta ao Inven!RA
       return finalJson;

    }

    // Este método vai pesquisar à base de dados por todos os Parametros de configuração de uma atividade
    public JSONObject getAllConfigParams()
    {
        List<ConfigParameters> analitics = ((ConfigParametersService) this.getService()).getAllParameters();
        System.out.println("List Returned --> " + analitics);
        List<Map<String, String>> params = new ArrayList<Map<String, String>>();
        JSONObject result = new JSONObject();
        for(ConfigParameters t : analitics)
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name",t.getAttribute());
            map.put("type", t.getType());
            params.add(map);
        }
        result.put("params", params);
        return result;
    }


    public JSONObject getAllAnalitics()
    {
        List<AnaliticDao> analitics = ((AnaliticDaoService) this.getService()).findByativity_instance((String)this.getObjectInstance());
        System.out.println("List Returned ALL ANALITICS--> " + analitics);
        int i = 0;

        // // Criar duas listas 
        // List<String>ListName = new ArrayList<String>();
        // List<String>ListType = new ArrayList<String>();
        // AnaliticDao.returnList(ListName, ListType);
           JSONObject result = new JSONObject();

        //    for(AnaliticDao t : analitics)
        //    {
        //     Class<?> classeAtual = t.getClass();
        //     while (classeAtual != null) {
        //         for (Field campo : classeAtual.getDeclaredFields()) {
        //             campo.setAccessible(true); // Permite acessar campos privados
        //             String valor = (String) campo.get(objectInstance);
        //             valores.add(String.valueOf(valor));
        //         }
        //         classeAtual = classeAtual.getSuperclass();
        //     }
        // } catch (IllegalAccessException e) {
        //     e.printStackTrace();
        // }
        //     for (String name : ListName)
        //     {
        //     //     Map<String, String> map = new HashMap<String, String>();
        //     //     map.put("name",name);
        //     //     map.put("type", ListType.get(0));
        //     //     map.put("value", t.getgetType());
        //     //    Map<String, String> map = new HashMap<String, String>();
        //     //    map.put("name",t.getAttribute());
        //     //    map.put("type", t.getType());
        //     //    params.add(map);

        //        i++;
        //    }
       // }
        //    // Vai ter que haver uma logica qualquer para filtrar por <M> que é o ativity id (string)
        //    List<Map<String, String>> params = new ArrayList<Map<String, String>>();
        //    for(AnaliticDao t : analitics)
        //    {
        //     result.put("params", params);
        //    }
            
           return result;

    }


    // Este método vai fazer a pesquisa por Deploy Ativity ID
    // Vai pesquisar todos os valores dos parametros de configuração para 
    // esta instancia da atividade
    public JSONObject getAllParams()
    {
        return null;
        
    }


    // Este método Vai fazer uma pesquisa pelo Deploy Ativity ID e pelo Student ID, 
    // pesquisando pelos analiticos desse aluno nessa atividade
    public JSONObject getStudentAnalitics()
    {
        // Vamos extrair os dois parametros do JSONObject do getObjectInstance
        JSONObject result = (JSONObject)this.getObjectInstance();
        List<AnaliticDao> analitics = ((AnaliticDaoService) this.getService()).findByativity_student(result.getString("InstanceID"), result.getString("StudentID"));
        System.out.println("List Returned STUDENT --> " + analitics);
        JSONObject jsonObject = new JSONObject();
        for(AnaliticDao t : analitics)
        {
             // Usa reflexão para obter os campos da classe
            for (java.lang.reflect.Field campo : t.getClass().getDeclaredFields()) {
                try {
                    campo.setAccessible(true);
                    Object valor = campo.get(t);
                    jsonObject.put(campo.getName(), valor);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("JsonOBJECT-----> " + jsonObject.toString());
        return jsonObject;
    }
    
}


   
    

