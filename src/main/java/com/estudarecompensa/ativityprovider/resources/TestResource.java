package com.estudarecompensa.ativityprovider.resources;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudarecompensa.ativityprovider.entities.Test;
import com.estudarecompensa.ativityprovider.services.TestService;


// This controller goes test the class User

//This is necessary to explain that class is a controller from class User
// This controller has acced through "localhost:8080/users"
@RestController
@RequestMapping(value = "/test")
public class TestResource {
    
    // this is a dependency os the UserServide for we can receive the data from database
    @Autowired
    private TestService service;

    // o ResponseEntity é um tipo especificpo do Spring para receber recursos da Web
    // This word @GetMapping os necessary for indicate this method is 
   @GetMapping
    public ResponseEntity <List<Map<String, String>>> findAll()
    {
        //User u = new User(1L, "Maria", "maria@gmail.com", "999000999", "12345");

        // Now we create a list that receives the list of Users in the Database from "findAll()" method int the "UserService"
        // but, first we add a dependency of the userService.

       Test test = Test.getInstance();
    //     Test test2 = Test.getInstance();
     System.out.println("Test Object 1: " + test.hashCode());
       System.out.println("Test Object 1: " + test);
    //    System.out.println("Test Object 2: " + test2.hashCode());
    //    System.out.println("Test Object 2: " + test2);

   test = service.findAll( test);

    Test test2 = Test.getInstance();
        System.out.println("Test2: " + test2.hashCode());
       System.out.println("Test Object 1: " + test.hashCode());
       System.out.println("Test Object 1: " + test);


    //     for (Map<String, String> test3  : list)
    //     {
    //         for(Entry<String, String> entry : test3.entrySet())
    //         {
    //               System.out.println("test 3: " + entry.getKey());
    //         System.out.println("Test 3: " + entry.getValue());
    //         }
          
    //     }
       
        // In the Header goes the Ok, and the body goes the list of users
        return ResponseEntity.ok().body(test.returnMap());
    }

    // // in this case, we go passe one parameter to url
    // @GetMapping(value = "/{id}")
    // // this "PathVariable" goes say to Spring that variable in method is the variable passed in URL
    // public ResponseEntity<User> findById(@PathVariable Long id)
    // {
    //     User obj = service.findById(id);
    //     return ResponseEntity.ok().body(obj);
    // }


    // /// POST Method to insert a new user in the BD
    // @PostMapping
    // public ResponseEntity<User> insert(@RequestBody User obj) // this anotations indicates that receives a object in the body in the type json and
    // // this object will goes deserializer to a object java
    // {
    //     obj = service.insert(obj);
    //     // in this case we returns, after insert a new user, the response status
    //     // for that, we need creates a header of type URI
    //     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    //     return ResponseEntity.created(uri).body(obj);

    // }


    // // DELETE Method
    // @DeleteMapping(value="/{id}")
    // public ResponseEntity<Void> delete(@PathVariable Long id)
    // {
    //     service.delete(id);
    //     // this method return a respponse with no body
    //     return ResponseEntity.noContent().build();
    // }

    // /// UPDATE
    // @PutMapping(value="/{id}")
    // public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj)
    // {
    //     obj = service.update(id, obj);
    //     return ResponseEntity.ok().body(obj);
    // }
}
