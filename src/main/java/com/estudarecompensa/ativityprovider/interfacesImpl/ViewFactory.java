package com.estudarecompensa.ativityprovider.interfacesImpl;

import com.estudarecompensa.ativityprovider.interfaces.IView;

public class ViewFactory implements IView {

    public ViewFactory()
    {
    }

    @Override
    public String viewProvider(String viewRequest) {
        if (viewRequest.equals("configure_ativity"))
        {
            return "config";
        }
      return "invalidpage";
    }
    
}
