package com.estudarecompensa.ativityprovider.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.UpdateDBOperation;
import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;

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

        System.out.println(objPertgTemp);


        // 2º ir buscar os analiticos
        AbstractDBOperation arOperation = new SearchDBOperation((IAtivityRespostas)ar, map);
        JSONObject mapRespostas = arOperation.executeAction();
        System.out.println("Respostas Recebidas: " + mapRespostas);
        Long id = Long.parseLong(mapRespostas.get("id").toString());
        
         // converte para um JSON Object para poder atualizar os campos de acesso à atividade
        newString = (mapRespostas.get("questions").toString()).replace('=', ':');
        JSONObject objRespTemp = new JSONObject(newString);

        System.out.println(objRespTemp);

        // 3º comparar
        // Questão 1:
        if (objPertgTemp.get("solucao_1").toString().equals(map.get("solucao_1")))
        {
            System.out.println("ACERTOU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            respostasCorretas +=1;
        }
        if (objPertgTemp.get("solucao_2").toString().equals(map.get("solucao_2")))
        {
            System.out.println("ACERTOU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            respostasCorretas +=1;
        }
        if (objPertgTemp.get("solucao_3").toString().equals(map.get("solucao_3")))
        {
            System.out.println("ACERTOU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            respostasCorretas +=1;
        }
        if (objPertgTemp.get("solucao_4").toString().equals(map.get("solucao_4")))
        {
            System.out.println("ACERTOU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            respostasCorretas +=1;
        }
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
        System.out.println("Resultado:    " + arDBObject);
        return false;
    }




    public boolean CheckResponses(JSONObject obj) {
        System.out.println("Logic: " + obj.toString());
        // 1º Fazer a consulta pelos valores das respostas
        AtivityPerguntas apDB = new AtivityPerguntas();
        apDB = (AtivityPerguntas)ap.findByativityStudent(obj.get("ativity_id").toString(),obj.get("student_id").toString());
        System.out.println("apDB: " + apDB.toString());
        JSONObject tempObj = new JSONObject(apDB);
        JSONObject quest = new JSONObject(tempObj.get("questions").toString());
        System.out.println(quest.toString());

        // 2º Fazer as consultas pelos parametros do utilizador
        AtivityRespostas arDB = new AtivityRespostas();
        arDB = (AtivityRespostas)ar.findByativityStudent(obj.get("ativity_id").toString(),obj.get("student_id").toString());
       System.out.println(arDB.toString());
       System.out.println("ID: " + arDB.getId());
        JSONObject tempObj2 = new JSONObject(arDB);
        System.out.println("JSONObjec: " + tempObj2.toString());
        JSONObject responses = new JSONObject(tempObj2.get("questions").toString()); 
        System.out.println(responses.toString());
        // 3º Comparar com as respostas dele

        if (((JSONObject) obj.get("questions")).get("q1".toString()).equals(quest.get("q1".toString())))
        {
            System.out.println("-------- Sao Iguais --------");
            int result = Integer.parseInt(responses.get("r1").toString());
            result = result + 1;
            responses.put("r1", result);
            // Criar um objeto temporario para guardar os dados na base de dados
            System.out.println(("Criado: " + responses));
            AtivityRespostas ativityTemp = new AtivityRespostas(obj.get("student_id").toString(), obj.get("ativity_id").toString(), responses.toString());
           
            System.out.println("Criado outras vez: " + responses.toString());
            ar.update(arDB.getId(),ativityTemp);
            return true;
        }
        else
        {
            return false;
        }
        // 4º atualizar os dados e guardar na base de dados

        // 5º devolver uma string para devolver para a view com perguntas ou nada

    }
}
