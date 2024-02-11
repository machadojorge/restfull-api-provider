package com.estudarecompensa.ativityprovider.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_json_perguntas")
public class AtivityPerguntas {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String student_id;
    private String ativity_id;
    private String json_p;
    
    public AtivityPerguntas(String student_id, String ativity_id, String questions) {
        this.student_id = student_id;
        this.ativity_id = ativity_id;
        this.json_p = questions;
    }

    public AtivityPerguntas() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public Long getId() {
        return id;
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
        return json_p;
    }

    public void setQuestions(String questions) {
        this.json_p = questions;
    }

    @Override
    public String toString() {
        return "AtivityPerguntas [id=" + id + ", student_id=" + student_id + ", ativity_id=" + ativity_id
                + ", questions=" + json_p + "]";
    }

    

}
