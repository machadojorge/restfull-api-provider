package com.estudarecompensa.ativityprovider.interfaces;

import java.util.List;

public interface IConfigParametersService <T>{

  public T getAllParameters(T ConfigParams);
  public List<T> getAllParameters();
    
}
