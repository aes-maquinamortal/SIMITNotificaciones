package com.simit.notificacion.notificacion;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.simit.notificacion.entidades.ETC;

import org.springframework.stereotype.Component;

@Component
public class EnvioNotificacion {
	
	public final static String MAIL_QUEUE = "send_mail";
	
	@WebMethod(action = "envioNotificacionUtilidad")
	public void envioNotificacionUtilidad(ETC etc, String tipo, String jsonPayload) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		try ( Connection connection = factory.newConnection();
			  Channel channel = connection.createChannel()
			) {
			final Map<String, String> message = new HashMap();
			final Gson gson = new Gson();
			message.put("etc", gson.toJson(etc, ETC.class));
			message.put("tipo", tipo);
			message.put("payload", jsonPayload);
			final String messageBytes = gson.toJson(message);
			channel.queueDeclare(MAIL_QUEUE, false, false, false, null);
			channel.basicPublish("", MAIL_QUEUE, null, messageBytes.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
