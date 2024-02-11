package com.estudarecompensa.ativityprovider.resources;

import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.estudarecompensa.ativityprovider.abstractClass.AbstractDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.InsertDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.SearchDBOperation;
import com.estudarecompensa.ativityprovider.abstractClass.UpdateDBOperation;
import com.estudarecompensa.ativityprovider.adapter.AtivityLogicAdapter;
import com.estudarecompensa.ativityprovider.entities.AtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IConfigAnalyticsParams;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DeployActivityResources {

    @Autowired 
    private IAtivityPerguntas ServicePergunt;
    
    @Autowired
    private IAtivityRespostas serviceRespost;

    @Autowired 
    private IConfigAnalyticsParams analyticParam;
    
    /**
     * Neste GET apenas devolve o URL da atividade, para os estudantes poderem aceder
     * Isto ocorre antes do Inven!RA fazer o post com os parametros da atividade e o AtivityID e studentID
     * @param id é o Ativity ID, só será armazenado no POST para este método
     * @return URL com o ID da atividade para onde os estudantes vão ter que aceder
     */
    @RequestMapping(value="/deploy_ativity", method=RequestMethod.GET)
    public ResponseEntity<String> InstanceActivity(@RequestParam String id)
    {
     
        String url = "https://estudo-recompensa.onrender.com/ativity_provider?id="+ id;
        return ResponseEntity.ok().body(url);
    }


    /**
     * Este método já é o POST com os parametros de configuração da atividade, a partir daqui irá ser armazenado na base de dados nas
     * Tabelas AtiviityPerguntas -> as perguntas vindas aqui, é o JSON
     * Na tabela AtivityResposta -> Os parametros das respostas -> tambem vai se buscar o JSON ao ConfigAnaliticsParams
     * @param payload parametros de configuração de uma atividade, as perguntas
     * @return URL para a view com as perguntas e frontend
     */
    @RequestMapping(value="/ativity_provider", method=RequestMethod.POST)
    public ResponseEntity<String> InitializeAtivity(@RequestBody Map<String, Object> payload)
    {
      
        // 1º Tabela das PerguntasAtivity -> Verifica se existe se não, adiciona
        AbstractDBOperation operationQuestions = new InsertDBOperation(ServicePergunt, payload);
        JSONObject obj = operationQuestions.executeAction();
    

        // verificar se o configAnaliticsParamests tem registos no objecto
        AbstractDBOperation operationAnalytics = new SearchDBOperation(analyticParam);
        JSONObject objMessage = operationAnalytics.executeAction();
    
        // 2º Respostas ativity - Verifica se existe, senao, adiciona
        AbstractDBOperation operationResponses = new InsertDBOperation(serviceRespost, payload);
        obj = operationResponses.executeAction();
      
        String url = "https://estudo-recompensa.onrender.com/start_ativity/" + (String) payload.get("activityID") + "/" + (String) payload.get("Inven!RAstdID");
        return ResponseEntity.ok().body(url);
    }

    // Metodo que vai devolver a página da atividade para ser Resolvida
    // Ainda não se encontra Operacional
    @GetMapping(value="/ativity/{ativity_id}/{student_id}")
    public String showActivyty(@PathVariable String ativity_id, @PathVariable String student_id, Model model)
    {
        model.addAttribute("ativity_id", ativity_id.toString());
        model.addAttribute("student_id", student_id.toString());

        return "atividade";
    }

    // Post que vai receber as respostas dadas pelos utilizadores à atividade!
    // Ainda em desenvolvimento
   @PostMapping(value="/ativity/{ativity_id}/{student_id}")
    public ResponseEntity<String> sendAtivityPage(@PathVariable String ativity_id, @PathVariable String student_id,  
                                                                                    @RequestParam("solucao_1") String solucao_1,
                                                                                    @RequestParam("solucao_2") String solucao_2,
                                                                                    @RequestParam("solucao_3") String solucao_3,
                                                                                    @RequestParam("solucao_4") String solucao_4,
                                                                                    Model model)  
    {
        Map<String, String>mapRes = new LinkedHashMap<>();
        // mapRes.put("activityID", ativity_id.toString());
        // mapRes.put("Inven!RAstdID", student_id.toString());
        // mapRes.put("solucao_1", solucao_1.toString());
        // mapRes.put("solucao_2", solucao_2.toString());
        // mapRes.put("solucao_3", solucao_3.toString());
        // mapRes.put("solucao_4", solucao_4.toString());

        // teste
        mapRes.put("activityID","01234");
        mapRes.put("Inven!RAstdID", "56789");
        mapRes.put("solucao_1","aaaa");
        mapRes.put("solucao_2","bbbb");
        mapRes.put("solucao_3","cccc");
        mapRes.put("solucao_4","dddd");
      

        AtivityLogicAdapter logic = new AtivityLogicAdapter(ServicePergunt, serviceRespost, mapRes);
        boolean bl = logic.checkQuestions();
        if (bl)
        {
            System.out.println("Ganhou");
        }
        else
            System.out.println("Não ganhou");        
       // Este é o endpoint onde submete a atividade para avaliação!
        return ResponseEntity.ok().body("A Atividade ainda não Se encontra pronta para a sua Resolução! Obrigado Por tentares Submeter algo mas ainda não estamos prontos para isso! Obrigado pela Paciência!");
    }

    @GetMapping("/start_ativity/{ativity_id}/{student_id}")
    public String informationOrAtivity(@PathVariable String ativity_id, @PathVariable String student_id, Model model)
    {
        model.addAttribute("ativity_id", ativity_id.toString());
        model.addAttribute("student_id", student_id.toString());
      
       
        return "formulario";
    }

    @GetMapping("/information/{ativity_id}/{student_id}")
    public String information(@PathVariable String ativity_id, @PathVariable String student_id, Model model)
    {
        model.addAttribute("ativity_id", ativity_id.toString());
        model.addAttribute("student_id", student_id.toString());
      
        Map<String, String> mapTemp = new LinkedHashMap();
        mapTemp.put("activityID", ativity_id.toString());
        mapTemp.put("Inven!RAstdID", student_id.toString());
    
        // verificar se o configAnaliticsParamests tem registos no objecto
        AbstractDBOperation operationAnalytics = new SearchDBOperation(serviceRespost, mapTemp);
        JSONObject objMessage = operationAnalytics.executeAction();

        // converte para um JSON Object para poder atualizar os campos de acesso à atividade
        String newString = (objMessage.get("questions").toString()).replace('=', ':');
        JSONObject objtemp = new JSONObject(newString);
    
        objtemp.put("acede_atividade", "true");
        objtemp.put("acede_atividade_info", "true");
     
        AtivityRespostas resp = new AtivityRespostas(Long.parseLong(objMessage.get("id").toString()), student_id.toString(), ativity_id.toString(),objtemp.toString());
        // mando atualizar
        AbstractDBOperation operationUpdate = new UpdateDBOperation(serviceRespost, resp);
        objMessage = operationUpdate.executeAction();
        System.out.println("OBJ UPDATE -> ID: " + objMessage);

        return "information";
    }

}

