package com.estudarecompensa.ativityprovider.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.entities.ConfigManager;
import com.estudarecompensa.ativityprovider.interfaces.IView;
import com.estudarecompensa.ativityprovider.interfacesImpl.ObjectFactory;

@RestController
@RequestMapping
public class ConfigManagerResources {
    
    @RequestMapping(value="/config_url")
    public ResponseEntity<ConfigManager> returnConfigWebPage()
    {

        IView viewConfig = ObjectFactory.createViewFactory();
        System.out.println("View Config: " + viewConfig);
        ConfigManager confManager = new ConfigManager(viewConfig);
        System.out.println("config_managr: " + confManager);
        return ResponseEntity.ok().body(confManager);

    }
}
