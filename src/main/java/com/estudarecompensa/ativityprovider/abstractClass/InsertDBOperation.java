package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;

public class InsertDBOperation<T, M> extends AbstractDBOperation {

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

    /**
     * Este método é responsavel por veriricar se já existe registo das perguntas 
     * para esta atividade e adicionar caso não haja
     * @return Um JSON com dois parametros, {"status":"true"} caso tenha sucesso ou {"status":"false"} caso nao tenha 
     * 
     */
    public JSONObject addPerguntas()
    {
        AtivityPerguntas apDB = new AtivityPerguntas();
        String instanceAtivity = (String) ((Map) this.getObjectInstance()).get("activityID");
        String instanceStudent = (String) ((Map) this.getObjectInstance()).get("Inven!RAstdID");
       
        JSONObject result = new JSONObject();
        result.put("status", "true");
        apDB = ((IAtivityPerguntas)this.getService()).findByativityStudent(instanceAtivity, instanceStudent);

        if (apDB != null)
        {
            String newString = apDB.getQuestions().replace('=', ':');
            JSONObject objtemp = new JSONObject(newString);  
            return result;
        }
        
        // Adicionar os parametros caso não Existam na base de dados
        Map<String, String> data = (Map<String, String>)((Map)this.getObjectInstance()).get("json_params");
        JSONObject obj = new JSONObject(data);
        AtivityPerguntas perguntasObj = new AtivityPerguntas(instanceStudent, instanceAtivity, data.toString());

        try{
            ((IAtivityPerguntas)this.getService()).saveValues(perguntasObj);
        }
        catch (Exception e)
        {
            System.out.println("Error to save the values: " + e.getMessage());
            result.put("status", "false");
            return result;
        }
        return result;
    
    }
    /**
     * Este método serve para armazenar os valores dos parametros analiticos onde se vai armazenar os dados
     * da atividade, nomeadamente o resultados obtidos da execução da atividade
     * @return (JSONObject) contem um true ou false, se teve ou não sucesso a armazenar os dados
     */
    public JSONObject addRespostas()
    {
        AtivityRespostas apDB = new AtivityRespostas();
        String instanceAtivity = (String) ((Map) this.getObjectInstance()).get("activityID");
        String instanceStudent = (String) ((Map) this.getObjectInstance()).get("Inven!RAstdID");
        JSONObject result = new JSONObject();
       
        apDB = ((IAtivityRespostas)this.getService()).findByativityStudent(instanceAtivity, instanceStudent);

        if (apDB != null)
        {
            result.put("status", "true");
            return result;
        }
        // Esta linha de código implementa um padrão singlton para a classe "ConfigAnaliticsAtivity"
        ConfigAnalyticsAtivity analyticsParams = ConfigAnalyticsAtivity.getInstance();
        // Verifica se ja existe um objeto criado e se tens os valores armazenados        
        if (!analyticsParams.getAnaliticsJson().isEmpty())
        {
            AtivityRespostas respostasObj = new AtivityRespostas(instanceStudent, instanceAtivity, AtivityRespostas.createJsonAnalitics().toString());
            try{
                ((IAtivityRespostas)this.getService()).saveValues(respostasObj);
            }
            catch (Exception e)
            {
                System.out.println("Error to save the values: " + e.getMessage());
            }
            result.put("status", "true");
            return result;
        }
        result.put("status", "false");
        return result;
    }
}

    

