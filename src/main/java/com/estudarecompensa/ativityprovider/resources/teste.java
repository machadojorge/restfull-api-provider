// package com.estudarecompensa.ativityprovider.resources;

// import java.util.List;
// import java.util.Map;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;

// import com.estudarecompensa.ativityprovider.adapter.AtivityLogicAdapter;
// import com.estudarecompensa.ativityprovider.entities.AtivityPerguntas;
// import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
// import com.estudarecompensa.ativityprovider.entities.ConfigParameters;
// import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
// import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;
// import com.estudarecompensa.ativityprovider.interfaces.IConfigParametersService;


// @RestController
// @RequestMapping
// public class teste {

//     @Autowired
//     private IAtivityPerguntas service;
//     @Autowired
//     private IAtivityRespostas serviceR;

//     // this endpoint return the json_params_url
//     @GetMapping(value="/teste")
//     public ResponseEntity<String> findAllParameters()
//     {
//     // AtivityPerguntas ap = new AtivityPerguntas();
//     //     {
//     //         ap = (AtivityPerguntas)service.findByativityStudent("6789", "12345");
//     //     }
//     //     JSONObject obj = new JSONObject(ap);
//     //     System.out.println("AP: " + ap);
//     //     System.out.println("obj: " + obj.get("questions"));
//     //     JSONObject quest = new JSONObject(obj.get("questions").toString());
//     //     System.out.println("Question: " + quest.get("p1"));
//         JSONObject objTest = new JSONObject();
//         objTest.put("ativity_id", "5678");
//         objTest.put("student_id", "1234");

//         JSONObject temp = new JSONObject();
//         temp.put("q1", "true");
//         temp.put("q2", "false");
//         objTest.put("questions", temp);
        

//        AtivityLogicAdapter adapter = new AtivityLogicAdapter(service, serviceR); 
//       boolean result =  adapter.CheckResponses(objTest);
//       if (result)
//         return ResponseEntity.ok().body("PArabens");
//     else
//         return ResponseEntity.ok().body("Não acertaste");
//     }

//     @GetMapping(value="/testeR")
//     public ResponseEntity<String> findAllParametersR()
//     {
//     AtivityRespostas ap = new AtivityRespostas();
//         {
//             ap = (AtivityRespostas)serviceR.findByativityStudent("5678", "1234");
//         }
        
//         System.out.println("AP: " + ap);
//         JSONObject obj = new JSONObject(ap);
//         System.out.println("obj: " + obj.get("questions"));
//         JSONObject quest = new JSONObject(obj.get("questions").toString());
//         System.out.println("Question: " + quest.get("r1"));
//         return ResponseEntity.ok().body(ap.toString());  
//     }
    
// }

