package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IView;
import com.estudarecompensa.ativityprovider.interfacesImpl.ObjectFactory;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ConfigManagerResources {

    //this endpoint is just for testing
    @GetMapping(value="/")
    public String returnRootView()
    {
        return "index";
    }
    
    // this endpoint is a GetMethod that returns the Web page for configure the activity
    @GetMapping(value="/configure_ativity")
    public String returnConfigWebPage()
    {
        IView view = ObjectFactory.createViewFactory();

        return view.viewProvider("configure_ativity");
    }


    // this endpoint returns the config analitics activity
    @GetMapping(value="analytics_ativity")
    public ResponseEntity<Map<String, List<Map<String, String>>>> findAllAnaliticsParamters()
    {
        ConfigAnalyticsAtivity confAnalitics = new ConfigAnalyticsAtivity();
        
        return ResponseEntity.ok().body(confAnalitics.getAnalytics_list_url());
    }
    
}
