package com.estudarecompensa.ativityprovider.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.entities.AnalyticsManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AnalyticsManagerResources {
    
    // This endpoint goes return the analiticas results of the activity, analytics_list_url
    @RequestMapping(value="/analytics_review_ativity", method=RequestMethod.POST)
    public ResponseEntity<List<Map<String, Object>>> findAllAnalyticsParams(@RequestBody String activityID)
    {
        String id = this.decodeActivityId(activityID);
    
    
        
        AnalyticsManager qualAnalytics = new AnalyticsManager(true,true,true,3, 1, 75.0F, true, true, true, false, false);
        
        List<Map<String, Object>> list = qualAnalytics.getListAllAnalytics(id);
        return ResponseEntity.ok(list);
    }

    public String decodeActivityId(String activityID)
    {
         try {
            // Usar Jackson para converter a string JSON em um objeto JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(activityID);

            // Extrair o valor associado à chave "activityID"
            String id = jsonNode.get("activityID").asText();

            // Imprimir o valor
         return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityID;
    }
    
}
