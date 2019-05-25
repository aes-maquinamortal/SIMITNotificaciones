package com.simit.notificacion.notificacion;

import javax.jws.WebMethod;

import org.springframework.stereotype.Component;

@Component
public class EnvioNotificacion {
	
	@WebMethod(action = "envioNotificacionUtilidad")
	public void envioNotificacionUtilidad(String correo, String tipo, String jsonPayload) {
			System.out.println(jsonPayload);
	}

}
