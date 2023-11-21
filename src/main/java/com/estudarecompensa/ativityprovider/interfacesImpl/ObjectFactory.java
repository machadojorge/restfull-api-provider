package com.estudarecompensa.ativityprovider.interfacesImpl;

import com.estudarecompensa.ativityprovider.interfaces.IView;

public class ObjectFactory {

    public static IView createViewFactory()
    {
        return new ViewFactory();
    }
    
}
