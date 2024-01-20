package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.InsertDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDao;
import com.estudarecompensa.ativityprovider.interfaces.IConfigAnalyticsParams;
import com.estudarecompensa.ativityprovider.interfaces.IConfigParametersService;
import com.estudarecompensa.ativityprovider.interfaces.IDeploy;
import com.estudarecompensa.ativityprovider.utils.CheckExist;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DeployActivityResources {
    @Autowired
    private IDeploy serviceDeploy;
    @Autowired
    private IAnaliticDao serviceDao;
    @Autowired
    private IConfigAnalyticsParams service;
    @Autowired
    private IConfigParametersService serviceParams;

    @RequestMapping(value="/deploy_ativity", method=RequestMethod.GET)
    public ResponseEntity<String> InstanceActivity(@RequestParam String id)
    {
        String id_ativity = "12345";
        String url = "https://estudo-recompensa.onrender.com/ativity_provider?id="+ id_ativity;
        return ResponseEntity.ok().body(url);
    }

    @RequestMapping(value="/ativity_provider", method=RequestMethod.POST)
    public ResponseEntity<String> InitializeAtivity(@RequestBody Map<String, Object> payload)
    {
        // Quando Receber este Post, vou guardar na base de dados estes valores, para depois guardar os valores da atividade
        System.out.println(payload);
        String student_id = (String) payload.get( "Inven!RAstdID");
        String ativity_id = (String) payload.get("activityID");
        System.out.println("String : " + ativity_id);
        Map<String, Object> map = (Map<String, Object>)payload.get("json_params");
        System.out.println(map.get("s1"));
        boolean result = false;
        // 1º Guardar o Activity ID da atividade
        List<DeployActivity> activities = (List<DeployActivity>) serviceDeploy.getAllDeployInstance();
        if(!CheckExist.existInDatabase(activities, ativity_id))
        {
            result = serviceDeploy.saveValues(new DeployActivity(ativity_id));
            System.out.println("Deploy Record Saved: " + result);
        }
        else
        {
             System.out.println("Deploy Record Saved: " + result);
        }
        
        // 2º Guardar nos parametros analiticos da atividade o ativity_id e o student_id
        List<AnaliticDao> analiticDaos = (List<AnaliticDao>) serviceDao.getAllDeployInstance();
        if(!CheckExist.existAnaliticsInDatabase(analiticDaos, ativity_id, student_id))
        {
            result = serviceDao.saveValues(new AnaliticDao(ativity_id, student_id));
            System.out.println("Analitics Record Saved: " + result);
        }
        else{
             System.out.println("Analitics Record Saved: " + result);
        }
        
        // 2º Guardar os parametros de configuração da atividade


        //complete
        // 3º  Devolver o URL com os dois parametros {id_atividade}/{student_id}

        //Vai devolver um URL que vai ser usado em um get para devolver a pagina web
        // vai ser usado em um post, onde vamos simular os dados que o utilizador devolve
        String url = "https://estudo-recompensa.onrender.com/ativity/" + ativity_id + "/" + student_id;
        return ResponseEntity.ok().body(url);
    }

   @PostMapping(value="/ativity/{ativity_id}/{student_id}")
    public ResponseEntity<String> sendAtivityPage(@PathVariable String ativity_id, @PathVariable String student_id, @RequestBody Map<String, Object> map)
    {

        // Este pesquisar por atividade e aluno já esta pronto, 
        String id_ativity = ativity_id;
        String student = student_id;
        
        List<DeployActivity> list = (List<DeployActivity>) serviceDeploy.getAllDeployInstance();

        for(DeployActivity act : list)
        {
            System.out.println(act.toString());
        }
      
       // Este é o endpoint onde submete a atividade para avaliação!
        return ResponseEntity.ok().body("ESTOU AQUI");
    }

       @RequestMapping(value="/test", method=RequestMethod.GET)
    public ResponseEntity<String> testAtivity() throws NoSuchMethodException, SecurityException
    {
    //     System.out.println("Service: " + service);
    //     List<ConfigAnalyticsAtivity> configAnalytics = service.getAllAnalyticsParams();
    //     System.out.println("Cheguei aqui--------"+ configAnalytics);
    //     AbstractDBOperation operation = new SearchDBOperation(service);
    //    JSONObject obj =  operation.executeAction();
    //    System.out.println(obj);
    //   //System.out.println(analyticsParams.getAnaliticsJson());
        JSONObject objtparams = new JSONObject();
        objtparams.put("StudentID", "This string is the student Inven!RA ID");
        objtparams.put("InstanceID", "This string is the Inven!RA activity ID257");
        AbstractDBOperation operation = new SearchDBOperation(serviceDao, objtparams);//serviceDao,"This string is the Inven!RA activity ID257" );
        JSONObject obj = operation.executeAction();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(obj.toString());
    }

       @RequestMapping(value="/test_2", method=RequestMethod.GET)
    public ResponseEntity<String> insertTest()
    {
        System.out.println("Service: " + service);
        DeployActivity ativity = new DeployActivity("TestDeploy");
        List<ConfigAnalyticsAtivity> configAnalytics = service.getAllAnalyticsParams();
        System.out.println("Cheguei aqui--------"+ configAnalytics);
        AbstractDBOperation operation = new InsertDBOperation(serviceDeploy, ativity);
       JSONObject obj =  operation.executeAction();
       System.out.println(obj.toString());
      //System.out.println(analyticsParams.getAnaliticsJson());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(obj.toString());
    }

}
