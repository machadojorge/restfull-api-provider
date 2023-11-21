package com.estudarecompensa.ativityprovider.interfacesImpl;

import com.estudarecompensa.ativityprovider.interfaces.IView;

public class ViewFactory implements IView {

    private String viewCnfig;
    public ViewFactory()
    {
        viewCnfig = "This is a Web page, in one line! \nThis is the code of the view config_url"; 
    }

    @Override
    public String viewProvider(String viewRequest) {
        if (viewRequest.equals("config_url"))
        {
            return viewCnfig;
        }
      return viewCnfig;
    }
    
}
