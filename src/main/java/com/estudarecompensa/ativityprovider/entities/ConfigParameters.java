package com.estudarecompensa.ativityprovider.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigParameters implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String typeTextPlain = "text/plain";
    List<Map<String, String>> json_params_url = new ArrayList<Map<String,String>>();
    private String urlInfo;
    private String questao_1;
    private String questao_suplente_1;
    private String resposta_1_1;
    private String resposta_1_2;
    private String resposta_1_3;
    private String resposta_1_4;
    private String solucao_1;
    private String solucao_suplente_1;

   


    
    
    public ConfigParameters()
    {
        this.urlInfo = this.createParamsMap("url_info", "url");
        this.questao_1 = this.createParamsMap("questao_1", typeTextPlain);
        this.questao_suplente_1 = this.createParamsMap("questao_suplente_1", typeTextPlain);
        this.resposta_1_1 = this.createParamsMap("resposta_1_1", typeTextPlain);
        this.resposta_1_2 = this.createParamsMap("resposta_1_2", typeTextPlain);
        this.resposta_1_3 = this.createParamsMap("resposta_1_3", typeTextPlain);
        this.resposta_1_4 = this.createParamsMap("resposta_1_4", typeTextPlain);
        this.solucao_1 = this.createParamsMap("solucao_1", typeTextPlain);
        this.solucao_suplente_1 = this.createParamsMap("solucao_sumplente_1", typeTextPlain);   
    }


    private String createParamsMap(String param, String type)
    {
         Map<String, String> param_map = new HashMap<String, String>();
        param_map.put("name",param);
        param_map.put("type",type);
        this.json_params_url.add(param_map);
        return param;
    }

    public List<Map<String, String>> getJson_params_url() {
        return json_params_url;
    }



    public void setJson_params_url(List<Map<String, String>> json_params_url) {
        this.json_params_url = json_params_url;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }


    public String getTypeTextPlain() {
        return typeTextPlain;
    }


    public void setTypeTextPlain(String typeTextPlain) {
        this.typeTextPlain = typeTextPlain;
    }


    public String getUrlInfo() {
        return urlInfo;
    }


    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo;
    }


    public String getQuestao_1() {
        return questao_1;
    }


    public void setQuestao_1(String questao_1) {
        this.questao_1 = questao_1;
    }


    public String getQuestao_suplente_1() {
        return questao_suplente_1;
    }


    public void setQuestao_suplente_1(String questao_suplente_1) {
        this.questao_suplente_1 = questao_suplente_1;
    }


    public String getResposta_1_1() {
        return resposta_1_1;
    }


    public void setResposta_1_1(String resposta_1_1) {
        this.resposta_1_1 = resposta_1_1;
    }


    public String getResposta_1_2() {
        return resposta_1_2;
    }


    public void setResposta_1_2(String resposta_1_2) {
        this.resposta_1_2 = resposta_1_2;
    }


    public String getResposta_1_3() {
        return resposta_1_3;
    }


    public void setResposta_1_3(String resposta_1_3) {
        this.resposta_1_3 = resposta_1_3;
    }


    public String getResposta_1_4() {
        return resposta_1_4;
    }


    public void setResposta_1_4(String resposta_1_4) {
        this.resposta_1_4 = resposta_1_4;
    }


    public String getSolucao_1() {
        return solucao_1;
    }


    public void setSolucao_1(String solucao_1) {
        this.solucao_1 = solucao_1;
    }


    public String getSolucao_suplente_1() {
        return solucao_suplente_1;
    }


    public void setSolucao_suplente_1(String solucao_suplente_1) {
        this.solucao_suplente_1 = solucao_suplente_1;
    }



  

}
