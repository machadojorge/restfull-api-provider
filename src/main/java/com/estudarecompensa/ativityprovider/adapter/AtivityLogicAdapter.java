package com.estudarecompensa.ativityprovider.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.UpdateDBOperation;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;
import com.estudarecompensa.ativityprovider.utils.CheckExist;

public class AtivityLogicAdapter {

    private IAtivityPerguntas ap;
    private IAtivityRespostas ar;
    private Map<String, String> map = new LinkedHashMap<String, String>();

    public AtivityLogicAdapter(IAtivityPerguntas ap, IAtivityRespostas ar, Map<String, String> map)
    {
        this.ap = ap;
        this.ar = ar;
        this.map = map;
    }
    public IAtivityPerguntas getAp() {
        return ap;
    }
    public void setAp(IAtivityPerguntas ap) {
        this.ap = ap;
    }
    public IAtivityRespostas getAr() {
        return ar;
    }
    public void setAr(IAtivityRespostas ar) {
        this.ar = ar;
    }



    public boolean checkQuestions()
    {
        Integer respostasCorretas = 0;
        // 1º ir buscar as questões
             // verificar se o configAnaliticsParamests tem registos no objecto
        AbstractDBOperation apOperation = new SearchDBOperation((IAtivityPerguntas)ap, map);
        JSONObject mapPerguntas = apOperation.executeAction();

         // converte para um JSON Object para poder atualizar os campos de acesso à atividade
        String newString = (mapPerguntas.get("questions").toString()).replace('=', ':');
        JSONObject objPertgTemp = new JSONObject(newString);

        // 2º ir buscar os analiticos
        AbstractDBOperation arOperation = new SearchDBOperation((IAtivityRespostas)ar, map);
        JSONObject mapRespostas = arOperation.executeAction();
        Long id = Long.parseLong(mapRespostas.get("id").toString());
        
         // converte para um JSON Object para poder atualizar os campos de acesso à atividade
        newString = (mapRespostas.get("questions").toString()).replace('=', ':');
        JSONObject objRespTemp = new JSONObject(newString);

        // 3º comparar
        // Questão 1:
        respostasCorretas = respostasCorretas + CheckExist.compareResult(objPertgTemp.get("solucao_1").toString(), map.get("solucao_1"));
        respostasCorretas = respostasCorretas + CheckExist.compareResult(objPertgTemp.get("solucao_2").toString(), map.get("solucao_2"));
        respostasCorretas = respostasCorretas + CheckExist.compareResult(objPertgTemp.get("solucao_3").toString(), map.get("solucao_3"));
        respostasCorretas = respostasCorretas + CheckExist.compareResult(objPertgTemp.get("solucao_4").toString(), map.get("solucao_4"));
     
        Integer respostasReeadas = 4 - respostasCorretas;
        Float percentAcerts = (float) (respostasCorretas / 4);
        // atualizar as respostas
        objRespTemp.put("responde_questoes_modulo", "true");
        objRespTemp.put("respostas_corretas", respostasCorretas.toString());
        objRespTemp.put("respostas_erradas", respostasReeadas.toString());
        objRespTemp.put("percentagem_acertos", percentAcerts.toString());
        AtivityRespostas aR = new AtivityRespostas(id, map.get("Inven!RAstdID"), map.get("activityID"),objRespTemp.toString());
        // atualizar a BD
         // 2º ir buscar os analiticos
         AbstractDBOperation arDB = new UpdateDBOperation((IAtivityRespostas)ar, aR);
         JSONObject arDBObject = arDB.executeAction();
        return false;
    }
}
