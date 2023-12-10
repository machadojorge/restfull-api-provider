package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IView;
import com.estudarecompensa.ativityprovider.interfacesImpl.ObjectFactory;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ConfigManagerResources {

    @Autowired
    private ConfigAnalyticsParamsService service;

    //Este endpoint é apenas para test
    @GetMapping("/")
    public String returnRootView()
    {
        return "index";
    }
    
    // este endpoint é um metodo que devolve a pagina web (html) de configuração da atividade
    @GetMapping(value="/configure_ativity")
    public String returnConfigWebPage()
    {
        IView view = ObjectFactory.createViewFactory();

        return view.viewProvider("configure_ativity");
    }


    // this endpoint returns the config analitics activity
    @GetMapping(value="/analytics_ativity")
    public ResponseEntity<Map<String, List<Map<String, String>>>> findAllAnaliticsParamters()
    {
        ConfigAnalyticsAtivity analyticsParams = ConfigAnalyticsAtivity.getInstance();
        if (analyticsParams.returnListConfigAnalytics().isEmpty())
        {
            analyticsParams = service.findAll(analyticsParams);
        }
    
        return ResponseEntity.ok().body(analyticsParams.returnListConfigAnalytics());
    }
    
}
