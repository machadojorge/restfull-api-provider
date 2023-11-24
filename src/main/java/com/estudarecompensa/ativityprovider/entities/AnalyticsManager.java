package com.estudarecompensa.ativityprovider.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsManager implements Serializable{

    private static final long serialVersionUID = 1;

    private Long id;
    private Boolean acede_atividade;
    private Boolean acede_atividade_info;
    private Boolean responde_questoes_modulo;
    private Integer respostas_corretas;
    private Integer respostas_erradas;
    private Float percentagem_acertos;
    private Boolean recompensa;
    private Boolean recompensa_acertos;
    private Boolean recompensa_nivel_1;
    private Boolean recompensa_nivel_2;
    private boolean concluiu_modulo;

    List<Map<String, Object>> list = new ArrayList<>();
    
    public AnalyticsManager() {
    }

    public AnalyticsManager(Boolean acede_atividade, Boolean acede_atividade_info, Boolean responde_questoes_modulo,
            Integer respostas_corretas, Integer respostas_erradas, Float percentagem_acertos, Boolean recompensa,
            Boolean recompensa_acertos, Boolean recompensa_nivel_1, Boolean recompensa_nivel_2,
            boolean concluiu_modulo)
    {
        this.acede_atividade = acede_atividade;
        this.acede_atividade_info = acede_atividade_info;
        this.responde_questoes_modulo = responde_questoes_modulo;
        this.respostas_corretas = respostas_corretas;
        this.respostas_erradas = respostas_erradas;
        this.percentagem_acertos = percentagem_acertos;
        this.recompensa = recompensa;
        this.recompensa_acertos = recompensa_acertos;
        this.recompensa_nivel_1 = recompensa_nivel_1;
        this.recompensa_nivel_2 = recompensa_nivel_2;
        this.concluiu_modulo = concluiu_modulo;
    }

    public Map<String, Object> createResponse(String id, AnalyticsManager obj)
    {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> listIntern = new ArrayList<>();

       
        listIntern.add(this.AddToMap("Acedeu à Atividade", obj.getAcede_atividade()));
        listIntern.add(this.AddToMap("Acedeu à Info da Atividade", obj.getAcede_atividade_info()));
        listIntern.add(this.AddToMap("Respondeu às Questões do Módulo", obj.getResponde_questoes_modulo()));
        listIntern.add(this.AddToMap("Respostas Corretas", obj.getRespostas_corretas()));
        listIntern.add(this.AddToMap("Respostas Erradas", obj.getRespostas_erradas()));
        map.put("InveniraStdID", id);
        map.put("quantAnalytics", listIntern);

        return map;

    }

    public Map<String, Object> AddToMap(String name, Object obj)
    {
         Map<String, Object> mapValues = new HashMap<>();
        mapValues.put("name", name);
        mapValues.put("Value", obj);
        return mapValues;
    }

    public  List<Map<String, Object>> getListAllAnalytics(String id)
    {
        this.list.add(this.createResponse(id, this));
        return this.list;
        
    }
    
    public Long getId() {
        return id;
    }

    public Boolean getAcede_atividade() {
        return acede_atividade;
    }

    public void setAcede_atividade(Boolean acede_atividade) {
        this.acede_atividade = acede_atividade;
    }

    public Boolean getAcede_atividade_info() {
        return acede_atividade_info;
    }

    public void setAcede_atividade_info(Boolean acede_atividade_info) {
        this.acede_atividade_info = acede_atividade_info;
    }

    public Boolean getResponde_questoes_modulo() {
        return responde_questoes_modulo;
    }

    public void setResponde_questoes_modulo(Boolean responde_questoes_modulo) {
        this.responde_questoes_modulo = responde_questoes_modulo;
    }

    public Integer getRespostas_corretas() {
        return respostas_corretas;
    }

    public void setRespostas_corretas(Integer respostas_corretas) {
        this.respostas_corretas = respostas_corretas;
    }

    public Integer getRespostas_erradas() {
        return respostas_erradas;
    }

    public void setRespostas_erradas(Integer respostas_erradas) {
        this.respostas_erradas = respostas_erradas;
    }

    public Float getPercentagem_acertos() {
        return percentagem_acertos;
    }

    public void setPercentagem_acertos(Float percentagem_acertos) {
        this.percentagem_acertos = percentagem_acertos;
    }

    public Boolean getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Boolean recompensa) {
        this.recompensa = recompensa;
    }

    public Boolean getRecompensa_acertos() {
        return recompensa_acertos;
    }

    public void setRecompensa_acertos(Boolean recompensa_acertos) {
        this.recompensa_acertos = recompensa_acertos;
    }

    public Boolean getRecompensa_nivel_1() {
        return recompensa_nivel_1;
    }

    public void setRecompensa_nivel_1(Boolean recompensa_nivel_1) {
        this.recompensa_nivel_1 = recompensa_nivel_1;
    }

    public Boolean getRecompensa_nivel_2() {
        return recompensa_nivel_2;
    }

    public void setRecompensa_nivel_2(Boolean recompensa_nivel_2) {
        this.recompensa_nivel_2 = recompensa_nivel_2;
    }

    public boolean isConcluiu_modulo() {
        return concluiu_modulo;
    }

    public void setConcluiu_modulo(boolean concluiu_modulo) {
        this.concluiu_modulo = concluiu_modulo;
    }

    
    


    
}
