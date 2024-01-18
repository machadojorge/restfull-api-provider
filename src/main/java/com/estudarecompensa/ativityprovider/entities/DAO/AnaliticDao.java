package com.estudarecompensa.ativityprovider.entities.DAO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table (name = "tb_analitycs_saved")
public class AnaliticDao {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ativity_instance;
    private String student_id;
    //Boolean
    private String acede_atividade = " ";
    //Boolean
    private String acede_atividade_info = " ";
    //Boolean
    private String responde_questoes_modulo = " ";
    //Integer
    private String respostas_corretas = " ";
    //Integer
    private String respostas_erradas = " ";
    //Float
    private String percentagem_acertos = " ";
    //Boolean
    private String recompensa = " ";
    //Boolean
    private String recompensa_acertos = " ";
    //Boolean
    private String recompensa_nivel_1 = " ";
    //Boolean
    private String recompensa_nivel_2 = " ";
    //Boolean
    private String concluiu_modulo = " ";

    public AnaliticDao()
    {

    }

    public AnaliticDao(String ativity_instance, String student_id) {
        this.ativity_instance = ativity_instance;
        this.student_id = student_id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtivity_instance() {
        return ativity_instance;
    }

    public void setAtivity_instance(String ativity_instance) {
        this.ativity_instance = ativity_instance;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    @Override
    public String toString() {
        return "AnaliticDao [id=" + id + ", ativity_instance=" + ativity_instance + ", student_id=" + student_id
                + ", acede_atividade=" + acede_atividade + ", acede_atividade_info=" + acede_atividade_info
                + ", responde_questoes_modulo=" + responde_questoes_modulo + ", respostas_corretas="
                + respostas_corretas + ", respostas_erradas=" + respostas_erradas + ", percentagem_acertos="
                + percentagem_acertos + ", recompensa=" + recompensa + ", recompensa_acertos=" + recompensa_acertos
                + ", recompensa_nivel_1=" + recompensa_nivel_1 + ", recompensa_nivel_2=" + recompensa_nivel_2
                + ", concluiu_modulo=" + concluiu_modulo + "]";
    }
    
}
