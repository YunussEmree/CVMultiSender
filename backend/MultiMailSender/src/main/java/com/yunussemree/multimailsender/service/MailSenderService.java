package com.yunussemree.multimailsender.service;

import com.yunussemree.multimailsender.model.ApiResponse;
import com.yunussemree.multimailsender.model.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Properties;

@Service
public class MailSenderService {

    private final JavaMailSenderImpl mailSender;

    public MailSenderService() {
        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setHost("smtp.gmail.com");
        this.mailSender.setPort(587);

        Properties props = this.mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
    }

    /**
     * Sends a single email using the provided parameters. Test Method.
     *
     * @param to       The recipient's email address.
     * @param subject  The subject of the email.
     * @param body     The body content of the email.
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    public void sendEmail(String to, String subject, String body, String username, String password) {
        this.mailSender.setUsername(username);
        this.mailSender.setPassword(password);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    /**
     * Sends multiple emails based on the provided request.
     *
     * @param request The request containing email details and company data.
     * @throws Exception If an error occurs while sending emails.
     */
    public void sendEmails(Request request) throws Exception {
        this.mailSender.setUsername(request.getUsername());
        this.mailSender.setPassword(request.getPassword());

        for (var company : request.getCompanyData()) {
            HashMap<String,String> parameters = company.getParameters();
            String body = request.getBodydraft();
            for (String key : parameters.keySet()) {
                body = body.replace("{" + key + "}", parameters.get(key));
            }

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(company.getCompanyMail());
            message.setSubject(request.getSubject());
            message.setText(body);
            mailSender.send(message);

             Thread.sleep(2000); // Wait for 2 seconds to avoid rate limiting
        }
    }



}
