package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.interfaces.IConfigParametersService;


@RestController
@RequestMapping
public class ConfigParametersResources {

    @Autowired
    private IConfigParametersService service;

    // this endpoint return the json_params_url
    @GetMapping(value="/configure_params_ativity")
    public ResponseEntity<List<Map<String, String>>> findAllParameters()
    {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        AbstractDBOperation operation = new SearchDBOperation(service);
        JSONObject obj = operation.executeAction();
      
        // Convertendo o JSONArray para List<Map<String, String>>
        @SuppressWarnings("unchecked")
        List<Map<String, String>> listaDeMapas = (List<Map<String, String>>) 
            (List<?>) obj.getJSONArray("params").toList();
        return ResponseEntity.ok().body(listaDeMapas);  
    }
}
