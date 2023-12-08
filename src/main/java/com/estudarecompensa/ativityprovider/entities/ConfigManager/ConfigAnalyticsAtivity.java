package com.estudarecompensa.ativityprovider.entities.ConfigManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static Map<String, List<Map<String, String>>> analytics_list_url;
    private static List<Map<String, String>> quantAnalytics;
    private static List<Map<String, String>> quanlAnalytics;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private int analytics_type;
    // Neste caso: 
    // 1 - parametros Quantitativos
    // 2 - parametros Qualitativos


    private ConfigAnalyticsAtivity()
    {
       analytics_list_url = new HashMap<String, List<Map<String, String>>>();
       quantAnalytics = new ArrayList<>();
       quanlAnalytics  = new ArrayList<>();
    
    }
    
// Implementação do Singlton a travez do methodo getInstance()
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

    public Map<String,List<Map<String,String>>> returnListConfigAnalytics()
    {
        return analytics_list_url;
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", atributo=" + name + ", tipo=" + type + "]";
    }

    public void addToQualityList(Map<String, String> map)
    {
        quanlAnalytics.add(map);
    }

    public void addToQuantityList(Map<String, String> map)
    {
        quantAnalytics.add(map);
    }

    public void addAnalyticsListToMap()
    {
        analytics_list_url.put("qualAnalytics", quanlAnalytics);
        analytics_list_url.put("quantAnalytics", quantAnalytics);
    }
    
}
