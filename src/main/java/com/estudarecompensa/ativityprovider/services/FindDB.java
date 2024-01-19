// package com.estudarecompensa.ativityprovider.services;

// import java.util.List;

// import org.json.JSONObject;

// import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
// import com.estudarecompensa.ativityprovider.interfaces.ICommand;

// public class FindDB <T> implements ICommand  {

//     private T dbSearch;

//     public void setClassService(T service)
//     {
//        this.dbSearch = service;
//     }

//     @Override
//     public JSONObject execute() {
       
//         if (this.dbSearch instanceof DeployService)
//         {
//             List<DeployActivity> apps = ((DeployService) this.dbSearch).getAllDeployInstance();

//         }
//     }

    

//     public JSONObject getObject(List<T> obj)
    
// }
