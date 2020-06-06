package by.epam.javatraining.restaurant.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private String username;
    private String password;
    private static final Properties PROPERTIES = new Properties();

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;

        System.out.println(PROPERTIES.getProperty("mail.smtp.auth"));
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
        PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        PROPERTIES.put("mail.smtp.port", "587");
    }

    public void sendEmail(String subject, String text, String fromEmail, String toEmail){
        Session session = Session.getInstance(PROPERTIES, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Заголовок письма
            message.setSubject(subject);
            //Содержимое
            message.setText(text);

            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        EmailSender sender = new EmailSender("alehkastsiukovich@gmail.com", "ez4simple");
        sender.sendEmail("hello", "yo", "alehkastsiukovich@gmail.com", "alehkastsiukovich@gmail.com");
    }
}
