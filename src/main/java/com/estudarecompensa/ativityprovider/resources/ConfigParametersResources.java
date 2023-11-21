package com.estudarecompensa.ativityprovider.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.entities.ConfigParameters;

@RestController
@RequestMapping
public class ConfigParametersResources {

    @RequestMapping(value="/json_params_url")
    public ResponseEntity<ConfigParameters> findAllParameters()
    {
        ConfigParameters confPar = new ConfigParameters(1L, "What is the Question?", "I Don't kown", "This is a question?");
        return ResponseEntity.ok().body(confPar);
        
    }
    
}
