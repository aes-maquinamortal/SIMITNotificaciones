package com.simit.notificacion.notificacion;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

public class EndPointPrueba {
	
	
	 
    private static final String NAMESPACE_URI = "http://localhost/springsoap/gen";
	 

	 
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
	    public String getCountry() {
	        return "HOLA BB";
	    }
	

}
