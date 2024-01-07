package com.estudarecompensa.ativityprovider.adapter;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IJson;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.ConfigParametersService;

public class JsonAdapter<T> implements IJson {

    // Padrão Adapter/Wrapper
    // Esta classe é a classe responsavel por fazer a compatibilidade/transformação entre os dados vindos da base de dados e
    // o formato correto dos dados, que o pedido da Inven!RA requere, que vão ser enviados como resposta a esse pedido.
    
    // 1º Esta classe faz de ligação entre a classe "ConfigManagerResources", que usa um objeto da interface "IJson", que é 
    // implementada por esta classe, passando um objecto da classe "ConfigAnalyticsParamsService", que é a outra classe a quem o
    // nosso Adapter (JsonAdapter) está ligada. É um Adapter entre estas duas classes

    // 2º Foi implementada recebendo um tipo "T" como injecção de dependencias pois foi pensada para servir mais do que uma classe, reutilizar este adapter
    // passando um objecto do "Service" que se pretende e no metodo "toJsonObject()", que é o unico metodo disponivel para as classes que usam o adapter, 
    // defenir qual é exactamente o tipo do objecto a que o "T service" se refere e chamar o metodo especifico desta classe que atende 
    // às necessidades desse Objecto dessa classe Concreta.
    // Neste caso, Verificamos se o objecto passado é uma instancia de "ConfigAnalyticsParamsService" e se for, chamamos o metodo especifico para esta classe,
    // o "getAnalitics", que vai receber os dados da base de dados, atravez da classe "ConfigAnalyticsParamsService", transforma-os no formato JSON desejado
    // e devolve esse JSON para ser enviado ao Inven!RA, já no formato pretendido.

    // 3º Cada classe terá o seu proprio método de acordo às transformações que os dados necessitem e ao formato JSON esperado na parte de quem responde 
    // aos pedidos.
    
    private T service;
    public JsonAdapter(T service)
    {
        this.service = service;
    }

    @Override
    public JSONObject toJsonObject() {
       JSONObject finalJson = new JSONObject();
        if (this.service instanceof ConfigAnalyticsParamsService)
        {
            finalJson = this.getAnalitics();
        }

        // Este "if" é apenas de exemplo, caso o parametro "T service" fosse instancia da classe "ConfigParametersService", o que pode acontecer pois
        // o Adapter foi pensado para ser generico e servir várias classes diferentes, neste "If", seria chamado outro método, diferente do chamado no 
        // "if" anterior, dedicado só a a esta classe, para fazer as alterações especificas que a classe que chamasse este adapter necessita-se
        if (this.service instanceof ConfigParametersService)
        {
            System.out.println("Sou uma outra instancia !!!!!!!!!!!");
            System.out.println("Vou chamar outro método especifico para Esta classe!");
        }

        return finalJson;
    }


    public JSONObject getAnalitics()
    {
     
        // Chamar o metodo que vai buscar os dados à base de dados
        List<ConfigAnalyticsAtivity> analitics = ((ConfigAnalyticsParamsService) service).getAllAnalyticsParams();
  
        //criar Objectos JSON Auxiliares
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

       // Devolver o JSON pronto a ser adicionado à resposta ao Inven!RA
       return finalJson;
    }
    
}
