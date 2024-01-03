package com.estudarecompensa.ativityprovider.resources;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.estudarecompensa.ativityprovider.adapter.JsonAdapter;
import com.estudarecompensa.ativityprovider.entities.ConfigManager.ConfigAnalyticsAtivity;
import com.estudarecompensa.ativityprovider.interfaces.IJson;
import com.estudarecompensa.ativityprovider.interfaces.IView;
import com.estudarecompensa.ativityprovider.interfacesImpl.ObjectFactory;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ConfigManagerResources {

    @Autowired
    // private JsonAdapter jsonObj;
    private ConfigAnalyticsParamsService service;

    //Este endpoint é apenas para test
    @GetMapping("/")
    public String returnRootView()
    {
        return "index";
    }
    
    // este endpoint é um metodo que devolve a pagina web (html) de configuração da atividade
    @GetMapping(value="/configure_ativity")
    public String returnConfigWebPage()
    {
        IView view = ObjectFactory.createViewFactory();

        return view.viewProvider("configure_ativity");
    }


     @GetMapping(value="/analytics_ativity")
    public ResponseEntity<String> findAllAnaliticsParamters_v1()
    {
        // Esta linha de código implementa um padrão singlton para a classe "ConfigAnaliticsAtivity"
        ConfigAnalyticsAtivity analyticsParams = ConfigAnalyticsAtivity.getInstance();

        // 1º Criamos um objecto da classe Adapter/Wrapper, "JSONAdapter", que implementa uma interface "IJson", 
        // esta Interface possui um unico método disponivel a todas as classes que criam 
        // um objecto desta interface do tipo de uma classe concreta, como é demonstrado na linha de código seguinte.

        // 2º É feita uma injecção de dependencias no construtor pois necessitamos deste objeto para ser possivel chamar o metodo 
        // de pesquisa à base de dados. 

        // 3º O Padrão Adapter foi implementado pois necessitavamos de alguma "classe" que fizesse a transformação dos dados, de modo a que os 
        // dados fossem compativeis com o formato esperado na resposta do Request, dos dados vindos da base de dados, que vêm em uma 
        // List<ConfigAnalyticsAtivity>, para um Objecto JSON que respeitassea estrutura do JSON pedido pelo 
        // request feito pelo Inven!RA. Isto é importante pois retira a logica de "transformação, adaptação dos dados da base de dados para 
        // a Resposta esperada pelo Inven!RA, do "Controller" (onde tem o endpoint que recebe o pedido e envia a resposta) e do Service
        // (onde vai buscar os dados à base de dados). para alem disso, faz a ligação entre estas duas classes, tornando-as compativeis
        // aos dados que se tem e que se espera enviar.
        IJson jsonObject = new JsonAdapter(service);
        
        if (analyticsParams.getAnaliticsJson().isEmpty())
        {
            // é chamado o método do adapter e esperada a resposta em formato "JSONObject".
            // Após isto, esta resposta é armazenada em um atributo da classe "ConfigAnalyticsAtivity" para de uma proxima vez que forem 
            // pedidos estes parametros, não é necessário fazer a consulta à base de dados, sendo devolvido logo este objeto. Isto se, 
            // O objecto for o mesmo e se o atributo que contem este "JSONObject" nã estiver vazio.
             JSONObject jsonAdapter = jsonObject.toJsonObject();
             analyticsParams.setAnaliticsJson(jsonAdapter);
        }
        else{
            System.out.println("Não Preciso, pois já os tenho . . . .");
        }
    
        return ResponseEntity.ok().body(analyticsParams.getAnaliticsJson().toString());
    }
    
}
