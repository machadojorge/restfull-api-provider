package com.estudarecompensa.ativityprovider.interfaces;

import org.json.JSONObject;

public interface ICommand <T> {
    public JSONObject execute();
    
}
