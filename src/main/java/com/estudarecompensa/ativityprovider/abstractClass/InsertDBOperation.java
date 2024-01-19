package com.estudarecompensa.ativityprovider.abstractClass;

import java.util.List;

import org.json.JSONObject;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.services.ConfigAnalyticsParamsService;
import com.estudarecompensa.ativityprovider.services.DeployService;
import com.estudarecompensa.ativityprovider.utils.CheckExist;

public class InsertDBOperation<T, M> extends AbstractDBOperation {

    public InsertDBOperation(T service, M instance)
    {
        super(service, instance);
    }

    @Override
    public JSONObject executeOperation() {
        JSONObject result = new JSONObject();
        result = this.checkInstance();
       return result;

    }

    @Override
    protected JSONObject checkInstance() {
        boolean result = false;
        JSONObject instance = new JSONObject();
        if(this.getService() instanceof DeployService)
        {
           result = checkRecord();
        }
        instance.put("Result", result);
        return instance;
    }

    @Override
    protected boolean checkRecord()
    {
        List<DeployActivity> activities = (List<DeployActivity>) ((DeployService) this.getService()).getAllDeployInstance();
        if(!CheckExist.existInDatabase(activities, ((DeployActivity) this.getObjectInstance()).getintance_ativity()))
        {
            System.out.println( "Ativities: !" + activities);
            boolean result = ((DeployService)this.getService()).saveValues((DeployActivity)this.getObjectInstance());
            System.out.println( "Registo guardado code: " + result);
            return result;
        }
        
        return false;
        // if(this.getService() instanceof DeployService)
        // {
        // List<DeployActivity> activities = (List<DeployActivity>) ((DeployService) this.getService()).getAllDeployInstance();
        // if(!CheckExist.existInDatabase(activities, ((DeployActivity) this.getObjectInstance()).getintance_ativity()))
        // {
        //    return true;
        // }
        // }
        // return false;
    }


    
    
}
