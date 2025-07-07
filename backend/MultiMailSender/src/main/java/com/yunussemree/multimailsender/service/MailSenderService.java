package com.yunussemree.multimailsender.service;

import com.yunussemree.multimailsender.model.Request;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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

    public void sendEmailsWithAttachments(Request request, MultipartFile[] files) throws Exception {
        this.mailSender.setUsername(request.getUsername());
        this.mailSender.setPassword(request.getPassword());

        for (var company : request.getCompanyData()) {
            HashMap<String,String> parameters = company.getParameters();
            String body = request.getBodydraft();
            for (String key : parameters.keySet()) {
                body = body.replace("{" + key + "}", parameters.get(key));
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(company.getCompanyMail());
            helper.setSubject(request.getSubject());
            helper.setText(body);

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
                }
            }

            mailSender.send(message);
            Thread.sleep(2000); // Wait for 2 seconds to avoid rate limiting
        }
    }

}
