package com.estudarecompensa.ativityprovider.abstractClass;

import org.json.JSONObject;

public abstract class AbstractDBOperation <T, M>{

    protected T service;

    protected M objectInstance;

    public AbstractDBOperation( T service) {
        System.out.println("Serviço " + service);
         this.service = service;
       
    }
    public AbstractDBOperation( T service, M objectInstance) {
          this.service = service;
          this.objectInstance = objectInstance;
       
    }

    // verificar se o registo existe na base de dados
    // vai ser subscrito no InsertDBOperation
    protected boolean checkRecord()
    {
        return false;
    }

    // metodo chamado pelo controller que vai devolver um JSONObject
    //implementado na classe que vai fazer as pesquisas
    public abstract JSONObject executeOperation();
    protected abstract JSONObject checkInstance();

    // // methodo chamado pelo controller, que passa a classe service e o objeto para guardar/atualizar
    // // vai ser implementado na classe que herda esta classe e faz os insertes
    // public boolean insertOrUpdateValue(T service, M objectInstance)
    // {
    //     setObjectInstance(objectInstance);
    //     setService(service);
    //     return true;
    // }


    public T getService() {
        return service;
    }

    public M getObjectInstance() {
        return objectInstance;
    }

    
    // public abstract JSONObject getFromDatabase(T service);
    
    
}
