package com.estudarecompensa.ativityprovider.entities.ConfigManager;

import org.json.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_config_analytics")
public class ConfigAnalyticsAtivity {

    private static final long serialVersionUID = 1L;

    private static ConfigAnalyticsAtivity instance;

    // JSON Object que vai guardar os parametros Analiticos de configuração vindos da base de dados
    private static JSONObject analiticsJson = new JSONObject();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private int analytics_type;


    private ConfigAnalyticsAtivity()
    {
    
    }
    
// Implementação do Singlton atravez do methodo getInstance()
// Foi aplicado o mesmo metodo de implementação do Singleton que na classe 
// "ConfigParameters.java"
     public static ConfigAnalyticsAtivity getInstance()
    {
        ConfigAnalyticsAtivity result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ConfigAnalyticsAtivity.class) {
            if (instance == null) {
                instance = new ConfigAnalyticsAtivity();
            }
            return instance;
        }
    }


    public static long getSerialversionid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return name;
    }

    public void setAttribute(String attribute) {
        this.name = attribute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeOfAnalyses()
    {
        return analytics_type;
    }

    
    public JSONObject getAnaliticsJson() {
        return analiticsJson;
    }

    public void setAnaliticsJson(JSONObject analiticsJson) {
        ConfigAnalyticsAtivity.analiticsJson = analiticsJson;
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", atributo=" + name + ", tipo=" + type + "]";
    }

    
}
