package com.registro.registro.Notificaciones;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EnviarCorreo {
    // Información de autenticación
    public static final String username = "adolfo.angel0124@gmail.com"; // Cambiar a tu dirección de correo
    public static final String password = "odcj wtpc gari mfum"; // Cambiar a tu contraseña

    public static String destinatario = "";

    public static void enviar(String titulo, String mensaje, String destinatario){
        // Configuración de las propiedades del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        // Crear una sesión de correo con autenticación
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Crear un mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(destinatario)); // Cambiar a tu dirección de correo
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); // Cambiar al destinatario
            message.setSubject(titulo);
            message.setText(mensaje);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo enviado con éxito");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
