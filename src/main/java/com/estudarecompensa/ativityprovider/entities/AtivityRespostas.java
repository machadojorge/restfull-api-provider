package com.estudarecompensa.ativityprovider.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_json_respostas")
public class AtivityRespostas {

    private static final long serialVersionUID = 1L;
     // JSON Object que vai guardar os parametros Analiticos de configuração vindos da base de dados
     private  static JSONObject jsonRespostas = new JSONObject();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String student_id;
    private String ativity_id;
    private String json_r;
    

    public AtivityRespostas(String student_id, String ativity_id, String questions) {
        this.student_id = student_id;
        this.ativity_id = ativity_id;
        this.json_r = questions;
    }

    public AtivityRespostas(Long id, String student_id, String ativity_id, String json_r) {
        this.id = id;
        this.student_id = student_id;
        this.ativity_id = ativity_id;
        this.json_r = json_r;
    }

    public AtivityRespostas() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getAtivity_id() {
        return ativity_id;
    }

    public void setAtivity_id(String ativity_id) {
        this.ativity_id = ativity_id;
    }

    public String getQuestions() {
        return json_r;
    }

    public void setQuestions(String questions) {
        this.json_r = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static JSONObject createJsonAnalitics()
    {
        JSONObject jsonAnlitics = new JSONObject();
        jsonAnlitics.put("acede_atividade", "");
        jsonAnlitics.put("acede_atividade_info", "");
        jsonAnlitics.put("responde_questoes_modulo", "");
        jsonAnlitics.put("respostas_corretas", "");
        jsonAnlitics.put("respostas_erradas", "");
        jsonAnlitics.put("percentagem_acertos", "");
        jsonAnlitics.put("recompensa", "");
        jsonAnlitics.put("recompensa_nivel_1", "");
        jsonAnlitics.put("recompensa_nivel_2", "");
        jsonAnlitics.put("concluiu_modulo", "");
        return jsonAnlitics;
    }

   
    @Override
    public String toString() {
        return "AtivityRespostas [id=" + id + ", student_id=" + student_id + ", ativity_id=" + ativity_id
                + ", questions=" + json_r + "]";
    }

    

}
