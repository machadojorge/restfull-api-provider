package com.estudarecompensa.ativityprovider.abstractClass;

import org.json.JSONObject;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityPerguntas;
import com.estudarecompensa.ativityprovider.interfaces.IAtivityRespostas;
import com.estudarecompensa.ativityprovider.interfaces.IConfigAnalyticsParams;
import com.estudarecompensa.ativityprovider.interfaces.IConfigParametersService;
import com.estudarecompensa.ativityprovider.utils.CheckExist;


public abstract class AbstractDBOperation <T, M>{

    // Atributos Genericos para armazenar as classes dos Serviços e
    // Objetos/Strings para as pesquisas e inserts à base de dados
    // São partilhados pelas duas classes que herdam desta classe
    protected T service;
    protected M objectInstance;


    // Construtor da Classe Base recebendo apenas a classe do Serviço pertendido
    // as classes que Herdam desta classe, chamam este construtor, ou a subercarga
    // do mesmo. 
    protected AbstractDBOperation( T service) {
         this.service = service;
       
    }


    // Construtor da Classe Base recebendo o Serviço que pretende usar para aceder à
    // Base de dados e o Ojeto que pretende Armazenar ou pesquisar
    protected AbstractDBOperation( T service, M objectInstance) {
          this.service = service;
          this.objectInstance = objectInstance;
    }


    protected T getService() {
        return service;
    }

    protected M getObjectInstance() {
        return objectInstance;
    }


    // Este método é o Método implementado do Paddrão Template Metodo, que define
    // A ordem pela qual o Algoritmo é implementado, tendo disponiveis os metodos 
    // abastratos para que as classes que Herdem desta classe Template Metodo, consigam
    // fazer a sua propria implementação ou, subscrever algum metodo.
    // Este método foi pensado pois é importante defenir uma Ordem pela qual o 
    // algoritmo que realiza operações à base de dados ocorre, havendo codigo
    // que pode ser partilhado, implementado na classe Base e reutilizado pelas
    // classes que herdam, evitando assim duplicação de código.
    // As etapas identificadas foram:
    // 1º Injetar o Serviço, mais o Objecto se fosse necessário
    // 2º Criar o map<String, Mathod> para mapear as chaves (String) para a fazer execução dos respetivos metodos
    // 3º Verificar a que classe o objeto pertense, pois queria que a classe fosse generica
    // de modo a que a mesma classe recebesse objetos de serviços diferentes, podendo assim reaproveitar o codigo.
    // Desta forma tambem conseguimos garantir um fraco acuplamento entre as classes
    // Concentrando tudo isso na classe que efetua as pesquisas ou os insertes, que são 
    // as unicas que conhecem as classes dos serviços e entidades pois necessitam de comunicar com os serviços.
    // 4º Executar a operação, sendo ela pesquisar ou inserir
    // 5º Devolver sempre um JSON Object Pronto para ser devolvido como resposta 
    // ao pedido do feito à API. 
    public JSONObject executeAction()
    {
        JSONObject result = new JSONObject();
      
        createMethodMap();
        String className = checkInstance();
        JSONObject obj = executeOperation(className);
        if (CheckExist.checkReturnObject(obj))
        {
           return obj;
        }
        return result; 
    }


    // Este método vai fazer a verificação da instancia, a que classe Service pertence
    // após verificar essa instancia, vai devolver a chave do metodo correspondente a essa classe
    // do mapa. Alguns Services Permitem pesquisas com condições parametro "M" 
    // Este método é implementado na Classe Base, pois para evitar a duplicação de código nas classes 
    // que herdam desta classe, pois assim, não tem que ser feita esta verificação em ambas
    // as classes, o que evita duplicação de código
    protected String checkInstance() {
        if (this.getService() instanceof IConfigAnalyticsParams)
        {   System.out.println("Service: " + this.service);
            System.out.println("Instance of: ConfigAnalyticsParamsService");
            return "getAllConfigAnalitics";
        }

        if (this.getService() instanceof IConfigParametersService)
        {
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: ConfigParamsService");
            return "getAllConfigParams";

        }
        if(this.getService() instanceof IAtivityPerguntas)
        {
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: AtivityPerguntas");
            return "addPerguntas";
        }
        if(this.getService() instanceof IAtivityRespostas && this.getObjectInstance() instanceof String)
        {
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: AtivityRespostasAnaliticsResult");
            return "getAnaliticsResult";
        }
        if(this.getService() instanceof IAtivityRespostas)
        {
            System.out.println("Service: " + this.service);
            System.out.println("Instance of: AtivityRespostas");
            return "addRespostas";
        }

        return new String();
    }

    

    // Metodo abstrato que as classes são Obrigadas a implementar para conseguirem
    // adicionar os metodos ao mapa para fazer o mapeamento "String -> Metodo"
    protected abstract void createMethodMap();
    
    // metodo chamado pelo controller que vai devolver um JSONObject
    //implementado na classe que vai fazer as pesquisas
    protected abstract JSONObject executeOperation(String value);
    


    // // verificar se o registo contem informação antes de ser devolvido ao controller
    // protected boolean checkReturnObject(JSONObject obj)
    // {
    //     if (obj!= null && !obj.isEmpty())
    //             return true;
    //     return false;
    // }
    
}
