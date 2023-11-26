package com.estudarecompensa.ativityprovider.resources;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.entities.AnalyticsManager;


@RestController
public class AnalyticsManagerResources {
    
    // This endpoint goes return the analiticas results of the activity, analytics_list_url
    @RequestMapping(value="/analytics_review_ativity", method=RequestMethod.POST)
    public ResponseEntity<List<Map<String, Object>>> findAllAnalyticsParams(@RequestBody String activityID)
    {
        System.out.println(activityID);
        String id = this.decodeActivityId(activityID);
    
    
        
        AnalyticsManager qualAnalytics = new AnalyticsManager(true,true,true,3, 1, 75.0F, true, true, true, false, false);
        
        List<Map<String, Object>> list = qualAnalytics.getListAllAnalytics(id);
        return ResponseEntity.ok().body(list);
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
