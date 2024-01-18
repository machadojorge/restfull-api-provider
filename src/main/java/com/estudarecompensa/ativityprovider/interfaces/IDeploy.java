package com.estudarecompensa.ativityprovider.interfaces;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;

public interface IDeploy <T> {
    
    public T getAllDeployInstance();

    public boolean saveValues(DeployActivity deployActivity);
}
