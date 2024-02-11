package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.adapter.JsonAdapter;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IConfigAnalyticsParams;
import com.estudarecompensa.ativityprovider.interfaces.IJson;
import com.estudarecompensa.ativityprovider.interfaces.IView;
import com.estudarecompensa.ativityprovider.interfacesImpl.ObjectFactory;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ConfigManagerResources {

    @Autowired
    private IConfigAnalyticsParams service;
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


     @GetMapping(value="/analytics_ativity")
    public ResponseEntity<String> findAllAnaliticsParamters()
    {
        // Criar o Objeto da classe Base do template metodo do tipo da 
        // classe responsavel pelas consultas
        AbstractDBOperation operation = new SearchDBOperation(service);
        // receber a resposta da consulta já no formato esperado
        JSONObject obj = operation.executeAction();
    
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
            .body(obj.get("params").toString());
    }
    
}
