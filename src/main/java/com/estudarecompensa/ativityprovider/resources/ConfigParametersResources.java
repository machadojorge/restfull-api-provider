package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.estudarecompensa.ativityprovider.entities.ConfigParameters;
import com.estudarecompensa.ativityprovider.services.ConfigParametersService;


@RestController
@RequestMapping
public class ConfigParametersResources {

    @Autowired
    private ConfigParametersService service;

    // this endpoint return the json_params_url
    @GetMapping(value="/configure_params_ativity")
    public ResponseEntity<List<Map<String, String>>> findAllParameters()
    {
        ConfigParameters paramsConfig = ConfigParameters.getInstance();
        paramsConfig = service.findAll(paramsConfig);
        return ResponseEntity.ok().body(paramsConfig.returnListConfigParams());  
    }
    
}
