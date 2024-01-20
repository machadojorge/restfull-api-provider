package com.estudarecompensa.ativityprovider.abstractClass;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public abstract class AbstractDBOperation <T, M>{

    // Atributos Genericos para armazenar as classes dos Serviços e
    // Objetos/Strings para as pesquisas à base de dados
    // São partilhados pelas duas classes que herdam desta classe
    protected T service;
    protected M objectInstance;

     // Mapa com os metodos atribuidos a cada classe especifica
    // deste modo, não é necessário andar a percorrer sempre a lista 
    // quando se identifica a instancia de uma classe
    // As chaves e os metodos vão ser adicionados no construtor
    protected Map<String, Method> methodMap = new HashMap<String, Method>();


    // Construtor da Classe Base recebendo apenas a classe do Serviço pertendido
    protected AbstractDBOperation( T service) {
         this.service = service;
       
    }


    // Construtor da Classe Base recebendo o Serviço que pretende usar para aceder à
    // Base de dados e o Ojeto que pretende Armazenar ou atualizar
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
    // que pode ser partilhado. As etapas identificadas foram:
    // 1º Injetar o Serviço mais o Objecto que necessitassem, se fosse necessário
    // 2º Verificar a que classe o objeto pertense, pois queria que a classe fosse generica
    // de modo a que a mesma classe recebesse objetos de serviços diferentes, podendo assim reaproveitar o codigo.
    // Desta forma tambem conseguimos garantir um fraco acuplamento entre as classes
    // Concentrando tudo isso na classe que efetua as pesquisas ou os insertes, que são 
    // as unicas que conhecem as classes dos serviços e entidades pois necessitam de comunicar com os serviços.
    // 3º Executar a operação, sendo ela pesquisar, inserir ou atualizar.
    // 4º Devolver sempre um JSON Object Pronto para ser devolvido como resposta 
    // ao pedido do feito à API. 
    public JSONObject executeAction()
    {
        JSONObject result = new JSONObject();
        createMethodMap();
        String className = checkInstance();
        JSONObject obj = executeOperation(className);
        System.out.println("obj: " + obj.toString());
        if (checkReturnObject(obj))
        {
           return obj;
        }
        return null; 
    }

    // Metodo abstrato que as classes são Obrigadas a implementar para conseguirem
    // adicionar os metodos ao mapa para fazer o mapeamento "String -> Metodo"
    protected abstract void createMethodMap();
    
    // metodo chamado pelo controller que vai devolver um JSONObject
    //implementado na classe que vai fazer as pesquisas
    protected abstract JSONObject executeOperation(String value);
    protected abstract String checkInstance();

    // verificar se o registo contem informação antes de ser devolvido ao controller
    protected boolean checkReturnObject(JSONObject obj)
    {
        if (obj!= null && !obj.isEmpty())
                return true;
        return false;
    }

   

   
    
    
}
