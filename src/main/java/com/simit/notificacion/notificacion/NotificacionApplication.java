package com.simit.notificacion.notificacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.simit.notificacion.entidades.ETC;

import freemarker.template.TemplateException;

@SpringBootApplication
public class NotificacionApplication implements ApplicationRunner {
	
	@Autowired
	public EmailService emailService;

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(NotificacionApplication.class, args);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("Queue consumer is running...");
		DeliverCallback callback = (consumerTag, delivery) -> {
			ETC etc = new ETC();
			String message = new String(delivery.getBody(), "UTF-8");
			final Gson gson = new Gson();
			final Map<String, String> messageMap = gson.fromJson(message, HashMap.class);
			etc = gson.fromJson(messageMap.get("etc"), ETC.class);
			final String tipo = messageMap.get("tipo");
			final Map<String, String> payload = gson.fromJson(messageMap.get("payload"), HashMap.class);
			
			sendEmail(etc, tipo, payload);
		};
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("some-rabbit");
	    factory.setPort(5672);
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(EnvioNotificacion.MAIL_QUEUE, false, false, false, null);
	    channel.basicConsume(EnvioNotificacion.MAIL_QUEUE, true, callback, consumerTag -> { });
	}
	
	private void sendEmail(ETC etc, String tipo, Map<String, String> payload) {
		Mail mail = new Mail();
		mail.setModel(payload);
		mail.setContent("");
		mail.setFrom("mailer.nodejs2019@gmail.com");
		mail.setSubject("Notificación pago.");
		mail.setTo(etc.getCorreo());
		try {
			emailService.sendSimpleMessage(mail);
		} catch (MessagingException | IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
