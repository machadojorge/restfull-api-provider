package com.estudarecompensa.ativityprovider.entities.ConfigManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigAnalyticsAtivity implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    private String acede_atividade;
    private String acede_atividade_info;
    private String responde_questoes_modulo;
    private String respostas_corretas;
    private String respostas_erradas;
    private String percentagem_acertos;
    private String recompensa;
    private String recompensa_acertos;
    private String recompensa_nivel_1;
    private String recompensa_nivel_2;
    private String concluiu_modulo;
    private String analyticsQualName;
    private String analyticsQuantName;
    private String typeDataBool;
    private String typeDataInt;
    private String typeDataFloat;


    private Map<String, List<Map<String, String>>> analytics_list_url = new HashMap<>();
    public List<Map<String, String>> quantAnalytics = new ArrayList<>();
    public List<Map<String, String>> quanlAnalytics = new ArrayList<>();

    public ConfigAnalyticsAtivity()
    {
        this.typeDataBool = "boolean";
        this.typeDataInt = "integer";
        this.typeDataFloat = "percentage";
        this.analyticsQuantName = "quantAnalytics";
        this.analyticsQualName = "qualAnalytics";
        this.acede_atividade = this.createParamsMap("acede_atividade", typeDataBool, quantAnalytics);
        this.acede_atividade_info = this.createParamsMap("acede_atividade_info", typeDataBool, quantAnalytics);
        this.responde_questoes_modulo = this.createParamsMap("responde_questoes_modulo", typeDataBool, quantAnalytics);
        this.respostas_corretas = this.createParamsMap("respostas_corretas", typeDataInt, quantAnalytics);
        this.respostas_erradas = this.createParamsMap("respostas_erradas", typeDataInt, quantAnalytics);
        this.percentagem_acertos = this.createParamsMap("percentagem_acertos", typeDataFloat, quantAnalytics);
        this.recompensa = this.createParamsMap("recompensa", typeDataBool, quantAnalytics);
        this.recompensa_acertos = this.createParamsMap("recompensa_acertos", typeDataBool, quantAnalytics);
        this.recompensa_nivel_1 = this.createParamsMap("recompensa_nivel_1", typeDataBool, quantAnalytics);
        this.recompensa_nivel_2 = this.createParamsMap("recompensa_nivel_2", typeDataInt, quantAnalytics);
        this.concluiu_modulo = this.createParamsMap("concluiu_modulo", typeDataInt, quantAnalytics);
       
        this.addToMap();
        System.out.println(this.analytics_list_url);
    }
    
    private String createParamsMap(String param, String type, List<Map<String, String>> list)
    {
         Map<String, String> param_map = new HashMap<String, String>();
        param_map.put("name",param);
        param_map.put("type",type);
        list.add(param_map);
        return param;
    }

    private void addToMap()
    {
        this.analytics_list_url.put(this.analyticsQualName,this.quanlAnalytics);
        this.analytics_list_url.put(this.analyticsQuantName, this.quantAnalytics);
    }

    public String getAcede_atividade() {
        return acede_atividade;
    }

    public void setAcede_atividade(String acede_atividade) {
        this.acede_atividade = acede_atividade;
    }

    public String getAcede_atividade_info() {
        return acede_atividade_info;
    }

    public void setAcede_atividade_info(String acede_atividade_info) {
        this.acede_atividade_info = acede_atividade_info;
    }

    public String getResponde_questoes_modulo() {
        return responde_questoes_modulo;
    }

    public void setResponde_questoes_modulo(String responde_questoes_modulo) {
        this.responde_questoes_modulo = responde_questoes_modulo;
    }

    public String getRespostas_corretas() {
        return respostas_corretas;
    }

    public void setRespostas_corretas(String respostas_corretas) {
        this.respostas_corretas = respostas_corretas;
    }

    public String getRespostas_erradas() {
        return respostas_erradas;
    }

    public void setRespostas_erradas(String respostas_erradas) {
        this.respostas_erradas = respostas_erradas;
    }

    public String getPercentagem_acertos() {
        return percentagem_acertos;
    }

    public void setPercentagem_acertos(String percentagem_acertos) {
        this.percentagem_acertos = percentagem_acertos;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public String getRecompensa_acertos() {
        return recompensa_acertos;
    }

    public void setRecompensa_acertos(String recompensa_acertos) {
        this.recompensa_acertos = recompensa_acertos;
    }

    public String getRecompensa_nivel_1() {
        return recompensa_nivel_1;
    }

    public void setRecompensa_nivel_1(String recompensa_nivel_1) {
        this.recompensa_nivel_1 = recompensa_nivel_1;
    }

    public String getRecompensa_nivel_2() {
        return recompensa_nivel_2;
    }

    public void setRecompensa_nivel_2(String recompensa_nivel_2) {
        this.recompensa_nivel_2 = recompensa_nivel_2;
    }

    public String getConcluiu_modulo() {
        return concluiu_modulo;
    }

    public void setConcluiu_modulo(String concluiu_modulo) {
        this.concluiu_modulo = concluiu_modulo;
    }

    public String getAnalyticsQualName() {
        return analyticsQualName;
    }

    public void setAnalyticsQualName(String analyticsQualName) {
        this.analyticsQualName = analyticsQualName;
    }

    public String getAnalyticsQuantName() {
        return analyticsQuantName;
    }

    public void setAnalyticsQuantName(String analyticsQuantName) {
        this.analyticsQuantName = analyticsQuantName;
    }

    public String getTypeDataBool() {
        return typeDataBool;
    }

    public void setTypeDataBool(String typeDataBool) {
        this.typeDataBool = typeDataBool;
    }

    public String getTypeDataInt() {
        return typeDataInt;
    }

    public void setTypeDataInt(String typeDataInt) {
        this.typeDataInt = typeDataInt;
    }

    public String getTypeDataFloat() {
        return typeDataFloat;
    }

    public void setTypeDataFloat(String typeDataFloat) {
        this.typeDataFloat = typeDataFloat;
    }

    public Map<String, List<Map<String, String>>> getAnalytics_list_url() {
        return analytics_list_url;
    }

    public void setAnalytics_list_url(Map<String, List<Map<String, String>>> analytics_list_url) {
        this.analytics_list_url = analytics_list_url;
    }

    
}
