package com.estudarecompensa.ativityprovider.entities.ConfigManager;

import java.io.Serializable;

import com.estudarecompensa.ativityprovider.interfaces.IView;

public class ConfigAtivity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String viewConfig;
 


    // In this cConstructor we inject one interface for the Views
    public ConfigAtivity(IView view)
    {
    
        viewConfig = view.viewProvider("config_url");
    }

    public ConfigAtivity()
    {
        
    }


    public String getViewConfig() {
        return viewConfig;
    }



    public void setViewConfig(String viewConfig) {
        this.viewConfig = viewConfig;
    }

 
    
}
