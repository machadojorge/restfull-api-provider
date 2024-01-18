package com.estudarecompensa.ativityprovider.utils;

import java.util.List;

import com.estudarecompensa.ativityprovider.entities.ConfigManager.DeployActivity;
import com.estudarecompensa.ativityprovider.entities.DAO.AnaliticDao;

public class CheckExist {

    public static boolean existInDatabase(List<DeployActivity> list, String valueCheck)
    {
        
        for (DeployActivity activity : list)
        {
            if (activity.getintance_ativity().equals(valueCheck))
            {
                return true;
            }
        }
        return false;
    }

       public static boolean existAnaliticsInDatabase(List<AnaliticDao> list, String ativity_id, String student_id)
    {
        
        for (AnaliticDao activity : list)
        {
            if (activity.getStudent_id().equals(student_id) && activity.getAtivity_instance().equals(ativity_id))
            {
                return true;
            }
        }
        return false;
    }
    
}
