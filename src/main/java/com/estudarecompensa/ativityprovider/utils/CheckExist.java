package com.estudarecompensa.ativityprovider.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;

public class CheckExist {

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
        
        String newString = ap.getQuestions().replace('=', ':');
        JSONObject objtemp = new JSONObject(newString);
        
        map.put("innveniraStdID", ap.getStudent_id());
        map.put("qualAnalytics", jsonQual);
        for (Entry<String, Object> entrada : objtemp.toMap().entrySet()) {
            JSONObject temp = new JSONObject();

            temp.put("name", names.get(entrada.getKey()));
            temp.put("value", entrada.getValue());
            jsonQuant.add(temp);
        }
        map.put("quantAnalytics", jsonQuant);
       
        return map;
    }
    
    public static int compareResult(String str1, String str2)
    {
        if (str1 == null || str2 == null)
        {
            return 0;
        }
        if (str1.equals(str2))
        {
            return 1;
        }
        return 0;
    }
}
