package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.adapter.JsonAdapter;
import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDaoService;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.DeployService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.JSONParser;

import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IJson;
public class UpdateDBOperation<T, M> extends AbstractDBOperation {

    private Map<String, Method> methodMap = new HashMap<String, Method>();


    // É necessário passar sempre para o construtor o Objecto do Service e o Objeto
    // Da classe que vai ser para adicionar.
    public UpdateDBOperation(T service, M instance)
    {
        super(service, instance);
    }


    // Metodo abstrato para criar o Mapa para mapear "Strings -> Methodos" nesta classe
    @Override
    protected void createMethodMap() {
        try{
            Class<?> classeAtual = this.getClass();
            methodMap.put("addPerguntas", classeAtual.getMethod("addPerguntas"));      
            methodMap.put("addRespostas", classeAtual.getMethod("addRespostas")); 
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

    // // Metodo para inserir os IDs de novos deploys que tenham sido feitos, para manter um registo
    // // dos deploys das atividades
    // public JSONObject addInstanceAtivity()
    // {
    //     JSONObject jsonObject = new JSONObject();
    //     String instanceAtivity = (String)this.getObjectInstance();
    //     List<DeployActivity> activities = ((DeployService) this.getService()).findByInstanceAtivity(instanceAtivity);
      
    //     if (activities.isEmpty())
    //     {
    //        boolean result =  ((DeployService) this.getService()).saveValues(new DeployActivity(instanceAtivity));
    //        if (result)
    //        {
    //            jsonObject.put("Save Record:", "True");
    //            return jsonObject;
    //        }
    //     }
    //     jsonObject.put("Save Record:", "False");
    //     return jsonObject;
    // } 


    // // Este metodo vai adicionar na tabela a lista de analiticos para este aluno nesta atividade
    // public JSONObject StudentAnalitics()
    // {
    //     JSONObject jsonObject = new JSONObject();
    //     String instanceAtivity = (String) ((JSONObject) this.getObjectInstance()).get("InstanceID");
    //     String instanceStudent = (String) ((JSONObject) this.getObjectInstance()).get("StudentID"); 
    //     boolean result =  ((IAnaliticDaoService) this.getService()).saveValues(new AnaliticDao(instanceAtivity, instanceStudent));
    //     if (result)
    //     {
    //         jsonObject.put("Save Record:", "True");
    //         System.out.println("Object Saved: " + jsonObject.toString());
    //         return jsonObject;
    //     }     
    //     jsonObject.put("Save Record:", "False");
    //     return jsonObject;
    // }

    /**
     * Este método é responsavel por veriricar se já existe registo das perguntas 
     * para esta atividade e adicionar caso não haja
     * @return Um JSON com dois parametros, {"status":"true"} caso tenha sucesso ou {"status":"false"} caso nao tenha 
     * 
     */
    public JSONObject addPerguntas()
    {
        System.out.println("-------------------------------");
        AtivityPerguntas apDB = new AtivityPerguntas();
        String instanceAtivity = (String) ((Map) this.getObjectInstance()).get("activityID");
        String instanceStudent = (String) ((Map) this.getObjectInstance()).get("Inven!RAstdID");
       
        System.out.println("####################################");
        System.out.println(instanceAtivity + "   ---   " + instanceStudent);
        JSONObject result = new JSONObject();
        System.out.println("####################################");
        apDB = ((IAtivityPerguntas)this.getService()).findByativityStudent(instanceAtivity, instanceStudent);
        System.out.println("3333333333");
        //System.out.println("OBJDB = " + apDB.toString());
        if (apDB != null)
        {
            System.out.println("OBJDB = " + apDB.toString());
            System.out.println(apDB.getQuestions());
            //importante
            String newString = apDB.getQuestions().replace('=', ':');
            JSONObject objtemp = new JSONObject(newString);
            System.out.println("Question: " + objtemp.get("s1"));
         
            // JSONObject obj = new JSONObject(objtemp.get("questions"));
            // String cast = obj.get("questions").toString();
            // System.out.println(cast);
            // System.out.println("\\\\\\ Questions: " + obj.toString());
            // System.out.println("JSONObject = " + objtemp.toString());

            // Map<String, Object> map = cast.get
            // System.out.println("MAP -> " + (String)map.get("s1"));


       

            result.put("status", "true");
            return result;
        }
        
        System.out.println("vamos adicionar - - - - ");
        // Caso não exista registo:
        System.out.println("Type: " + this.getObjectInstance().getClass().getName());
        Map<String, String> data = (Map<String, String>)((Map)this.getObjectInstance()).get("json_params");
        
        JSONObject obj = new JSONObject(data);
        
        System.out.println("data: " + data.toString());
       
        AtivityPerguntas perguntasObj = new AtivityPerguntas(instanceStudent, instanceAtivity, data.toString());
        try{
            ((IAtivityPerguntas)this.getService()).saveValues(perguntasObj);
        }
        catch (Exception e)
        {
            System.out.println("Error to save the values: " + e.getMessage());
        }
        return result;
    
    }
    public JSONObject addRespostas()
    {
        System.out.println("------------------------------------------------------------------------------------------------------");
        AtivityRespostas apDB = new AtivityRespostas();
        String instanceAtivity = (String) ((AtivityRespostas) this.getObjectInstance()).getAtivity_id();
        String instanceStudent = (String) ((AtivityRespostas) this.getObjectInstance()).getStudent_id();
        Long id = (Long) ((AtivityRespostas) this.getObjectInstance()).getId();
        String questions = (String) ((AtivityRespostas) this.getObjectInstance()).getQuestions();
        AtivityRespostas respostaToSave = (AtivityRespostas) ((AtivityRespostas) this.getObjectInstance());
        System.out.println("---------------------------------------------------------respostaToSave" + respostaToSave.getStudent_id() + ":" + respostaToSave.getAtivity_id());
       
        System.out.println("####################################");
        System.out.println(instanceAtivity + "   ---   " + instanceStudent);
        JSONObject result = new JSONObject();
       
       apDB = ((IAtivityRespostas)this.getService()).update(id, respostaToSave);

     
            result.put("status", "true");
            return result;
       



        // Esta linha de código implementa um padrão singlton para a classe "ConfigAnaliticsAtivity"
        // ConfigAnalyticsAtivity analyticsParams = ConfigAnalyticsAtivity.getInstance();
        // // Verifica se ja existe um objeto criado e se tens os valores armazenados        
        // if (!analyticsParams.getAnaliticsJson().isEmpty())
        // {
        //     AtivityRespostas respostasObj = new AtivityRespostas(instanceStudent, instanceAtivity, analyticsParams.getAnaliticsJson().toString());
        //     try{
        //         ((IAtivityRespostas)this.getService()).update(null, respostasObj) 
        //     }
        //     catch (Exception e)
        //     {
        //         System.out.println("Error to save the values: " + e.getMessage());
        //     }
        //     result.put("status", "true");
        //     return result;
        // }
        // result.put("status", "false");
        // return result;
    

    }
}

    

