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
    // This endpoint goes return the analiticas results of the activity, analytics_list_url
    @RequestMapping(value="/analytics_review_ativity", method=RequestMethod.POST)
    public ResponseEntity<String> findAllAnalyticsParams(@RequestBody String activityID)
    {
        JSONObject objtemp = new JSONObject();
        System.out.println("String: " + activityID.getClass().getName());
        JSONObject json = new JSONObject(activityID.toString());
        System.out.println("JSON: " + json.get("activityID"));
        String id =  json.get("activityID").toString();
        System.out.println("ID: " + id);
        AbstractDBOperation operationUpdate = new SearchDBOperation(serviceRespost, id);
        objtemp = operationUpdate.executeAction();
        System.out.println("Analiticd --->: " + objtemp);
        return ResponseEntity.ok().body(objtemp.get("status").toString());
       
    }

    public String decodeActivityId(String activityID)
    {
         try {
            JSONObject jsonObject = new JSONObject(activityID);
            String id = jsonObject.getString("activityID");
            return id;//"1234";// map.get("activityID").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityID;
    }
    
}
