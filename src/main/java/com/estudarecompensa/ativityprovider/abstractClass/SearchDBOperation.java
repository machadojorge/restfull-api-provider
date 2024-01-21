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
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDaoService;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.ConfigParametersService;


public class SearchDBOperation<T, M> extends AbstractDBOperation {

    // Mapa com os metodos atribuidos a cada classe especifica
    // Faz um mapeamento entre uma string que identifica a instancia
    // do objeto e o metodo que deve ser chamado.
    // As chaves e os metodos vão ser adicionados quando for executado o método
    // createMethodMap()
    private Map<String, Method> methodMap = new HashMap<String, Method>();


    // Construtor passando apenas o Serviço ao qual se irá fazer a consulta à base de dados
    public SearchDBOperation(T service)  {
        super(service);
    }


    // Subrecarga do construtor para efetue Pesquisas de acorco com os parametros "M"
    // à respetiva classe de Serviço "T"
    // o M Poderá ser uma String ou um JSONObject
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
            // methodMap.put("getAllAnalitics", classeAtual.getMethod("getAllAnalitics"));
            // methodMap.put("getAllParams", classeAtual.getMethod("getAllParams"));
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
    // Chamar o método para realizar a consulta especifica
    @Override
    public JSONObject executeOperation(String value) {
       JSONObject result = new JSONObject();
       try 
       {
            result =  (JSONObject) methodMap.get(value).invoke(this);
       } 
       catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
       {
           e.printStackTrace();
       }     
       return result;
    }

   

    // Este método vai pesquisar por todos os parametros analiticos contidos na tabela
    // para fornecer ao inven!RA 
    public JSONObject getAllConfigAnalitics()
    {
        List<ConfigAnalyticsAtivity> analitics = ((ConfigAnalyticsParamsService) this.getService()).getAllAnalyticsParams();
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
       return finalJson;
    }

    // Este método vai pesquisar à base de dados por todos os Parametros de configuração de uma atividade
    public JSONObject getAllConfigParams()
    {
        List<ConfigParameters> analitics = ((ConfigParametersService) this.getService()).getAllParameters();
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


    // Este método Vai fazer uma pesquisa pelo Deploy Ativity ID e pelo Student ID, 
    // pesquisando pelos analiticos desse aluno nessa atividade
    public JSONObject StudentAnalitics()
    {
        // Vamos extrair os dois parametros do JSONObject do getObjectInstance
        JSONObject result = (JSONObject)this.getObjectInstance();
        String  ativityId = result.getString("InstanceID");
        String student_id = (String) result.get("StudentID");

        List<AnaliticDao> analitics = ((IAnaliticDaoService) this.getService()).findByativityInstanceStudent(ativityId, student_id);//.findByativity_student(ativityId,student_id);
       
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
        
        return jsonObject;
    } 
    
}


   
    

