package com.estudarecompensa.ativityprovider.resources;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;


@RestController
public class AnalyticsManagerResources {
    
    @Autowired
    private IAtivityRespostas serviceRespost;

    /**
     * Este método é o endpoint responsavel por receber o pedido pelos dados Analiticos
     * de cada atividade de cada estudante. 
     * @param activityID É o id da atividade que pretende os dados Analiticos
     * @return Uma String com os dados no formato correto que o Inven!RA espera.
     */
    @RequestMapping(value="/analytics_review_ativity", method=RequestMethod.POST)
    public ResponseEntity<String> findAllAnalyticsParams(@RequestBody String activityID)
    {
        // Extrair o ID da atividade
        JSONObject json = new JSONObject(activityID.toString());
        String id =  json.get("activityID").toString();

        // realizar a pesquisa
        JSONObject objtemp = new JSONObject();
        AbstractDBOperation operationUpdate = new SearchDBOperation(serviceRespost, id);
        objtemp = operationUpdate.executeAction();
        System.out.println("Analiticd --->: " + objtemp);
        return ResponseEntity.ok().body(objtemp.get("status").toString());
       
    }

}
