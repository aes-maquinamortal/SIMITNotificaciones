package com.simit.notificacion.notificacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@SpringBootApplication
public class NotificacionApplication {

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(NotificacionApplication.class, args);
		startQueueConsumer();
	}
	
	@SuppressWarnings("unchecked")
	private static void startQueueConsumer() throws IOException, TimeoutException {
		System.out.println("Queue consumer is running...");
		DeliverCallback callback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			final Gson gson = new Gson();
			final Map<String, String> messageMap = gson.fromJson(message, HashMap.class);
		};
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setPort(5672);
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(EnvioNotificacion.MAIL_QUEUE, false, false, false, null);
	    channel.basicConsume(EnvioNotificacion.MAIL_QUEUE, true, callback, consumerTag -> { });
	}

}
