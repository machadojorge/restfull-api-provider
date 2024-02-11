package com.estudarecompensa.ativityprovider.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public class CheckExist {

    public static boolean existInDatabase(List<DeployActivity> list, String valueCheck)
    {
        
        for (DeployActivity activity : list)
        {
            if (activity.getintance_ativity().equals(valueCheck))
            {
                return true;
            }
        }
        return false;
    }

       public static boolean existAnaliticsInDatabase(List<AnaliticDao> list, String ativity_id, String student_id)
    {
        
        for (AnaliticDao activity : list)
        {
            if (activity.getStudent_id().equals(student_id) && activity.getAtivity_instance().equals(ativity_id))
            {
                return true;
            }
        }
        return false;
    }

    
    // verificar se o registo contem informação antes de ser devolvido ao controller
    public static boolean checkReturnObject(JSONObject obj)
    {
        if (obj!= null && !obj.isEmpty())
                return true;
        return false;
    }
     
    public static Map<String, Object> createMapResponse(AtivityRespostas ap)
    {
        Map<String, String> names = new HashMap<String, String>();
        names.put("respostas_erradas", "Respostas Erradas");
        names.put("recompensa_nivel_1", "Recompensa Nivel 1");
        names.put("recompensa_nivel_2", "Recompensa Nivel 2");
        names.put("concluiu_modulo", "Concluiu o Módulo");
        names.put("acede_atividade_info", "Acede à informação da Atividade");
        names.put("percentagem_acertos", "Percentagens de acertos");
        names.put("responde_questoes_modulo", "Responde Às Questões");
        names.put("acede_atividade", "Acede à Atividade");
        names.put("respostas_corretas", "Respostas Corretas");
        names.put("recompensa", "Recompensas");



        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<JSONObject> jsonQuant = new ArrayList<JSONObject>();
        List<JSONObject> jsonQual = new ArrayList<JSONObject>();
        System.out.println(ap.getQuestions());
        System.out.println(".........  - ............");
        
        String newString = ap.getQuestions().replace('=', ':');
        System.out.println("String: " + newString);

        JSONObject objtemp = new JSONObject(newString);
        System.out.println("MAPA --> " + objtemp.toMap());
        
        map.put("innveniraStdID", ap.getStudent_id());
        map.put("qualAnalytics", jsonQual);
        
        for (Entry<String, Object> entrada : objtemp.toMap().entrySet()) {
            JSONObject temp = new JSONObject();

            temp.put("name", names.get(entrada.getKey()));
            temp.put("value", entrada.getValue());
            jsonQuant.add(temp);
           
        }

       
        System.out.println("json:  " + jsonQuant);
        map.put("quantAnalytics", jsonQuant);
       
        return map;
    }
    
}
