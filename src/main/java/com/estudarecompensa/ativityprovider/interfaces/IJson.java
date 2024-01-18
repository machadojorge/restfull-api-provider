package com.estudarecompensa.ativityprovider.interfaces;

import org.json.JSONObject;

// Foi criada esta interface pois, quando for criado um objecto desta interface do tipo da da implementação "JsonAdapter",
// (e.g. IJson obj = new JsonAdapter(service) ), só terão acesso a este metodo da classe JsonAdapter
public interface IJson {
    
    public JSONObject toJsonObject();
}
