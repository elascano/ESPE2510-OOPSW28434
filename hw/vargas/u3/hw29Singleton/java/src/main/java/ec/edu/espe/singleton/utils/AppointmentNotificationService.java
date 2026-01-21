package ec.edu.espe.singleton.utils;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class AppointmentNotificationService {

    private final String senderEmail;
    private final String appPassword;
    private final Session mailSession;


    public AppointmentNotificationService() {
        GmailConfiguration config = GmailConfiguration.getInstance();
        this.senderEmail = config.getSenderEmail();
        this.appPassword = config.getAppPassword();
        this.mailSession = createMailSession();
    }

  
    public AppointmentNotificationService(String senderEmail, String appPassword) {
        this.senderEmail = senderEmail;
        this.appPassword = appPassword;
        this.mailSession = createMailSession();
    }

    private Session createMailSession() {

        GmailConfiguration config = GmailConfiguration.getInstance();
        
        Properties props = new Properties();
        

        props.put("mail.smtp.host", config.getSmtpHost() != null ? config.getSmtpHost() : "smtp.gmail.com");
        props.put("mail.smtp.auth", config.getSmtpAuth() != null ? config.getSmtpAuth() : "true");
        props.put("mail.smtp.port", config.getSmtpPort() != null ? config.getSmtpPort() : "465");
        props.put("mail.smtp.socketFactory.port", config.getSocketFactoryPort() != null ? config.getSocketFactoryPort() : "465");
        props.put("mail.smtp.socketFactory.class", config.getSocketFactoryClass() != null ? config.getSocketFactoryClass() : "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });
    }

    public void sendReservationConfirmation(String recipientEmail, String patientName, String appointmentDetails) {

        if (senderEmail == null || appPassword == null) {
            System.err.println("Error: No hay credenciales de correo configuradas.");
            return;
        }

        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            String subject = "Confirmación de Cita Médica - Clínica Toamedical";
            message.setSubject(subject);
            String emailBody = buildEmailBody(patientName, appointmentDetails);
            message.setText(emailBody);

            Transport.send(message);

            System.out.println(" Notificación enviada correctamente a: " + recipientEmail);

        } catch (MessagingException e) {
            System.err.println(" Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String buildEmailBody(String name, String details) {
        return "Estimado/a " + name + ",\n\n"
                + "Su cita ha sido reservada exitosamente.\n"
                + "Fecha de la cita agendada: " + details + "\n\n"
                + "Por favor, llegue 10 minutos antes.\n"
                + "Atentamente,\n"
                + "Tú clínica de confianza Toamedical.";
    }

}
