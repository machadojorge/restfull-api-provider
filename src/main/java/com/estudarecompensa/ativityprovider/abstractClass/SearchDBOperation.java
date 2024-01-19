package com.estudarecompensa.ativityprovider.abstractClass;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.adapter.JsonAdapter;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IJson;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;

public class SearchDBOperation<T, M> extends AbstractDBOperation {

    // public SearchDBOperation()
    // {

    public SearchDBOperation(T service) {
        super(service);
    }


    // }
    @Override
    public JSONObject executeOperation() {
       JSONObject result = new JSONObject();
        result = this.checkInstance();
       return result;
    }


    @Override
	protected JSONObject checkInstance() {
        //  List<ConfigAnalyticsAtivity> analitics = ((ConfigAnalyticsParamsService) this.getService()).getAllAnalyticsParams();
        //     System.out.println("Result: checkInstance" + analitics);
        // System.out.println("Service: " + this.service);
        //  System.out.println("Service: " + this.getService().getClass());
        if (this.getService() instanceof ConfigAnalyticsParamsService)
        {     System.out.println("Service: " + this.service);
            System.out.println("Estou no IF");
            IJson adp = new JsonAdapter(this.getService());

            JSONObject result = new JSONObject();
          
            result = adp.toJsonObject();
            System.out.println("Adapter: " + result);
            return result;
       
        }
        return null;
    }


    // @Override
    // public JSONObject getFromDatabase(T service) {
    //     List<ConfigAnalyticsAtivity> analitics = service.getAllAnalyticsParams();
    //     return null;
        
    // }

    // @Override
    // public boolean insertOrUpdateValue(T service, M objectInstance) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'insertOrUpdateValue'");
    // }

    // public JSONObject createJSONAnalytics()
    // {
    //       // Chamar o metodo que vai buscar os dados à base de dados
    //     List<ConfigAnalyticsAtivity> analitics = ((ConfigAnalyticsParamsService) service).getAllAnalyticsParams();
    //     System.out.println("Result: " + analitics);
    //     //criar Objectos JSON Auxiliares
    //     JSONObject finalJson = new JSONObject();

    //     // criar Listas auxiliares para dados quantitativos e qualitativos
    //    List<JSONObject> quanlAnalytics = new ArrayList<JSONObject>();
    //    List<JSONObject> quantAnalytics = new ArrayList<JSONObject>();

    //     // Converter a lista para o objecto jSON, no formato desejado pronto a ser devolvido na resposta
    //       for(ConfigAnalyticsAtivity t : analitics)
    //     {
    //         JSONObject map = new JSONObject();
    //         map.put("name",t.getAttribute());
    //         map.put("type", t.getType());

    //         if (t.getTypeOfAnalyses() == 1)
    //         {
    //             quantAnalytics.add(map);
    //         }
    //         if(t.getTypeOfAnalyses() == 0)
    //         {
    //             quanlAnalytics.add(map);
    //         }   
    //     }
    //    finalJson.put("qualAnalytics", quanlAnalytics);
    //    finalJson.put("quatAnalytics", quantAnalytics);

    //    // Devolver o JSON pronto a ser adicionado à resposta ao Inven!RA
    //    return finalJson;
    //     }
  
       
    }
    

