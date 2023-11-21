package com.estudarecompensa.ativityprovider.entities;

import java.io.Serializable;

import com.estudarecompensa.ativityprovider.interfaces.IView;

public class ConfigManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String viewConfig;
 


    // In this cConstructor we inject one interface for the Views
    public ConfigManager(IView view)
    {
        System.out.println("config_url");
        viewConfig = view.viewProvider("config_url");
        System.out.println(viewConfig);
    }



    public String getViewConfig() {
        return viewConfig;
    }



    public void setViewConfig(String viewConfig) {
        this.viewConfig = viewConfig;
    }

 
    
}
