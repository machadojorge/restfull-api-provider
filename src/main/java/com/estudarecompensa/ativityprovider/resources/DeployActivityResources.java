package com.estudarecompensa.ativityprovider.resources;

import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.InsertDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.interfaces.IAnaliticDaoService;
import com.estudarecompensa.ativityprovider.interfaces.IDeploy;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DeployActivityResources {
    @Autowired
    private IDeploy serviceDeploy;
    @Autowired
    private IAnaliticDaoService serviceDao;


    @RequestMapping(value="/deploy_ativity", method=RequestMethod.GET)
    public ResponseEntity<String> InstanceActivity(@RequestParam String id)
    {
        AbstractDBOperation operation = new SearchDBOperation(serviceDeploy, id);
        JSONObject obj = operation.executeAction();
        String url = "https://estudo-recompensa.onrender.com/ativity_provider?id="+ id;
        return ResponseEntity.ok().body(url);
    }


    // Este endpoint vai chamar as classes do Template Method
    @RequestMapping(value="/ativity_provider", method=RequestMethod.POST)
    public ResponseEntity<String> InitializeAtivity(@RequestBody Map<String, Object> payload)
    {
        // Este endpoint vai receber um POST com todos os parametros necessários para armazenar dados da atividade
        // Irá enviar o "activityID", o "Inven!RAstdID", e o "json_params". Tudo isto informações que serão
        // armazenadas na base de dados
        // Será feita uma verificação se já existe um registo deste utilizador nesta atividade
        // caso não haja, adiciona uma nova, caso haja, não adiciona pois já existe atividade aberta
        // No final vai devolver um URL para para a resolução da atividade 
     
     
        // 1º Extrair os valores da Valores dos IDS do Body do POST para fazer a pesquisa
        // por essa atividade e esse utilizador
        JSONObject instanceStudent = new JSONObject();
        instanceStudent.put("InstanceID",(String) payload.get("activityID"));
        instanceStudent.put("StudentID",(String) payload.get("Inven!RAstdID"));

        //2º Verifica se existe esse registo dessa atividade e desse aluno na base de dados
        // se não existir, adiciona, é onde se vai guardar os resultados
        AbstractDBOperation operation = new SearchDBOperation(serviceDao, instanceStudent);
        JSONObject obj = operation.executeAction();
        System.out.println("Message Received: " + obj.toString());

   
        // 3º Se a Resposta for vazia, significa que o registo não existe na base de dados, logo é um novo registo
        // adiciona um novo registo na base de dados para guardar os analiticos 
        // do aluno nesta atividade
        if (obj.isEmpty()) {
            // Inserir aqui para guardar analiticos por atividade
            AbstractDBOperation insertOp = new InsertDBOperation(serviceDao, instanceStudent);
            JSONObject objOP = insertOp.executeAction();
            System.out.println("Registo guardado: " + objOP);
        }

        
        // 4º  Devolver o URL com os 
        //Vai devolver um URL que vai ser usado em um get para devolver a pagina web
        // para a resoluçaõa da atividade, o url vai conter os parametros ara identificar a atividad e o student
        // dois parametros {id_atividade}/{student_id}
        String url = "https://estudo-recompensa.onrender.com/ativity/" + (String) payload.get("activityID") + "/" + (String) payload.get("Inven!RAstdID");
        return ResponseEntity.ok().body(url);
    }

    // Metodo que vai devolver a página da atividade para ser Resolvida
    // Ainda não se encontra Operacional
    @GetMapping(value="/ativity/{ativity_id}/{student_id}")
    public ResponseEntity<String> showActivyty(@PathVariable String ativity_id, @PathVariable String student_id)
    {

        // vai ter a logica do ativity Provider
        String id_ativity = ativity_id;
        String student = student_id;
        

        
       // Este é o endpoint onde submete a atividade para avaliação!
        return ResponseEntity.ok().body("A Atividade ainda não Se encontra pronta para a sua Resolução! Obrigado pela Paciência!");
    }

    // Post que vai receber as respostas dadas pelos utilizadores à atividade!
    // Ainda em desenvolvimento
   @PostMapping(value="/ativity/{ativity_id}/{student_id}")
    public ResponseEntity<String> sendAtivityPage(@PathVariable String ativity_id, @PathVariable String student_id, @RequestBody Map<String, Object> map)
    {

        // vai ter a logica do ativity Provider
        String id_ativity = ativity_id;
        String student = student_id;
        

        
       // Este é o endpoint onde submete a atividade para avaliação!
        return ResponseEntity.ok().body("A Atividade ainda não Se encontra pronta para a sua Resolução! Obrigado Por tentares Submeter algo mas ainda não estamos prontos para isso! Obrigado pela Paciência!");
    }

    
}
