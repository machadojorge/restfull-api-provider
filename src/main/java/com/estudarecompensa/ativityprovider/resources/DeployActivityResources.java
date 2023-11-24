package com.estudarecompensa.ativityprovider.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DeployActivityResources {

    @RequestMapping(value="/deploy_ativity", method=RequestMethod.GET)
    public ResponseEntity<String> InstanceActivity(@RequestParam String id)
    {
        String id_ativity = "12345";
        String url = "http://<domain>/ativity_provider?id="+ id_ativity;
        return ResponseEntity.ok().body(url);
    }

    @RequestMapping(value="/ativity_provider", method=RequestMethod.POST)
    public ResponseEntity<String> InitializeAtivity(@RequestBody String payload)
    {
        System.out.println(payload);
        String id = "12345";
        String url = "This is a Web page with the Params received in POST from the inven!RA";
        return ResponseEntity.ok().body(url);
    }
    
}
