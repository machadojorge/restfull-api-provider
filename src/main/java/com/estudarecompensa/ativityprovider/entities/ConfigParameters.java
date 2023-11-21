package com.estudarecompensa.ativityprovider.entities;

import java.io.Serializable;

public class ConfigParameters implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstQuestion;
    private String firstResponse;
    private String correctFirstRespoinse;
    
    public ConfigParameters(Long id, String firstQuestion, String firstResponse, String correctFirstRespoinse) {
        this.id = id;
        this.firstQuestion = firstQuestion;
        this.firstResponse = firstResponse;
        this.correctFirstRespoinse = correctFirstRespoinse;
    }

    public ConfigParameters() {
    }

    public String getFirstQuestion() {
        return firstQuestion;
    }

    public void setFirstQuestion(String firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    public String getFirstResponse() {
        return firstResponse;
    }

    public void setFirstResponse(String firstResponse) {
        this.firstResponse = firstResponse;
    }

    public String getCorrectFirstRespoinse() {
        return correctFirstRespoinse;
    }

    public void setCorrectFirstRespoinse(String correctFirstRespoinse) {
        this.correctFirstRespoinse = correctFirstRespoinse;
    }

    

}
