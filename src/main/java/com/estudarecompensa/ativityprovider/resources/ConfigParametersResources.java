package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.entities.ConfigParameters;

@RestController
@RequestMapping
public class ConfigParametersResources {

    // this endpoint return the json_params_url
    @GetMapping(value="/configure_params_ativity")
    public ResponseEntity<List<Map<String, String>>> findAllParameters()
    {
        ConfigParameters confPar = new ConfigParameters();
        return ResponseEntity.ok().body(confPar.getJson_params_url());  
    }
    
}
