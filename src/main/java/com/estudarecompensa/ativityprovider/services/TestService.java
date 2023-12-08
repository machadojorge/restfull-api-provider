package com.estudarecompensa.ativityprovider.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.estudarecompensa.ativityprovider.repositories.TestRepository;
// import com.estudarecompensa.ativityprovider.services.exceptions.DataBaseException;
// import com.estudarecompensa.ativityprovider.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import com.estudarecompensa.ativityprovider.entities.Test;
// import com.estudarecompensa.ativityprovider.entities.User;

@Service
public class TestService {
    
     @Autowired
    private TestRepository repository;

    // creates a method that return all users in the DB
    public Test findAll(Test t)
    {
      
           
    //    t = repository.findById(1L).orElse(null);
        // return t;
    //    System.out.println("Tipo do Object(): " + t.hashCode());
        List<Test> tt = repository.findAll();
        t.addToMap(tt);
        t.setAtributo(tt.get(0).getAtributo());
        t.setTipo(tt.get(0).getTipo());
        System.out.println("Lista: " + tt.get(0).getAtributo());
        t.showMap();
        // t.setList(tt);
         //System.out.println("Lista: " + t.getList());
    //     // List<Test> list = repository.findAll();
    //     // Test a = Test.getInstance();
    //     // a.setAtributo(list.get(0).getAtributo());
    //     // a.setTipo(list.get(0).getTipo());

         return t;

        // Now we go to the UserResource, the User Controller, we change the findAll method
    }
    
}
