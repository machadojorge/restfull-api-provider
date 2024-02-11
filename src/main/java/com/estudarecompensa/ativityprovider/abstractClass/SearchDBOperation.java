package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.adapter.JsonAdapter;
import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.entities.ConfigParameters;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IJson;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.ConfigParametersService;
import com.estudarecompensa.ativityprovider.utils.CheckExist;


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
            methodMap.put("addPerguntas", classeAtual.getMethod("addPerguntas"));
            methodMap.put("addRespostas", classeAtual.getMethod("addRespostas"));
            methodMap.put("getAnaliticsResult", classeAtual.getMethod("getAnaliticsResult"));
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
        // Esta linha de código implementa um padrão singlton para a classe "ConfigAnaliticsAtivity"
        ConfigAnalyticsAtivity analyticsParams = ConfigAnalyticsAtivity.getInstance();
        JSONObject result = new JSONObject();
        // Verifica se ja existe um objeto criado e se tens os valores armazenados        
        if (!analyticsParams.getAnaliticsJson().isEmpty())
        {
            result.put("params", analyticsParams.getAnaliticsJson());
            return result;
        }
        // Se não tiver chama a classe adapter que vai fazer a consulta e devolver o JSONObject com a resposta
        IJson jsonObject = new JsonAdapter((ConfigAnalyticsParamsService)this.getService());
        JSONObject finalJson = jsonObject.toJsonObject();

        // Guarda a resposta na variavel da classe que implementa o singleton para 
        // poder ser reutilizados os valores
        analyticsParams.setAnaliticsJson(finalJson);
        // devolver a resposta
        result.put("params", finalJson);
        return result;
    }

    // Este método vai pesquisar à base de dados por todos os Parametros de configuração de uma atividade
    public JSONObject getAllConfigParams()
    {
       
        ConfigParameters paramsConfig = ConfigParameters.getInstance();
        JSONObject result = new JSONObject();

        // Verificar se o objeto criado já existe e se tem os valores armazenados nele
        if (!paramsConfig.returnListConfigParams().isEmpty())
        {
            List<Map<String, String>> params = paramsConfig.getConfigList();
            result.put("params", params);
            return result;
        }

        List<ConfigParameters> analitics = ((ConfigParametersService) this.getService()).getAllParameters();
        List<Map<String, String>> params = new ArrayList<Map<String, String>>();
        for(ConfigParameters t : analitics)
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name",t.getAttribute());
            map.put("type", t.getType());
            params.add(map);
        }
        paramsConfig.setConfigList(params);
        result.put("params", params);
        return result;
    }

    /**
     * Este método é reponsavel por pesquisar todos os analiticos armazenados para 
     * uma tividade e para um utilizador. Isto é util para atualizar os analiticos quando estiver a 
     * responder à atividade
     * @return (JSONObject) com todos os analiticos  
     */
    public JSONObject addRespostas()
    {
        AtivityRespostas apDB = new AtivityRespostas();
        String instanceAtivity =  (String) ((Map) this.getObjectInstance()).get("activityID");
        String instanceStudent = (String) ((Map) this.getObjectInstance()).get("Inven!RAstdID");
        JSONObject result = new JSONObject();
       
       apDB = ((IAtivityRespostas)this.getService()).findByativityStudent(instanceAtivity, instanceStudent);

       if (apDB != null)
       {
            result.put("status", "true");
            result.put("id", apDB.getId().toString());
            result.put("questions", apDB.getQuestions());
            return result;
       }
       result.put("status", "false");
       return result;
    }

    /**
     * Este método é reponsavel por pesquisar todos as perguntas armazenados para 
     * uma tividade e para um utilizador. Isto é util para atualizar os analiticos quando estiver a 
     * responder à atividade
     * @return (JSONObject) com todos as perguntas e respostas
     */
    public JSONObject addPerguntas()
    {
        AtivityPerguntas apDB = new AtivityPerguntas();
        String instanceAtivity =  (String) ((Map) this.getObjectInstance()).get("activityID");
        String instanceStudent = (String) ((Map) this.getObjectInstance()).get("Inven!RAstdID");
       
        System.out.println(instanceAtivity + "   ---   " + instanceStudent);
        JSONObject result = new JSONObject();
       
       apDB = ((IAtivityPerguntas)this.getService()).findByativityStudent(instanceAtivity, instanceStudent);

       if (apDB != null)
       {
            result.put("status", "true");
            result.put("id", apDB.getId().toString());
            result.put("questions", apDB.getQuestions());
            return result;
       }
       result.put("status", "false");
       return result;

    }

    /**
     * Este método é reponsavel por pedir a consulta à base de dados pelos 
     * analiticos dos utilizadores em uma atividade. Após isso, cria uma 
     * lista com todos os parametros por id de estudante/utilizador e 
     * devolve como resposta
     * @return (JSONObject) result, em que a chave é "status":lista
     */
    public JSONObject getAnaliticsResult()
    {
        String InveniraStdID = (String)this.getObjectInstance();
        List<AtivityRespostas> apDB = new ArrayList<AtivityRespostas>();
        JSONObject result = new JSONObject();
        Map<String, Object> resultAnalitics = new HashMap<String, Object>();
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
       apDB = ((IAtivityRespostas)this.getService()).findByStudentInstance(InveniraStdID);
   
       for (AtivityRespostas ap : apDB)
       {      
            resultAnalitics = CheckExist.createMapResponse(ap);
            resultList.add(resultAnalitics);
       }
       result.put("status", resultList);
       return result;
    }
}


   
    

