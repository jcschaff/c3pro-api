package org.bch.c3pro.server.util;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.bch.c3pro.server.config.AppConfig;

/**
 * Utility class for sending emails via postfix
 * @author CHIP-IHL
 */
public class Mail {

    /**
     * Send an email if 'msg' contains 'error', with subject: prefix + error and body:msg
     * @param prefix the prefix
     * @param msg   the message
     * @param error the error message
     */
    public static void emailIfError(String prefix, String msg, String error) {
        if (msg.contains(error)) {
            send(prefix + error, msg);
        }
    }

    /**
     * Sends an email with subject and body text to the recipient in the config.properties
     * @param subject the subject
     * @param text  the text
     */
    public static void send(String subject, String text) {
        Properties props = new Properties();
        InternetAddress fromAddress = null;
        InternetAddress []toAddress = null;
        try {
            props.put("mail.smtp.host", AppConfig.getProp(AppConfig.HOST_SMTP, AppConfig.HOST_SMTP_DEFAULT));
            props.put("mail.smtp.port", AppConfig.getProp(AppConfig.PORT_SMTP, AppConfig.PORT_SMTP_DEFAULT));
            fromAddress = new InternetAddress("no-reply@uchc.edu");
            toAddress = InternetAddress.parse(AppConfig.getProp(AppConfig.MAIL_RECEIPTIENT, AppConfig.MAIL_RECEIPTIENT_DEFAULT));
        } catch (AddressException e) {
            e.printStackTrace();
        }
        Session mailSession = Session.getDefaultInstance(props);
        javax.mail.Message simpleMessage = new MimeMessage(mailSession);

        try {
            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipients(RecipientType.TO, toAddress);
            simpleMessage.setSubject(subject);
            simpleMessage.setText(text);
            Transport.send(simpleMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
